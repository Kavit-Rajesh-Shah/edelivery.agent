package io.edelivery.agent.transactionprofiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Subscription extends Transaction{
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${accesspointurl}") 
	private String accesspointurl;
	
	@Override
	public void processTransaction(String messageId) {
	}
}
