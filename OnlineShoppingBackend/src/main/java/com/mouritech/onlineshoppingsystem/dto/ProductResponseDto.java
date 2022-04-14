package com.mouritech.onlineshoppingsystem.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {

	
	private String prodId;
 	
	@NotBlank(message = "Product Name is required")
	private String prodName;

	@NotNull
	@Min(1)
	private double price;
	
	@NotBlank(message = "Description is required")

	private String description;
		
	@NotNull
	@Min(1)
	@Max(1000)
	private int availableQuantity;

	private String categoryName;
}
