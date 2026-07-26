package com.example.payment_service.constant;

//Final : Nobody can extend this class. It just utility class
public  final class KafkaTopics {
	private KafkaTopics() {
		//prevent constructor 
		/*
		 * It prevents new KafkaTopics() : because the class contains only constants
		 */
		 
		
	}
	
    /*
     *  static : Only one copy 	for the whole application
     *  final : cannot be changed accidentally
     */
	public static final String PAYMENT_CREATED = "payment-created";
	
	public static final String PAYMENT_REFUNDED = "payment-refunded";
	
	public static final String PAYMENT_STATUS_REFUNDED = "payment-status-updated";
	
}
