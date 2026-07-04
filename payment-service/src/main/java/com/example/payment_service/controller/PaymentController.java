package com.example.payment_service.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.payment_service.dto.ApiResponse;
import com.example.payment_service.dto.PaymentRequest;
import com.example.payment_service.dto.PaymentResponse;
import com.example.payment_service.service.PaymentService;
import com.example.payment_service.service.PaymentServiceImpl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {
	
	private final PaymentService service;
	
	@PostMapping
	public  ResponseEntity<ApiResponse<PaymentResponse>> createPayment(@Valid @RequestBody PaymentRequest request){
		
		PaymentResponse Response = service.createPayment(request);
		
		ApiResponse<PaymentResponse> apiResponse = 
				ApiResponse.<PaymentResponse>builder()
				.success(true)
			     .message("Payement Successful")
				.data(Response)
				.timestamp(LocalDateTime.now())
				.build();
				
				
			return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
				
			
				
		
	}
	
	@GetMapping("{paymentReference}")
	public ResponseEntity<ApiResponse<PaymentResponse>> getPayment(@PathVariable UUID paymentReference){
		PaymentResponse response = service.getPayment(paymentReference);
		ApiResponse<PaymentResponse> res = 
				ApiResponse.<PaymentResponse>builder()
				.success(true)
				.message("Payment Fetched Successfully")
				.data(response)
				.timestamp(LocalDateTime.now())
				.build();
		
		return ResponseEntity.ok(res);
		
	}

}
