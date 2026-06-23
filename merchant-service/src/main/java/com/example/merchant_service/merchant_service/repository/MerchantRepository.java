package com.example.merchant_service.merchant_service.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.merchant_service.merchant_service.entity.MerchantEntity;

public interface MerchantRepository  extends JpaRepository<MerchantEntity, Long>{
	Optional<MerchantEntity> findByMerchantReference(UUID merchantReference);
	boolean existsByEmail(String email);
}
