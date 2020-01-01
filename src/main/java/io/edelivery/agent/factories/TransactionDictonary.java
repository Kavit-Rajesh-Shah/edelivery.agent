package io.edelivery.agent.factories;

import java.util.HashMap;
import java.util.Iterator;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import io.edelivery.agent.transactionprofiles.Transaction;

@Component
public class TransactionDictonary {
	
	private static HashMap<String, Transaction> transactionDictory = new HashMap<String, Transaction>();
	
	@Autowired
    ApplicationContext context;
	
	@PostConstruct
    public void initialize() {
        populateDataMapperMap(context.getBeansOfType(Transaction.class).values().iterator());
    }
	
	private void populateDataMapperMap(final Iterator<Transaction> classIterator) {
        while (classIterator.hasNext()) {
        	Transaction abstractClassImpl = (Transaction) classIterator.next();
        	transactionDictory.put(abstractClassImpl.toString(), abstractClassImpl);
        }
    }
	
	public static HashMap<String, Transaction> getDictionary() {
		return transactionDictory;
	}
}
