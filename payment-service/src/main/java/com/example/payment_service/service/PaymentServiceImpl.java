package com.example.payment_service.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.payment_service.dto.ApiResponse;
import com.example.payment_service.dto.MerchantResponse;
import com.example.payment_service.dto.PaymentRequest;
import com.example.payment_service.dto.PaymentResponse;
import com.example.payment_service.entity.PaymentEntity;
import com.example.payment_service.entity.repository.PaymentRepository;
import com.example.payment_service.exceptions.MerchantBlockedException;
import com.example.payment_service.exceptions.MerchantInactiveException;
import com.example.payment_service.feign.MerchantClient;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService{
	
	private final MerchantClient merchantclient;
	private final PaymentRepository repo;

	@Override
	public PaymentResponse createPayment(PaymentRequest request) {
		// TODO Auto-generated method stub
		MerchantResponse merchant = merchantclient.getMerchant(request.getMerchantReference()).getData();
		
		if(merchant.getStatus().equals("BLOCKED")) {
			throw new MerchantBlockedException("Merchant is Blocked");
		}
		
		if(merchant.getStatus().equals("INACTIVE")) {
			throw new MerchantInactiveException("Merchant is Inactive");
		}
		PaymentEntity payment = PaymentEntity.builder()
				.paymentReference(UUID.randomUUID())
				.merchantReference(merchant.getMerchantReference())
				.amount(request.getAmount())
				.currency(request.getCurrency())
				.createdAt(LocalDateTime.now())
				.build();
		
		PaymentEntity paymentEntity = repo.save(payment);
		
		return PaymentResponse.builder()
				.paymentReference(paymentEntity.getPaymentReference())
				.currency(paymentEntity.getCurrency())
				.amount(paymentEntity.getAmount())
				.build();
				
		
	}

	@Override
	public PaymentResponse getPayment(UUID paymentReference) {
		// TODO Auto-generated method stub

		PaymentEntity payment = repo.findByPaymentReference(paymentReference).orElseThrow(() -> new RuntimeException("Payment Not Found") );
		return PaymentResponse.builder()
			.paymentReference(payment.getPaymentReference())
			.merchantReference(payment.getMerchantReference())
			.amount(payment.getAmount())
			.currency(payment.getCurrency())
			.createdAt(payment.getCreatedAt())
			.build();
		
			
			
				
	}

}
