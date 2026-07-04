package com.example.payment_service.exceptions;

public class MerchantNotFoundException extends RuntimeException {
	
	public MerchantNotFoundException(String msg) {
		super(msg);
	}
}
