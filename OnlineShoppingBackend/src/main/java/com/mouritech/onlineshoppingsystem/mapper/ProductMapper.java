package com.mouritech.onlineshoppingsystem.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mouritech.onlineshoppingsystem.dto.ProductDto;
import com.mouritech.onlineshoppingsystem.dto.ProductImageDto;
import com.mouritech.onlineshoppingsystem.dto.ProductResponseDto;
import com.mouritech.onlineshoppingsystem.entity.Product;
import com.mouritech.onlineshoppingsystem.entity.ProductImage;
@Component
public class ProductMapper {

	

		
			@Autowired
			private ModelMapper modelMapper;
			public Product toProductEntity(ProductDto productDto) {
				return modelMapper.map(productDto, Product.class);
			}
			
			public ProductResponseDto toProductDTO(Product product) {
				ProductResponseDto detailsDto = modelMapper.map(product, ProductResponseDto.class);
				detailsDto.setCategoryName(product.getCategory().getCatName());
				
			
				return detailsDto;
			}
			
		
			public ProductImage toProductImageEntity(ProductImageDto productImageDto) {
				return modelMapper.map(productImageDto, ProductImage.class);
			}
			
			public ProductImageDto toProductImageDTO(ProductImage productImage) {
				return modelMapper.map(productImage, ProductImageDto.class);
			}
			
			


}
