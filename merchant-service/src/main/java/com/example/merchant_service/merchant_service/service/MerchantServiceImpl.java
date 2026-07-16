package com.example.merchant_service.merchant_service.service;

import java.io.ObjectInputFilter.Status;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.merchant_service.merchant_service.domains.MerchantStatus;
import com.example.merchant_service.merchant_service.dto.CreateMerchantRequest;
import com.example.merchant_service.merchant_service.dto.MerchantResponse;
import com.example.merchant_service.merchant_service.dto.UpdateMerchantRequest;
import com.example.merchant_service.merchant_service.entity.MerchantEntity;
import com.example.merchant_service.merchant_service.exception.MerchantAlreadyExistsException;
import com.example.merchant_service.merchant_service.exception.MerchantNotFoundException;
import com.example.merchant_service.merchant_service.mapper.MerchantMapper;
import com.example.merchant_service.merchant_service.repository.MerchantRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MerchantServiceImpl implements MerchantService {

	private final MerchantRepository repo;
	
	private final MerchantMapper merchantMapper;

	@Override
	public MerchantResponse createMerchant(CreateMerchantRequest request) {
		// TODO Auto-generated method stub
		if (repo.existsByEmail(request.getEmail())) {
			throw new MerchantAlreadyExistsException("Merchant Already Exists with Email: " + request.getEmail());
		}

		MerchantEntity entity = MerchantEntity.builder().merchantReference(UUID.randomUUID())
				.merchantName(request.getMerchantName()).email(request.getEmail()).status(MerchantStatus.ACTIVE)
				.build();

		MerchantEntity savedMerchant = repo.save(entity);

		return merchantMapper.toResponse(savedMerchant);
	}

	@Override
	public MerchantResponse getMerchant(UUID merchantReference) {
		// TODO Auto-generated method stub
		MerchantEntity entity = repo.findByMerchantReference(merchantReference)
				.orElseThrow(() -> new MerchantNotFoundException("Merchant Not Found with id: " + merchantReference));
		return merchantMapper.toResponse(entity);

		// Update the Merchant

	}

	@Override
	@Transactional
	public MerchantResponse updateMerchant(UpdateMerchantRequest request, UUID merchantReference) {
		// TODO Auto-generated method stub
		MerchantEntity merchant = repo.findByMerchantReference(merchantReference)
				.orElseThrow(() -> new MerchantNotFoundException("Merchant Not Found with id : " + merchantReference));
		if (request.getEmail() != null && !request.getEmail().isBlank()
				&& repo.existsByEmailAndMerchantReferenceNot(request.getEmail(), merchantReference)) {
			throw new MerchantAlreadyExistsException("Email Already Exists");
		} else {
			merchant.setEmail(request.getEmail());
		}
		if (request.getMerchantName() != null) {
			merchant.setMerchantName(request.getMerchantName());
		}

		MerchantEntity update = repo.save(merchant);

		return merchantMapper.toResponse(update);
	}

	// Delete Merchant
	@Override
	@Transactional
	public MerchantResponse deleteMerchant(UUID merchantReference) {
		// TODO Auto-generated method stub
		MerchantEntity entity = repo.findByMerchantReference(merchantReference)
				.orElseThrow(() -> new MerchantNotFoundException("Merchant Not Found with id: " + merchantReference));
		if(entity.getStatus()!= MerchantStatus.INACTIVE) {
		entity.setStatus(MerchantStatus.INACTIVE);
		}
		
		
		MerchantEntity delete = repo.save(entity);
		return merchantMapper.toResponse(delete);
	}
	
	//Get  list of all Merchants
	public Page<MerchantResponse> getAllMerchants(int page , int size, String SortBy,String direction,MerchantStatus status){
		
		
		if(page < 0) {
			throw new IllegalArgumentException("Page number cannot be negative");
		}
		if(size <= 0 || size>100) {
			throw new IllegalArgumentException("Page size must be between 1 and 100");
		}
		
		//create sort object
		Sort sort = Sort.by(direction, SortBy);
		
		//create pageable object
		Pageable pageable = PageRequest.of(page, size,sort);
		
		//Fetch data
		Page<MerchantEntity> merchants;
		
		if(status != null) {
			merchants = repo.findByStatus(status, pageable);
		}else {
			merchants = repo.findAll(pageable);
		}
		
		return merchants.map(this :: mapToResponse);
		
		
	}
	
	/** 
	 * Maps MerchantEntity to CreateMerchantREsponse
	 */
	private MerchantResponse mapToResponse(MerchantEntity merchant) {
		return MerchantResponse.builder()
				.merchantReference(merchant.getMerchantReference())
				.merchantName(merchant.getMerchantName())
				.email(merchant.getEmail())
				.status(merchant.getStatus().name())
			    .build();
	}
	
	
}