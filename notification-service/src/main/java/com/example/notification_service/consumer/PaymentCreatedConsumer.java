package com.example.notification_service.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.notification_service.events.PaymentCreatedEvent;

@Component
public class PaymentCreatedConsumer {
	
	@KafkaListener(
			topics = "KafakTopics.PAYMENT_CREATED",
			groupId = "notification-group\""
			)
	public void consume(PaymentCreatedEvent event) {
		System.out.println("Received Payment Event ");
		
		System.out.println(event);
	}
}
