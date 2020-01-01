package io.edelivery.agent.transactionprofiles;

import org.springframework.stereotype.Component;

import io.edelivery.agent.domain.Notification;

@Component
public class Tender extends Transaction{
	
	@Override
	public void processTransaction(Notification notitication) {
		// TODO Auto-generated method stub
	}

	@Override
    public String toString() {
        return "DOCUMENT.TENDER";
    }
	
}
