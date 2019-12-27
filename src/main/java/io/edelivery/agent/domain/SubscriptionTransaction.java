package io.edelivery.agent.domain;

import java.util.UUID;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class SubscriptionTransaction {

	private String id;
	
	private String sender;
	
	private String receiver;
	
	private UUID contractFolderID;
	
}
