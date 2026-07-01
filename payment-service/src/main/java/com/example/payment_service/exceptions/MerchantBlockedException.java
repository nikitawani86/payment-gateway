package com.example.payment_service.exceptions;

public class MerchantBlockedException extends RuntimeException {
	public MerchantBlockedException(String msg) {
		super(msg);
	}
}
