package io.edelivery.agent.transactionprofiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.edelivery.agent.domain.Notification;
import io.edelivery.agent.service.AccessPointService;

@Component
public class TenderStatus extends Transaction{
	
	@Autowired
	AccessPointService accessPointService;
	
	@Override
	public void processTransaction(Notification notification) {
	}

	@Override
    public String toString() {
        return "DOCUMENT.TENDERSTATUS";
    }

}
