package com.example.merchant_service.merchant_service.mapper;

import org.springframework.stereotype.Component;

import com.example.merchant_service.merchant_service.dto.MerchantResponse;
import com.example.merchant_service.merchant_service.entity.MerchantEntity;

@Component
public class MerchantMapper {
	
	public MerchantResponse toResponse(MerchantEntity entity) {
		return MerchantResponse.builder()
				.merchantReference(entity.getMerchantReference())
				.merchantName(entity.getMerchantName())
				.email(entity.getEmail())
				.status(entity.getStatus().name())
				.build();
	}

}
