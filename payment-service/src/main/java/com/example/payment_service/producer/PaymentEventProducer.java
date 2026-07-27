package com.example.payment_service.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.example.payment_service.constant.KafkaTopics;
import com.example.payment_service.events.PaymentCreatedEvent;

@Component
public class PaymentEventProducer {
	
	private final KafkaTemplate<String,PaymentCreatedEvent> kafkaTemplate;
	
	public  PaymentEventProducer(KafkaTemplate<String,PaymentCreatedEvent> kafkaTemplate) {
	this.kafkaTemplate = kafkaTemplate;
	}
	
	public void publishPaymentCreatedEvent(PaymentCreatedEvent event) {
		kafkaTemplate.send(KafkaTopics.PAYMENT_CREATED,event);
	}

}
