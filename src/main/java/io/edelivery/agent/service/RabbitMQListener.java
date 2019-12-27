package io.edelivery.agent.service;

import java.io.File;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import io.edelivery.agent.domain.Notification;
import io.edelivery.agent.factories.AbstractTransactionFactory;
import io.edelivery.agent.transactionprofiles.Transaction;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class RabbitMQListener {

	@Autowired
	AbstractTransactionFactory transactionFactory;
	
	@Autowired
	S3FileService s3FileService;
	
	@RabbitListener(queues = "${edelivery.rabbitmq.queue}")
	public void recievedMessage(Notification notification) throws Exception{
		log.info("Recieved Message From RabbitMQ: " + notification);
		
		// Get the Transaction from the Factory
		Transaction transaction = transactionFactory.getTransaction(notification.getEvent());

		if(transaction==null)
			log.warn("Transaction is null");
		else {
			File file = transaction.getTransactionDetails(notification.getEventData().documentId);
			s3FileService.uploadFileTos3bucket(notification.getEventData().documentId, file);
			transaction.processTransaction(notification.getEventData().documentId);
		}	
	}
}
