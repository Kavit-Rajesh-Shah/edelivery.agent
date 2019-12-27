package io.edelivery.agent.factories;

import java.util.HashMap;

import org.springframework.stereotype.Component;

import io.edelivery.agent.transactionprofiles.Transaction;

@Component
public class TransactionFactory extends AbstractTransactionFactory {
	
	@Override
	public Transaction getTransaction(String eventType) {
		// TODO Auto-generated method stub
		HashMap<String, Transaction> transactionDictory = TransactionDictonary.getDictionary();
		return transactionDictory.get(eventType);
	}
}
