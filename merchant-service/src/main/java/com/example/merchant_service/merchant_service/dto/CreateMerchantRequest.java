package com.example.merchant_service.merchant_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateMerchantRequest {
	
	@NotBlank(message = "Merchant Name cannot be blank")
	private String merchantName;
	
	@NotBlank
	@Email(message = "Invalid Email Format")
	private String email;
	

}
