package io.edelivery.agent.factories;

import java.util.HashMap;

import io.edelivery.agent.transactionprofiles.Subscription;
import io.edelivery.agent.transactionprofiles.Tender;
import io.edelivery.agent.transactionprofiles.TenderStatus;
import io.edelivery.agent.transactionprofiles.Transaction;

public class TransactionDictonary {
	
	static HashMap<String, Transaction> transactionDictory = new HashMap<String, Transaction>() {
		{
			put("DOCUMENT.SUBSCRIPTION",new Subscription());
			put("DOCUMENT.TENDERSTATUS",new TenderStatus());
			put("DOCUMENT.TENDERSTATUS",new Tender());
		}
	};
	
	public static HashMap<String, Transaction> getDictionary() {
		return transactionDictory;
	}
}
