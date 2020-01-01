package io.edelivery.agent.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
<<<<<<< HEAD
=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
>>>>>>> 592da0a2f48bd8624cbe9d283a974bbf7887d83e
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import io.edelivery.agent.domain.Notification;
import io.edelivery.agent.factories.TransactionFactory;
import io.edelivery.agent.transactionprofiles.Transaction;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class RabbitMQListener {

<<<<<<< HEAD
	@RabbitListener(queues = "${edelivery.rabbitmq.queue}")
	public void recievedMessage(Notification notification) throws Exception{
		log.info("Recieved Message From RabbitMQ: " + notification);
=======
	private RestTemplate restTemplate;
	
	@Autowired
	AbstractTransactionFactory transactionFactory;
	
	@Autowired
	RestTemplateBuilder builder;
	
	@Value("${accesspointurl}")
	String accesspointurl; 
	
	@Value("${validatorapiurl}")
	String validateapiurl;
	
	@Value("${filestorageurl}")
	String filestorageurl;
	
	@RabbitListener(queues = "${edelivery.rabbitmq.queue}")
	public void recievedMessage(Notification notification) throws Exception{
		log.info("Recieved Message From RabbitMQ: " + notification);
		
		this.restTemplate = builder.build();
		
		// Get the Transaction from the Factory
		Transaction transaction = transactionFactory.getTransaction(notification.getEvent());
>>>>>>> 592da0a2f48bd8624cbe9d283a974bbf7887d83e

		Transaction transaction = TransactionFactory.getTransaction(notification.getEvent());
		if(transaction==null)
<<<<<<< HEAD
			log.error("Transaction is null");
		else 
			transaction.processTransaction(notification);
=======
			log.warn("Transaction is null");
		else {
			log.info("Retrieving the Transaction file");
			File file = transaction.getTransactionDetails(notification.getEventData().documentId,restTemplate, accesspointurl);
			if(file==null)
				log.error("File retrieval failed");
			else
			{
				// Validate the file
				
				// Process the file
				
				// Save the file
				transaction.saveTransactionFile(notification.getEventData().documentId, file, restTemplate, filestorageurl);
			}
		}	
>>>>>>> 592da0a2f48bd8624cbe9d283a974bbf7887d83e
	}
}