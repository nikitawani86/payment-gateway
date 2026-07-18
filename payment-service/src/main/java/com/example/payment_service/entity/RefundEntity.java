package com.example.payment_service.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.payment_service.domains.PaymentStatus;
import com.example.payment_service.domains.RefundStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Refund")
public class RefundEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false , unique = true)
	private Long id;
	
	@Column(name = "payment_reference",nullable = false)
	private UUID paymentReference;
	
	@Column(precision = 19 ,scale = 2,name = "amount",nullable = false)
	private BigDecimal amount;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status" , nullable = false)
	private RefundStatus status;
	
	@CreationTimestamp
	@Column(name = "createdAt" ,nullable = false, updatable = false)
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	@Column(name = "updateAt",nullable = false)
	private LocalDateTime updatedAt;

}
