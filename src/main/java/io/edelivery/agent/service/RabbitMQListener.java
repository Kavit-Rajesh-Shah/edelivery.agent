package io.edelivery.agent.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

import io.edelivery.agent.domain.Notification;
import io.edelivery.agent.factories.TransactionFactory;
import io.edelivery.agent.transactionprofiles.Transaction;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class RabbitMQListener {

	@RabbitListener(queues = "${edelivery.rabbitmq.queue}")
	public void recievedMessage(Notification notification) throws Exception{
		log.info("Recieved Message From RabbitMQ: " + notification);

		Transaction transaction = TransactionFactory.getTransaction(notification.getEvent());
		if(transaction==null)
			log.error("Transaction is null");
		else 
			transaction.processTransaction(notification);
	}
}