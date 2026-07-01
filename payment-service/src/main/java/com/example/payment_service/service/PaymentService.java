package com.example.payment_service.service;

import java.util.UUID;

import com.example.payment_service.dto.PaymentRequest;
import com.example.payment_service.dto.PaymentResponse;

public interface PaymentService {

	PaymentResponse createPayment(PaymentRequest request);
	
	PaymentResponse getPayment(UUID paymentReference);
}
