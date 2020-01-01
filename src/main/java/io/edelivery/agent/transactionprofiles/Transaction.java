package io.edelivery.agent.transactionprofiles;

import java.io.File;

import io.edelivery.agent.domain.Notification;
import io.edelivery.agent.service.AccessPointService;

public abstract class Transaction {
	
	protected File getTransactionFile(AccessPointService accessPointService, String messageId) {
		return accessPointService.getTransactionFile(messageId);
	}
	
	// Process the Transaction
	public abstract void processTransaction(Notification notification);
}
