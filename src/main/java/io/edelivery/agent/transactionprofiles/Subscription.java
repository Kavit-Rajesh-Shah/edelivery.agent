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
	public void processTransaction(Notification notification) {
		log.info("Procesing Subscription Request");
		String messageId = notification.getEventData().getDocumentId();
		
		File transactionfile = getTransactionFile(accessPointService,messageId);
		fileService.saveFile(messageId, transactionfile);
	}
	
	@Override
    public String toString() {
        return "DOCUMENT.SUBSCRIPTION";
    }
}