package io.edelivery.agent.transactionprofiles;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.edelivery.agent.domain.Notification;
import io.edelivery.agent.service.AccessPointService;
import io.edelivery.agent.service.FileStorageService;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Subscription extends Transaction{
	
	@Autowired
	private AccessPointService accessPointService;
	
	@Autowired
	private FileStorageService fileService; 
	
	@Override
<<<<<<< HEAD
	public void processTransaction(Notification notification) {
		
		log.info("Procesing Subscription Request");
		String messageId = notification.getEventData().getDocumentId();
		
		// Get the Transaction file
		if(accessPointService != null) {
		
		File transactionfile = getTransactionFile(accessPointService, 
												messageId);
		
		// Save the transaction file
		fileService.saveFile(messageId, transactionfile);
		}
=======
	public void processTransaction(String messageId) {
		System.out.println(accesspointurl);
>>>>>>> 592da0a2f48bd8624cbe9d283a974bbf7887d83e
	}
	
	@Override
    public String toString() {
        return "DOCUMENT.SUBSCRIPTION";
    }
}