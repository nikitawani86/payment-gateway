package com.example.notification_service.service;

import com.example.notification_service.events.PaymentCreatedEvent;

/*
 * Consumer should depend on abstraction not an implementation 
 */

public interface NotificationService {
	void sendPaymentSuccessNotification(PaymentCreatedEvent event);
	
	void sendEmail(PaymentCreatedEvent event);
}
