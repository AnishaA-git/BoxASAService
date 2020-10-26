                                                  ****************************************************
                                                                    Box As A Service
                                                  ****************************************************
PROJECT DETAILS:                                               
* 3 Tier Web Application, which serves as an online storage solution.
* Where users can upload, download, reupload and delete the files. 
* The application is highly available highly scalable and cost effective.
* www.mysaasproject.com

REPOSITORY INCLUDES:
* README.md - this file.
* .ebextensions/ - this directory contains the Java configuration file that
  allows AWS Elastic Beanstalk to deploy my Java application.
* buildspec.yml - this file is used by AWS CodeBuild to build the web
  application.
* pom.xml - this file is the Maven Project Object Model for the web application.
* src/main - this directory contains my Java service source files.
* src/test - this directory contains my Java service unit test files.
* template.yml - this file contains the description of AWS resources used by AWS
  CloudFormation to deploy my infrastructure.
  
PRE-REQUISITE:
* Install JDK 1.8 and JRE 1.8.
* Install Maven 3.6.3.
* Install Tomcat 9.0.
* Install Eclipse Neon and Eclipse AWS toolkit.
* MYSQL 8.0
* AWS account to use their services.

* Project needs to leverage following AWS services (at minimum) if you were to choose AWS as your cloud provider:
* EC2
* ELB
* Lambda
* AutoScaling Group
* Single AZ RDS (Describe what steps you can take to covert the single AZ DB into multi-AZ deployment as part of your project deliverable)
* CloudFront
* S3
* S3 Transfer Acceleration
* R53
* ElastiCache (Optional)
* CloudWatch
* SNS

DEVELOPMENT:
* Create a CodeStar Project in AWS of JSP and Java which uses Elastic Beanstalk for deployment, Link it with your GitHub and do a local checkout of the project.
* Develop the application using AWS Java SDK in order to access S3 and SNS.
* Use AWS IAM user Access Key and Secret Access key to connect your eclipse with AWS account. Integrate it with Elastic Beanstalk with the Environment variables.
* Commit the code to github frequently.
* CI/CD takes place as soon as code is committed to the github repo.
* Automatic deployment to AWS Elastic Beanstalk takes place. EC2 instance is created with auto scaling groups and load balancers.
* Create a RDS instance and connect it with local MYSQL. Create tables and columns according to the application requirement.
* Use Disaster Recovery policy as per assignmet-2. The most content uploaded by customers globally is used frequently in first 75 days but then it is rarely used. When content     is needed post 75 days, it is still needed instantly by the customers. The content needs to be available online only for one year after creation after which it needs to be       archived for one additional year for legal/compliance reasons before it can be deleted from the system.
* The download use case is leveraging CloudFront.
* Create a Lambda function to trigger S3 events like PUT object REMOVE object.
* Use CloudWatch to see the logs of the events trigger.
* Create a topic for SNS and Subscribers in order to recieve notification as an E-mail or SMS.
* Create health checks for the project in route53. Register your own domain with the help of route53.
* Create Alarms using CloudWatch.


For more details and application screenshots please the document of boxasaservice.doc.



