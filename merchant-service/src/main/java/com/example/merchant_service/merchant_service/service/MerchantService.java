package com.example.merchant_service.merchant_service.service;

import java.util.UUID;

import com.example.merchant_service.merchant_service.dto.CreateMerchantRequest;
import com.example.merchant_service.merchant_service.dto.CreateMerchantResponse;

public interface MerchantService {
	
	CreateMerchantResponse createMerchant(CreateMerchantRequest request);
		
	CreateMerchantResponse getMerchant(UUID merchantReference);
	
	

}
