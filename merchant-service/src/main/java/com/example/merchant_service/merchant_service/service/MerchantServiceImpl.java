package com.example.merchant_service.merchant_service.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.merchant_service.merchant_service.domains.MerchantStatus;
import com.example.merchant_service.merchant_service.dto.CreateMerchantRequest;
import com.example.merchant_service.merchant_service.dto.CreateMerchantResponse;
import com.example.merchant_service.merchant_service.entity.MerchantEntity;
import com.example.merchant_service.merchant_service.exception.MerchantAlreadyExistsException;
import com.example.merchant_service.merchant_service.exception.MerchantNotFoundException;
import com.example.merchant_service.merchant_service.repository.MerchantRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MerchantServiceImpl implements MerchantService {

	private final  MerchantRepository repo;

	
	@Override
	public CreateMerchantResponse createMerchant(CreateMerchantRequest request) {
		// TODO Auto-generated method stub
		if (repo.existsByEmail(request.getEmail())) {
			throw new MerchantAlreadyExistsException("Merchant Already Exists with Email: " + request.getEmail());
		}

		MerchantEntity entity = MerchantEntity.builder().merchantReference(UUID.randomUUID())
				.merchantName(request.getMerchantName()).email(request.getEmail()).status(MerchantStatus.ACTIVE)
				.build();

		MerchantEntity savedMerchant = repo.save(entity);

		return CreateMerchantResponse.builder().merchantReference(savedMerchant.getMerchantReference())
				.merchantName(savedMerchant.getMerchantName()).email(savedMerchant.getEmail()).status(savedMerchant.getStatus().name())
				.build();
	}

	@Override
	public CreateMerchantResponse getMerchant(UUID merchantReference) {
		// TODO Auto-generated method stub
		MerchantEntity entity = repo.findByMerchantReference(merchantReference).orElseThrow(() -> new MerchantNotFoundException("Merchant Not Found with id: "+merchantReference));
		return CreateMerchantResponse.builder()
				.merchantReference(entity.getMerchantReference())
				.merchantName(entity.getMerchantName())
				.email(entity.getEmail())
				.status(entity.getStatus().name())
				.build();
	}

}
