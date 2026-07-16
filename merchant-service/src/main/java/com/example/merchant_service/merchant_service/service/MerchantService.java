package com.example.merchant_service.merchant_service.service;

import java.util.UUID;

import org.springframework.data.domain.Page;

import com.example.merchant_service.merchant_service.domains.MerchantStatus;
import com.example.merchant_service.merchant_service.dto.CreateMerchantRequest;
import com.example.merchant_service.merchant_service.dto.MerchantResponse;
import com.example.merchant_service.merchant_service.dto.UpdateMerchantRequest;

public interface MerchantService {
	
	MerchantResponse createMerchant(CreateMerchantRequest request);
		
	MerchantResponse getMerchant(UUID merchantReference);
	
	MerchantResponse updateMerchant(UpdateMerchantRequest request , UUID merchantReference);
	
	MerchantResponse deleteMerchant(UUID merchantReference);
	
	Page<MerchantResponse> getAllMerchants(int page , int size, String SortBy,String direction,MerchantStatus status);
	
	

}
