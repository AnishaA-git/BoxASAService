
package com.aws.codestar.projecttemplates.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.aws.codestar.projecttemplates.databaselayer.DeleteFileFromDatabase;

public class DeleteController extends HttpServlet {

	/**
	 * @author Anisha Agarwal
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("deprecation")
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String accesskey = System.getProperty("AWS_ACCESS_KEY_ID");
		String secretkey = System.getProperty("AWS_SECRET_ACCESS_KEY");
		String firstusername = request.getSession(false).getAttribute("firstusername").toString();
		String filename = request.getParameter("myObject");
		String key;
		key = firstusername + CommonConstants.SUFFIX + filename;
		DeleteFileFromDatabase file = new DeleteFileFromDatabase();
		file.deleteFile(filename);

		try {
			AWSCredentials credentials = new BasicAWSCredentials(accesskey,secretkey);

			AmazonS3 s3client = new AmazonS3Client(credentials);
			s3client.setRegion(Region.getRegion(Regions.US_WEST_1));
			s3client.setEndpoint("s3.us-west-1.amazonaws.com");
			s3client.deleteObject(CommonConstants.BUCKET_NAME, key);
		} catch (AmazonClientException ace) {
			System.out.println("Caught an AmazonClientException, which means" + " the client encountered "
					+ "an internal error while trying to " + "communicate with S3, "
					+ "such as not being able to access the network.");
			System.out.println("Error Message: " + ace.getMessage());
		}

		RequestDispatcher dispatcher2 = request.getRequestDispatcher("MainMenu.jsp");
		dispatcher2.forward(request, response);

	}

}
