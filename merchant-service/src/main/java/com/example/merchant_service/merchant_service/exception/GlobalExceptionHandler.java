package com.example.merchant_service.merchant_service.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.merchant_service.merchant_service.dto.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MerchantNotFoundException.class)
	public ResponseEntity<ApiResponse<Void>> handleMerchantNotFound(MerchantNotFoundException exception) {
		ApiResponse<Void> response = ApiResponse.<Void>builder().success(false).message(exception.getMessage()).data(null)
				.timestamp(LocalDateTime.now()).build();

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

	}

	@ExceptionHandler(MerchantAlreadyExistsException.class)
	public ResponseEntity<ApiResponse<Void>> handleMerchantAlreadyExists(MerchantAlreadyExistsException ex) {
		ApiResponse<Void> response = ApiResponse.<Void>builder().success(false).message(ex.getMessage()).data(null)
				.timestamp(LocalDateTime.now()).build();

		return ResponseEntity.status(HttpStatus.CONFLICT).body(response);

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
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<Void>> handlerException(Exception ex){
		ApiResponse<Void> response=  ApiResponse.<Void>builder().success(false).message("Internal Server Error").data(null).timestamp(LocalDateTime.now()).build();
		 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}
}
