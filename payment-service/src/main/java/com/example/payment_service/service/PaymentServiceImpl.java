package com.example.payment_service.service;

import java.net.Authenticator.RequestorType;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.payment_service.domains.PaymentMethod;
import com.example.payment_service.domains.PaymentStatus;
import com.example.payment_service.domains.RefundStatus;
import com.example.payment_service.dto.ApiResponse;
import com.example.payment_service.dto.MerchantResponse;
import com.example.payment_service.dto.PaymentRequest;
import com.example.payment_service.dto.PaymentResponse;
import com.example.payment_service.dto.RefundResponse;
import com.example.payment_service.dto.UpdatePaymentStatusRequest;
import com.example.payment_service.entity.PaymentEntity;
import com.example.payment_service.entity.RefundEntity;
import com.example.payment_service.exceptions.InvalidPaymentStatusTransitionException;
import com.example.payment_service.exceptions.InvalidRefundException;
import com.example.payment_service.exceptions.MerchantBlockedException;
import com.example.payment_service.exceptions.MerchantInactiveException;
import com.example.payment_service.exceptions.PaymentNotFoundException;
import com.example.payment_service.feign.MerchantClient;
import com.example.payment_service.repository.PaymentRepository;
import com.example.payment_service.repository.RefundRepository;
import com.example.payment_service.validator.PaymentStatusValidator;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService{
	
	private final MerchantClient merchantclient;
	private final PaymentRepository repo;
	private final RefundRepository refundRepo;

	private final PaymentStatusValidator validator = new PaymentStatusValidator();
	@Override
	public PaymentResponse createPayment(PaymentRequest request) {
		// TODO Auto-generated method stub
		MerchantResponse merchant = merchantclient.getMerchant(request.getMerchantReference()).getData();
		
		if(merchant.getStatus().equals("BLOCKED")) {
			throw new MerchantBlockedException("Merchant is Blocked");
		}
		
		if(merchant.getStatus().equals("INACTIVE")) {
			throw new MerchantInactiveException("Merchant is Inactive");
		}
		PaymentEntity payment = PaymentEntity.builder()
				.paymentReference(UUID.randomUUID())
				.merchantReference(merchant.getMerchantReference())
				.amount(request.getAmount())
				.currency(request.getCurrency())
				.paymentMethod(request.getPaymentMethod())
				.status(PaymentStatus.INITIATED)
				.build();
		
		PaymentEntity paymentEntity = repo.save(payment);
		
		return PaymentResponse.builder()
				.paymentReference(paymentEntity.getPaymentReference())
				.currency(paymentEntity.getCurrency())
				.amount(paymentEntity.getAmount())
				.createdAt(payment.getCreatedAt())
				.status(paymentEntity.getStatus())
				.merchantReference(paymentEntity.getMerchantReference())
				.createdAt(paymentEntity.getCreatedAt())
				.build();
				
		
	}

	@Override
	public PaymentResponse getPayment(UUID paymentReference) {
		// TODO Auto-generated method stub

		PaymentEntity payment = repo.findByPaymentReference(paymentReference).orElseThrow(() -> new PaymentNotFoundException("Resource doesn't exists" +paymentReference) );
		return PaymentResponse.builder()
			.paymentReference(payment.getPaymentReference())
			.merchantReference(payment.getMerchantReference())
			.amount(payment.getAmount())
			.currency(payment.getCurrency())
			.paymentMethod(payment.getPaymentMethod())
			.status(payment.getStatus())
			.createdAt(payment.getCreatedAt())
			.build();
		
			
			
				
	}
	

	@Override
	public PaymentResponse updatePaymentStatus(UUID paymentReference, UpdatePaymentStatusRequest request) {
		// TODO Auto-generated method stub
PaymentEntity payment = repo.findByPaymentReference(paymentReference).orElseThrow(() -> new PaymentNotFoundException("Resource doesn't exists"));
		
		if(!validator.isTransitionAllowed(payment.getStatus(), request.getStatus())) {
			throw new InvalidPaymentStatusTransitionException("Cannot transition from " +payment.getStatus() + "to " +request.getStatus());
			
		}
		payment.setStatus(request.getStatus());
			
		PaymentEntity updatePayment = repo.save(payment);
		
		return PaymentResponse.builder()
				.paymentReference(updatePayment.getPaymentReference())
				.merchantReference(updatePayment.getMerchantReference())
				.amount(updatePayment.getAmount())
				.currency(updatePayment.getCurrency())
				.status(updatePayment.getStatus())
				.createdAt(updatePayment.getCreatedAt())
				.build();
		
	}

	@Override
	@Transactional
	public RefundResponse RefundPayments(UUID paymentRefernce) {
		// TODO Auto-generated method stub
		
	PaymentEntity payment = repo.findByPaymentReference(paymentRefernce).orElseThrow(() -> new PaymentNotFoundException("Resource doesn;t exists"));
	if(payment.getStatus().equals(PaymentStatus.CAPTURED) || payment.getStatus().equals(PaymentStatus.SETTLED)) {
		payment.setStatus(PaymentStatus.REFUNDED);
	}else {
		throw new InvalidRefundException("Refund is allowed only for CAPTURED or SETTLED payments.");
	}
	
	PaymentEntity updatedPayment = repo.save(payment);

	
	
	RefundEntity  refund = RefundEntity.builder()
			.refundReference(UUID.randomUUID())
			.paymentReference(updatedPayment.getPaymentReference())
			.amount(updatedPayment.getAmount())
			.status(RefundStatus.SUCCESS)
			.build();
	
	RefundEntity savedRefund = refundRepo.save(refund);
	
	
	return RefundResponse.builder()
			.refundReference(savedRefund.getRefundReference())
			.paymentReference(savedRefund.getPaymentReference())
			.refundAmount(savedRefund.getAmount())
			.status(savedRefund.getStatus())
			.createdAt(savedRefund.getCreatedAt())
			.build();
	
		
		
	}

}
