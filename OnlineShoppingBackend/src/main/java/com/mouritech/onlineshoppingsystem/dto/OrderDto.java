package com.mouritech.onlineshoppingsystem.dto;

import java.math.BigDecimal;
import java.time.LocalDate;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.LastModifiedDate;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {


	private String orderId;

	@Min(100)
	@Max(100000)
	private BigDecimal amount;

	@NotBlank(message = "Address is required")
	private String  shippingAddress;
	

	@LastModifiedDate
	private LocalDate orderedOn;

	@NotBlank(message = "Status is required")
	private String orderStatus;
}
