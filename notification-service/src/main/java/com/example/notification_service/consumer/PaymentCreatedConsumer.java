package com.example.notification_service.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.notification_service.events.PaymentCreatedEvent;
import com.example.notification_service.kafka.KafkaTopics;

@Component
public class PaymentCreatedConsumer {
	
	@KafkaListener(
			topics = KafkaTopics.PAYMENT_CREATED
			)
	public void consume(PaymentCreatedEvent event) {
		System.out.println("Received Payment Event ");
		
		System.out.println(event);
	}
}
