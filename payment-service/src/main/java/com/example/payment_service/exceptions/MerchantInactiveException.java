package com.example.payment_service.exceptions;

public class MerchantInactiveException extends RuntimeException {
	public MerchantInactiveException(String msg) {
		super(msg);
	}

}
