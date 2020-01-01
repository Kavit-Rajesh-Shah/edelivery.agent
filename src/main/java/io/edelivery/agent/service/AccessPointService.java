package io.edelivery.agent.service;

import java.io.File;
import java.io.FileOutputStream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AccessPointService {

	@Value("${edelivery.accesspointurl}")
    private String accessPointUrl;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private File transaction_file = null;
	
	public File getTransactionFile(String messageId) {
		File file = restTemplate.execute(accessPointUrl + messageId,HttpMethod.GET,null,clientHttpResponse -> {
			transaction_file = File.createTempFile("edelivery", messageId);
		    StreamUtils.copy(clientHttpResponse.getBody(), new FileOutputStream(transaction_file));
		    return transaction_file;
		});
		return transaction_file;
	}
}
