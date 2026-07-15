package com.example.merchant_service.merchant_service.service;

import java.util.UUID;

import org.springframework.data.domain.Page;

import com.example.merchant_service.merchant_service.domains.MerchantStatus;
import com.example.merchant_service.merchant_service.dto.CreateMerchantRequest;
import com.example.merchant_service.merchant_service.dto.CreateMerchantResponse;
import com.example.merchant_service.merchant_service.dto.UpdateMerchantRequest;

public interface MerchantService {
	
	CreateMerchantResponse createMerchant(CreateMerchantRequest request);
		
	CreateMerchantResponse getMerchant(UUID merchantReference);
	
	CreateMerchantResponse updateMerchant(UpdateMerchantRequest request , UUID merchantReference);
	
	CreateMerchantResponse deleteMerchant(UUID merchantReference);
	
	Page<CreateMerchantResponse> getAllMerchants(int page , int size, String SortBy,String direction,MerchantStatus status);
	
	

}
