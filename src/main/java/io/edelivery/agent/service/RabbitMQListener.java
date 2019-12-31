package io.edelivery.agent.service;

import java.io.File;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import io.edelivery.agent.domain.Notification;
import io.edelivery.agent.factories.AbstractTransactionFactory;
import io.edelivery.agent.transactionprofiles.Transaction;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class RabbitMQListener {

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

		if(transaction==null)
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
	}
}
