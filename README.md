# edelivery.agent
Agent is microservice which listens to the Message Queue Notification and then process the Notification.

It uses the Factory Design Pattern to get the Transaction processor. The Template design pattern is used since the most common processing is 
clubbed together at the base class and only transaction specific functionality is implemented in the subclass.

The Web Service calls the Mock Service to get the Transaction file and later calls the validation service which will send the response in JSON format.

If everything is fine then the message is stored in S3 Bucket. The S3 Bucket storage is implemented inside this service.

# Planned Activity
1. Incorporate the Database storage mechanism using MongoDB 
2. Containarized the service and write Dockerfile
3. Add the Jenkins file for CI/CD 
