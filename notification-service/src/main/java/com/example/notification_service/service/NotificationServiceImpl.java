package com.example.notification_service.service;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;

import com.example.notification_service.events.PaymentCreatedEvent;

/*
 * @Service : Create an object(bean) of this class and manage it
 * 
 * why implements NotificationSerivice: Its programming interface and its core object-oriented design principle
 */
@Service
public class NotificationServiceImpl implements NotificationService {

	@Override
	public void sendPaymentSuccessNotification(PaymentCreatedEvent event) {
		
	
		// TODO Auto-generated method stub
		System.out.println("===============================");
		System.out.println("PAYMENT SUCCESS NOTIFICATION");
		System.out.println("Payment Reference: "+event.getPaymentReference());
		System.out.println("Merchant Reference: "+event.getMerchantReference());
		System.out.println("Amount: "+event.getAmount());
		System.out.println("Currency: "+event.getCurrency());
		System.out.println("Payment Method: "+event.getPaymentMethod());
		System.out.println("Created At: "+event.getCreatedAt());
		System.out.println("==============================");
		
	}

	@Override
	public void sendEmail(PaymentCreatedEvent event) {
		// TODO Auto-generated method stub
		throw new RuntimeException("SMTP Server Down");
	}

	
}
