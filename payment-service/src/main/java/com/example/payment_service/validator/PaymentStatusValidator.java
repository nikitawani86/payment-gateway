package com.example.payment_service.validator;

import java.util.Map;
import java.util.Set;

import com.example.payment_service.domains.PaymentStatus;
import static com.example.payment_service.domains.PaymentStatus.*;


public class PaymentStatusValidator {

	//final : These transition rules should never change after validator is created
private final Map<PaymentStatus, Set<PaymentStatus>> allowedTransitions;
	
	public PaymentStatusValidator() {
		
		allowedTransitions = Map.ofEntries(
		Map.entry(INITIATED,Set.of(PROCESSING)),
		Map.entry(PROCESSING,Set.of(AUTHORIZED,FAILED)),
		Map.entry(AUTHORIZED, Set.of(CAPTURED,FAILED)),
		Map.entry(CAPTURED, Set.of(SETTLED)),
		Map.entry(SETTLED,Set.of()),
		Map.entry(FAILED, Set.of())
		);
		
		
	}
	
	 public boolean isTransitionAllowed(PaymentStatus currentStatus, PaymentStatus nextStatus) {
		return 	allowedTransitions.getOrDefault(currentStatus, Set.of()).contains(nextStatus);
		
	}
}
