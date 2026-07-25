package com.example.payment_service.events;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.example.payment_service.domains.PaymentMethod;
import com.example.payment_service.domains.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentCreatedEvent {
	private UUID paymentReference;
	
	private UUID merchantReference;
	
	private BigDecimal amount;
	
	private String currency;
	
	private PaymentMethod paymentMethod;
	
	private PaymentStatus paymentStatus;
	
	private LocalDateTime createdAt;
}
