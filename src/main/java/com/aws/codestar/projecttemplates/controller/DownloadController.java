
package com.aws.codestar.projecttemplates.controller;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.aws.codestar.projecttemplates.sns.SNSImplementation;
import java.io.File;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.FileWriter;
import java.io.FileInputStream;
import org.apache.commons.io.IOUtils;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadController extends HttpServlet {

	/**
	 * @author Anisha Agarwal
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings({ "deprecation", "unused" })
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String accesskey = System.getenv("AWS_ACCESS_KEY_ID");
		String secretkey = System.getenv("AWS_SECRET_ACCESS_KEY");
		String firstusername = request.getSession(false).getAttribute("firstusername").toString();
		String filename = request.getParameter("myObject");
		String key;
		String dir = "C:\\Users\\anish\\Desktop\\281_Cloud_Technology\\";
		key = firstusername + CommonConstants.SUFFIX + filename;
		try {
			AWSCredentials credentials = new BasicAWSCredentials(accesskey,secretkey);

			AmazonS3 s3client = new AmazonS3Client(credentials);
			s3client.setRegion(Region.getRegion(Regions.US_WEST_1));
			s3client.setEndpoint("s3.us-west-1.amazonaws.com");
			S3Object s3object = s3client.getObject(new GetObjectRequest(CommonConstants.BUCKET_NAME,
					firstusername + CommonConstants.SUFFIX + filename));

			InputStream objectData = s3object.getObjectContent();

			InputStreamReader Ip = new InputStreamReader(objectData);

			StringWriter writer = new StringWriter();
			IOUtils.copy(objectData, writer, "UTF-8");
			String theFileContent = writer.toString();

			String totalfileName = dir + filename;
			File file = new File(totalfileName);
			file.createNewFile();

			FileWriter fwriter = new FileWriter(file);
			fwriter.write(theFileContent);
			fwriter.close();
			ServletContext context = getServletContext();
			String mimeType = context.getMimeType(dir);

			if (mimeType == null) {
				mimeType = "application/octet-stream";
			}

			FileInputStream inStream = new FileInputStream(file);
			response.setContentType(mimeType);
			response.setContentLength((int) file.length());

			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", file.getName());
			response.setHeader(headerKey, headerValue);

			OutputStream outStream = response.getOutputStream();

			byte[] buffer = new byte[4096];
			int bytesRead = -1;

			while ((bytesRead = inStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}
			inStream.close();
			outStream.close();

			SNSImplementation implementation = new SNSImplementation();
			implementation.setNotificationMethod();

		} catch (AmazonClientException ace) {
			System.out.println("Caught an AmazonClientException, which means" + " the client encountered "
					+ "an internal error while trying to " + "communicate with S3, "
					+ "such as not being able to access the network.");
			System.out.println("Error Message: " + ace.getMessage());
		}

	}

	@SuppressWarnings("unused")
	private static void displayTextInputStream(InputStream input) throws IOException {
		// Read one text line at a time and display.
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		while (true) {
			String line = reader.readLine();
			if (line == null) {
				break;
			}

			System.out.println("    " + line);
		}
		System.out.println();
	}
}
