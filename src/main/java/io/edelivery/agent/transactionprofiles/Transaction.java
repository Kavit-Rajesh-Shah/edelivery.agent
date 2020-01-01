package io.edelivery.agent.transactionprofiles;

import java.io.File;

<<<<<<< HEAD
import io.edelivery.agent.domain.Notification;
import io.edelivery.agent.service.AccessPointService;
=======
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
>>>>>>> 592da0a2f48bd8624cbe9d283a974bbf7887d83e

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Component
public abstract class Transaction {
	
<<<<<<< HEAD
	protected File getTransactionFile(AccessPointService accessPointService, String messageId) {
		return accessPointService.getTransactionFile(messageId);
=======
	File ret = null;

	// Get the transaction from Access Point
	public File getTransactionDetails(String messageId, RestTemplate restTemplate, String AccessPointUrl) {
		
		File file = restTemplate.execute(AccessPointUrl + messageId, HttpMethod.GET, null, clientHttpResponse -> {
			ret = File.createTempFile("edelivery", messageId);
		    StreamUtils.copy(clientHttpResponse.getBody(), new FileOutputStream(ret));
		    return ret;
		});
		return ret;
>>>>>>> 592da0a2f48bd8624cbe9d283a974bbf7887d83e
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
	public abstract void processTransaction(Notification notification);
}
