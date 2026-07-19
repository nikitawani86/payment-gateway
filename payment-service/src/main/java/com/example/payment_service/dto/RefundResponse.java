package com.example.payment_service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.example.payment_service.domains.RefundStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class RefundResponse {
	private UUID refundReference;
    private UUID paymentReference;
    private BigDecimal refundAmount;
    private RefundStatus status;
    private LocalDateTime createdAt;
}
