package com.aws.codestar.projecttemplates.sns;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.aws.codestar.projecttemplates.controller.CommonConstants;

/**
 *
 * @author Anisha Agarwal
 */
public class SNSImplementation {
	@SuppressWarnings("deprecation")
	public void setNotificationMethod() {
		try {
			System.out.println("in sns");
			String accesskey = System.getProperty("AWS_ACCESS_KEY_ID");
			String secretkey = System.getProperty("AWS_SECRET_ACCESS_KEY");
			AWSCredentials credentials = new BasicAWSCredentials(accesskey,secretkey);

			AmazonSNSClient snsClient = new AmazonSNSClient(credentials);
			snsClient.setRegion(Region.getRegion(Regions.US_WEST_1));

			String topicArn = "arn:aws:sns:us-west-1:336729709499:mytopicforsns281";
			String msgToSubscribers = "file has been downloaded from bucket";
			PublishRequest publishRequest = new PublishRequest(topicArn, msgToSubscribers);
			PublishResult publishResult = snsClient.publish(publishRequest);
			System.out.println("MessageId of the message published to subscribers- " + publishResult.getMessageId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
