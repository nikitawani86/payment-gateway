package com.example.notification_service.consumer;

import org.springframework.kafka.annotation.KafkaListener;

import org.springframework.stereotype.Component;

import com.example.notification_service.events.PaymentCreatedEvent;
import com.example.notification_service.kafka.KafkaTopics;
import com.example.notification_service.service.NotificationService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.kafka.support.Acknowledgment;

@Component
@AllArgsConstructor
public class PaymentCreatedConsumer {
	
	private final NotificationService notificationService;
	
	private static final Logger log = LoggerFactory.getLogger(PaymentCreatedConsumer.class);
	
	@KafkaListener(
			topics = KafkaTopics.PAYMENT_CREATED
			)
	public void consume(PaymentCreatedEvent event,Acknowledgment acknowledgment) {
		log.info("Received Payment Event: {}",event);
		
		
		notificationService.sendEmail(event);
		acknowledgment.acknowledge();
		
		log.info("Offset Committed Sucessfully");
	
	}
}
