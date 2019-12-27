package io.edelivery.agent.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
	
	@JsonProperty(value="event")
	private String event;
	
	@JsonProperty(value="company_id")
	private String companyId;
	
	@JsonProperty(value="event_timestamp")
	private String eventTimestamp;
	
	@JsonProperty(value="event_data")
	private EventData eventData;
}