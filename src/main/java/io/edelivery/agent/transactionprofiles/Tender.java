package io.edelivery.agent.transactionprofiles;

import org.springframework.stereotype.Component;

import io.edelivery.agent.domain.Notification;

@Component
public class Tender extends Transaction{
	
	@Override
	public void processTransaction(Notification notitication) {
		// TODO Auto-generated method stub
	}
<<<<<<< HEAD

	@Override
    public String toString() {
        return "DOCUMENT.TENDER";
    }
	
=======
>>>>>>> 592da0a2f48bd8624cbe9d283a974bbf7887d83e
}
