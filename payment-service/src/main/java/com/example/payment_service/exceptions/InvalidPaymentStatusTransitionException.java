package com.example.payment_service.exceptions;

public class InvalidPaymentStatusTransitionException extends RuntimeException	 {
	
	public InvalidPaymentStatusTransitionException(String msg) {
		super(msg);
	}

}
