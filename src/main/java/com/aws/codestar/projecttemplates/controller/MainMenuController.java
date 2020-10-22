package com.aws.codestar.projecttemplates.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.aws.codestar.projecttemplates.databaselayer.DeleteFileFromDatabase;
import com.aws.codestar.projecttemplates.databaselayer.FileDetailsToDatabase;

@MultipartConfig
public class MainMenuController extends HttpServlet {

	/**
	 * @author Anisha Agarwal
	 */
	private static final long serialVersionUID = 1L;
	private static boolean flagForNewFolder = true;

	@SuppressWarnings({ "deprecation" })
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<String> arrayListOfFile;
		arrayListOfFile = new ArrayList<>();
		String firstusername = request.getSession(true).getAttribute("firstusername").toString();
		String accesskey = System.getProperty("AWS_ACCESS_KEY_ID");
		String secretkey = System.getProperty("AWS_SECRET_ACCESS_KEY");

		AWSCredentials credentials = new BasicAWSCredentials(accesskey, secretkey);
		AmazonS3 s3client = new AmazonS3Client(credentials);
		s3client.setRegion(Region.getRegion(Regions.US_WEST_1));
		s3client.setEndpoint("s3.us-west-1.amazonaws.com");

		List<Part> fileParts;
		fileParts = request.getParts().stream().filter(part -> "file".equals(part.getName()))
				.collect(Collectors.toList());
		ObjectListing listofFolders;
		listofFolders = s3client.listObjects(CommonConstants.BUCKET_NAME);
		List<S3ObjectSummary> summaries = listofFolders.getObjectSummaries();
		listofFolders = s3client.listNextBatchOfObjects(listofFolders);
		summaries.addAll(listofFolders.getObjectSummaries());

		for (S3ObjectSummary objectSummary : summaries) {
			String uniqueId = objectSummary.getKey();
			if (uniqueId.equals(firstusername)) {
				flagForNewFolder = false;
				break;

			} else {
				flagForNewFolder = true;
			}
		}
		if (flagForNewFolder)
			createFolder(CommonConstants.BUCKET_NAME, firstusername, s3client);

		for (Part filePart : fileParts) {
			String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
			String key = firstusername + CommonConstants.SUFFIX + fileName;
			InputStream fileContent = filePart.getInputStream();

			StringWriter writer = new StringWriter();
			IOUtils.copy(fileContent, writer, "UTF-8");
			String theFileContent = writer.toString();

			String ec2_location = System.getProperty("user.dir") + "/" + "anisha/" + firstusername + fileName;
			System.out.println("######################################");
			System.out.println("file name is: " + fileName);
			System.out.println("this is fro system property: " + System.getProperty("user.dir"));
			System.out.println("ec2_location: " + ec2_location);
			System.out.println("######################################");

			File file = new File(ec2_location);
			if (file.exists()) {
				// this is update mode
				file.delete();
				DeleteFileFromDatabase d = new DeleteFileFromDatabase();
				d.deleteFile(fileName);
				s3client.deleteObject(CommonConstants.BUCKET_NAME, key);
			}

			file.setExecutable(true);
			file.setReadable(true);
			file.setWritable(false);

			file.createNewFile();

			FileWriter fwriter = new FileWriter(file);
			fwriter.write(theFileContent);
			fwriter.close();

			String completeFilePath = firstusername + CommonConstants.SUFFIX + fileName;

			s3client.putObject(new PutObjectRequest(CommonConstants.BUCKET_NAME, completeFilePath, file)
					.withCannedAcl(CannedAccessControlList.PublicRead));

			arrayListOfFile.add(fileName);
		}

		FileDetailsToDatabase info = new FileDetailsToDatabase();
		info.addDataIntoDatabase(firstusername, arrayListOfFile);

		try {
			RequestDispatcher dispatcher = request.getRequestDispatcher("MainMenu.jsp");
			dispatcher.forward(request, response);
		} catch (ServletException | IOException ex) {
			Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@SuppressWarnings("unused")
	public static void createFolder(String bucketName, String folderName, AmazonS3 client) {

		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(0);

		InputStream emptyContent = new ByteArrayInputStream(new byte[0]);

		PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, folderName + CommonConstants.SUFFIX,
				emptyContent, metadata);

		String rbn = putObjectRequest.getBucketName();

		PutObjectResult por = client.putObject(putObjectRequest);

	}

}
