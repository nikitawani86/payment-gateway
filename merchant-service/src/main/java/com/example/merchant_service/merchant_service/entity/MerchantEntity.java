package com.example.merchant_service.merchant_service.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.merchant_service.merchant_service.domains.MerchantStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
@Entity
@Table(name = "merchants")
public class MerchantEntity {

	private Long id;
	private UUID mechantReference;
	private String merchantNames;
	private String email;
	private MerchantStatus status;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
}
