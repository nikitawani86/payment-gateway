package com.example.payment_service.repository;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.payment_service.entity.PaymentEntity;
import com.example.payment_service.entity.RefundEntity;

@Repository
public interface RefundRepository extends JpaRepository<RefundEntity, Long> {
	
	@Query("""
			select COALESCE (sum(r.amount),0) 
			from RefundEntity r 
			where r.paymentReference = :paymentReference
			""")
	BigDecimal getTotalRefundAmount(@Param("paymentReference") UUID paymentReference);

}
