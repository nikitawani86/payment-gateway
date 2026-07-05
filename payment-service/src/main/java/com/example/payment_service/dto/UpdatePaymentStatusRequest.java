package com.example.payment_service.dto;

import com.example.payment_service.domains.PaymentStatus;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class UpdatePaymentStatusRequest {
	@NotNull(message = "Payment Status is required")
	private PaymentStatus status;
}
