package io.edelivery.agent.factories;

import io.edelivery.agent.transactionprofiles.Transaction;

public abstract class AbstractTransactionFactory {
	
	public abstract Transaction getTransaction(String eventType);

}
