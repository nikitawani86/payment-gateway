package com.example.notification_service.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.notification_service.events.PaymentCreatedEvent;
import com.example.notification_service.kafka.KafkaTopics;
import com.example.notification_service.service.NotificationService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Component
@AllArgsConstructor
public class PaymentCreatedConsumer {
	
	private final NotificationService notificationService;
	
	@KafkaListener(
			topics = KafkaTopics.PAYMENT_CREATED
			)
	public void consume(PaymentCreatedEvent event) {
		notificationService.sendPaymentSuccessNotification(event);
	
	}
}
