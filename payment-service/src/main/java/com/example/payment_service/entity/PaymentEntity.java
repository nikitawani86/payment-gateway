package com.example.payment_service.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.payment_service.domains.PaymentMethod;
import com.example.payment_service.domains.PaymentStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "payment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "payment_reference",nullable = false , unique = true)
	private UUID paymentReference;
	
	@Column(name = "merchant_reference",nullable = false, unique = true)
	private UUID merchantReference;
	
	@Column(name = "amount",nullable = false,precision = 19,scale= 2)
	private BigDecimal amount;
	
	@Column(name = "currency" , nullable = false ,length = 3)
	private String currency;
	
	@Enumerated(EnumType.STRING)
	@Column(name="payment_method", nullable=false)
	private PaymentMethod paymentMethod;
	
	@Enumerated(EnumType.STRING)
	@Column(name="status", nullable=false)
	private PaymentStatus status;
	
	@CreationTimestamp
	@Column(name = "created_at",nullable = false,updatable=false)
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	@Column(name="updated_at" , nullable = false)
	private LocalDateTime updatedAt;
}
