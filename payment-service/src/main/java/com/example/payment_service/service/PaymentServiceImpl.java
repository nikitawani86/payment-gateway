package com.example.payment_service.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.payment_service.dto.MerchantResponse;
import com.example.payment_service.dto.PaymentRequest;
import com.example.payment_service.dto.PaymentResponse;
import com.example.payment_service.exceptions.MerchantBlockedException;
import com.example.payment_service.exceptions.MerchantInactiveException;
import com.example.payment_service.feign.MerchantClient;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService{
	
	private final MerchantClient merchantclient;

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
			
		return null;
	}

	@Override
	public PaymentResponse getPayment(UUID paymentReference) {
		// TODO Auto-generated method stub
		return null;
	}

}
