package com.example.merchant_service.merchant_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class MerchantRequest {
	
	@NotBlank
	private String merchantName;
	
	@NotBlank
	@Email(message = "Invalid Email Format")
	private String email;
	

}
