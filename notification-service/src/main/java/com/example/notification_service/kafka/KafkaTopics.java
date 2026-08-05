package com.example.notification_service.kafka;

public class KafkaTopics {
	
	private KafkaTopics() {
		
	}
	
	public static final String PAYMENT_CREATED ="payment-created";
	
	public static final String PAYMENT_UPDATED = "payment-updated";
	
	public static final String PAYMENT_REFUNDED = "payment-refunded";
	
	public static final String PAYMENT_CREATED_DLT = "payment-created-dlt";

}
