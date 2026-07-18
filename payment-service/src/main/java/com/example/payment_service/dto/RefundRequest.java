package com.example.payment_service.dto;

import jakarta.validation.constraints.NotNull;

public class RefundRequest {
	
	@NotNull
	private String reason;
	

}
