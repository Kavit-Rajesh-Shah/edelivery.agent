package io.edelivery.agent.transactionprofiles;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;

import jdk.internal.jline.internal.Log;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Component
public abstract class Transaction {
	
	File ret = null;

	// Get the transaction from Access Point
	public File getTransactionDetails(String messageId, RestTemplate restTemplate, String AccessPointUrl) {
		
		File file = restTemplate.execute(AccessPointUrl + messageId, HttpMethod.GET, null, clientHttpResponse -> {
			ret = File.createTempFile("edelivery", messageId);
		    StreamUtils.copy(clientHttpResponse.getBody(), new FileOutputStream(ret));
		    return ret;
		});
		return ret;
	}
	
	public void saveTransactionFile(String messageId, File file, RestTemplate restTemplate, String FileStorageService) {
		
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("transactionfile", new FileSystemResource(file));
		body.add("TransactionID", messageId);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		
		HttpEntity<MultiValueMap<String, Object>> requestEntity
		  = new HttpEntity<>(body, headers);
		
		ResponseEntity<String> response = restTemplate
				  .postForEntity(FileStorageService, requestEntity, String.class);
	}
	// Process the Transaction
	abstract public void processTransaction(String messageId);
}
