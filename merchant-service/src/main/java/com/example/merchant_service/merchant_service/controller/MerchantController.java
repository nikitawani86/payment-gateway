package com.example.merchant_service.merchant_service.controller;

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

import com.example.merchant_service.merchant_service.dto.ApiResponse;
import com.example.merchant_service.merchant_service.dto.CreateMerchantRequest;
import com.example.merchant_service.merchant_service.dto.CreateMerchantResponse;
import com.example.merchant_service.merchant_service.service.MerchantService;

import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/merchants")
@RequiredArgsConstructor
public class MerchantController {
	
	private final MerchantService merchantService;
	
	//Create Merchant API
	@PostMapping
	public ResponseEntity<ApiResponse<CreateMerchantResponse>> createMerchant(@RequestBody @Valid CreateMerchantRequest request){
		CreateMerchantResponse response = merchantService.createMerchant(request);
		 ApiResponse<CreateMerchantResponse> apiresponse = 
				 ApiResponse.<CreateMerchantResponse>builder()
				 .success(true)
				 .message("Merchant Created Successfully")
				 .data(response)
				 .timestamp(LocalDateTime.now())
				 .build();
		 
		 return ResponseEntity.status(HttpStatus.CREATED)
				 .body(apiresponse);
	}

	
	//Get For Merchant 
	@GetMapping("{merchantReference}")
	public ResponseEntity<ApiResponse<CreateMerchantResponse>> getMerchant(@PathVariable UUID merchantReference){
		CreateMerchantResponse respone = merchantService.getMerchant(merchantReference);
		
		ApiResponse<CreateMerchantResponse> apiResponse = 
				ApiResponse .<CreateMerchantResponse>builder()
				.success(true)
				.message("Merchant Fetched Successfully")
				.data(respone)
				.timestamp(LocalDateTime.now())
				.build();
		
		return ResponseEntity.ok(apiResponse);
	}
}
