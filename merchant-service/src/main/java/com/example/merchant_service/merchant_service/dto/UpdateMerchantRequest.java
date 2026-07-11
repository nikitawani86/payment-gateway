package com.example.merchant_service.merchant_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMerchantRequest {
	
	@Size(max = 100)
	private String merchantName;
	
	@Email
	@Size(max = 255)
	private String email;

}
