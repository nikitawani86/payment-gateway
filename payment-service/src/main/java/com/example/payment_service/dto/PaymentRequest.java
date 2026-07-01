package com.example.payment_service.dto;

import java.math.BigDecimal;
import java.util.UUID;

import com.example.payment_service.domains.PaymentMethod;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {
	
	@NotNull
	private UUID merchantReference;
	@NotNull
	@DecimalMin("0.01")
	private BigDecimal amount;
	@NotBlank
	@Size(min=3,max=3)
	@Pattern(regexp = "^[A-Z]{3}$")
	private String currency;
	@NotNull
	private PaymentMethod paymentMethod;
	
	
	
}
