package com.example.merchant_service.merchant_service.controller;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.merchant_service.merchant_service.domains.MerchantStatus;
import com.example.merchant_service.merchant_service.dto.ApiResponse;
import com.example.merchant_service.merchant_service.dto.CreateMerchantRequest;
import com.example.merchant_service.merchant_service.dto.MerchantResponse;
import com.example.merchant_service.merchant_service.dto.UpdateMerchantRequest;
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
	public ResponseEntity<ApiResponse<MerchantResponse>> createMerchant(@RequestBody @Valid CreateMerchantRequest request){
		MerchantResponse response = merchantService.createMerchant(request);
		 ApiResponse<MerchantResponse> apiresponse = 
				 ApiResponse.<MerchantResponse>builder()
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
	public ResponseEntity<ApiResponse<MerchantResponse>> getMerchant(@PathVariable UUID merchantReference){
		MerchantResponse respone = merchantService.getMerchant(merchantReference);
		
		ApiResponse<MerchantResponse> apiResponse = 
				ApiResponse .<MerchantResponse>builder()
				.success(true)
				.message("Merchant Fetched Successfully")
				.data(respone)
				.timestamp(LocalDateTime.now())
				.build();
		
		return ResponseEntity.ok(apiResponse);
	}
	
	//Update the Merchant
	@PutMapping("{merchantReference}")
	public ResponseEntity<ApiResponse<MerchantResponse>> updateMerchant(@PathVariable UUID merchantReference, @Valid @RequestBody UpdateMerchantRequest request ){
		
		MerchantResponse response = merchantService.updateMerchant(request, merchantReference);
		
		ApiResponse<MerchantResponse> apiResponse = 
				ApiResponse .<MerchantResponse>builder()
				.success(true)
				.message("Merchant Updated Successfully")
				.data(response)
				.timestamp(LocalDateTime.now())
				.build();
		
		return ResponseEntity.ok(apiResponse);
		
	}
	
	//Delete the MerchantS
	@DeleteMapping("{merchantReference}")
	public ResponseEntity<ApiResponse<MerchantResponse>> deleteMerchant(@PathVariable UUID merchantReference){
		MerchantResponse response = merchantService.deleteMerchant(merchantReference);
		
		ApiResponse<MerchantResponse> apiResponse = 
				ApiResponse.<MerchantResponse>builder()
				.success(true)
				.message("Merchant Deactivated Successfully")
				.data(response)
				.timestamp(LocalDateTime.now())
				.build();
		
		return ResponseEntity.ok(apiResponse);
	}
	
	//Get all Merchants
	@GetMapping
	public ResponseEntity<ApiResponse<Page<MerchantResponse>>> getAllMerchants(
			
			@RequestParam(defaultValue = "0")
			int page,
			
			@RequestParam(defaultValue = "10")
			int size,
			
			@RequestParam(defaultValue = "merchantName")
			String sortBy,
			
			@RequestParam(defaultValue = "ASC")
			Sort.Direction direction,
			
			@RequestParam(required = false)
			MerchantStatus   status )
	
	{
		Page<MerchantResponse> merchants = merchantService.getAllMerchants(page, size, sortBy, sortBy, status);
		
		ApiResponse<Page<MerchantResponse>> response = ApiResponse.<Page<MerchantResponse>> builder()
															.success(true)
															.message("Merchants Fetched Successfully")
															.data(merchants)
															.timestamp(LocalDateTime.now())
															.build();
		return ResponseEntity.ok(response);
	}
	
}
