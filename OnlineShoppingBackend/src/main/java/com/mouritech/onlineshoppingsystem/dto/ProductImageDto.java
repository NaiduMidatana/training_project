package com.mouritech.onlineshoppingsystem.dto;

import lombok.Data;

@Data
public class ProductImageDto {
	
	private Long id;
	private byte[] picByte;
	private ProductDto product;
}
