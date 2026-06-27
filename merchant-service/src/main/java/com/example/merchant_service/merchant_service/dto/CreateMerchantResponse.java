package com.example.merchant_service.merchant_service.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateMerchantResponse {
	
	private UUID merchantReference;
	private String merchantName;
	private String email;
	private String status;
	

}
