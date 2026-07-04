package com.example.payment_service.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
	
	@ExceptionHandler(MerchantNotFoundException.class)
	public ResponseEntity<ApiResponse<Void>> handleMerchantNotFoundException(MerchantNotFoundException exception){
		
		ApiResponse<Void> response = ApiResponse.<Void>builder()
				.success(false)
				.message("Merchant doesn't exist")
				.data(null)
				.timestamp(LocalDateTime.now())
				.build();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<Void>> handlerException(Exception ex){
		ApiResponse<Void> response=  ApiResponse.<Void>builder().success(false).message("Internal Server Error").data(null).timestamp(LocalDateTime.now()).build();
		 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<Map<String, String>>> handleValidatonException(
			MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

		ApiResponse<Map<String, String>> response = ApiResponse.<Map<String, String>>builder().success(false)
				.message("Validation failed").data(errors).timestamp(LocalDateTime.now()).build();
		return ResponseEntity.badRequest().body(response);

	}
}
