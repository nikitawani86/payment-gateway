package com.example.payment_service.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.payment_service.dto.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandle {
	
	@ExceptionHandler(PaymentNotFoundException.class)
	public ResponseEntity<ApiResponse<Void>> handlePaymentNotFound(PaymentNotFoundException exception){
		
		ApiResponse<Void> response = ApiResponse.<Void>builder()
				.success(false)
				.message("Resource doesn't exists")
				.data(null)
				.timestamp(LocalDateTime.now())
				.build();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
	
	@ExceptionHandler(MerchantBlockedException.class)
	public ResponseEntity<ApiResponse<Void>> handleMerchantBlockedException(MerchantBlockedException exception){
		
		ApiResponse<Void> response = ApiResponse.<Void>builder()
				.success(false)
				.message("Exists but access denied")
				.data(null)
				.timestamp(LocalDateTime.now())
				.build();
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
	}
	
	@ExceptionHandler(MerchantInactiveException.class)
	public ResponseEntity<ApiResponse<Void>> handleMerchantInactiveException(MerchantInactiveException exception){
		
		ApiResponse<Void> response = ApiResponse.<Void>builder()
				.success(false)
				.message("Exists but access denied")
				.data(null)
				.timestamp(LocalDateTime.now())
				.build();
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
	}
	
	
	
}
