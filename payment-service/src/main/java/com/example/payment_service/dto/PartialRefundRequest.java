package com.example.payment_service.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PartialRefundRequest {
		
	@NotNull
	@DecimalMin("0.01")
	@Digits(integer = 17 , fraction = 2)
	private BigDecimal amount;
	
	@NotBlank
	private String reason;
}