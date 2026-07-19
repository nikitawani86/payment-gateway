package com.example.payment_service.service;

import java.util.UUID;

import com.example.payment_service.dto.PartialRefundRequest;
import com.example.payment_service.dto.PaymentRequest;
import com.example.payment_service.dto.PaymentResponse;
import com.example.payment_service.dto.RefundResponse;
import com.example.payment_service.dto.UpdatePaymentStatusRequest;

public interface PaymentService {

	PaymentResponse createPayment(PaymentRequest request);
	
	PaymentResponse getPayment(UUID paymentReference);
	
	PaymentResponse updatePaymentStatus(UUID paymentReference, UpdatePaymentStatusRequest request);
	
	RefundResponse RefundPayments(UUID paymentRefernce);
	
	RefundResponse partialRefund(UUID paymentReference,PartialRefundRequest request);
}
