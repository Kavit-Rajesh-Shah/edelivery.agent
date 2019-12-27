package io.edelivery.agent.transactionprofiles;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.http.HttpMethod;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;

public abstract class Transaction {
	
	private File ret = null;
	
	// Get the transaction from Access Point
	public File getTransactionDetails(String messageId) {
		File file = new RestTemplate().execute("http://localhost:8090/api/" + messageId, HttpMethod.GET, null, clientHttpResponse -> {
			ret = File.createTempFile("edelivery", messageId);
		    StreamUtils.copy(clientHttpResponse.getBody(), new FileOutputStream(ret));
		    return ret;
		});
		return ret;
	}
	
	// Process the Transaction
	abstract public void processTransaction(String messageId);
}
