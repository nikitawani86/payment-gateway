package com.example.merchant_service.merchant_service.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.merchant_service.merchant_service.entity.MerchantEntity;

@Repository
public interface MerchantRepository  extends JpaRepository<MerchantEntity, Long>{
	Optional<MerchantEntity> findByMerchantReference(UUID merchantReference);
	boolean existsByEmail(String email);
}
