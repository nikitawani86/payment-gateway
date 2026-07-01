package com.example.payment_service.feign;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.payment_service.dto.ApiResponse;
import com.example.payment_service.dto.MerchantResponse;

@FeignClient(name  = "merchant-service")
public interface MerchantClient {
	
	@GetMapping("/api/v1/merchants/{merchantReference}")
	ApiResponse<MerchantResponse> getMerchant(@PathVariable UUID merchantReference);

}
