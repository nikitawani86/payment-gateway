package com.example.payment_service.exceptions;

public class InvalidRefundException extends RuntimeException {
	
	public InvalidRefundException(String msg) {
		super(msg);
	}

}
