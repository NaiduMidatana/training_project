package com.mouritech.onlineshoppingsystem.service;

import java.util.List;
import org.springframework.http.ResponseEntity;

import com.mouritech.onlineshoppingsystem.dto.ProductDto;
import com.mouritech.onlineshoppingsystem.dto.ProductResponseDto;
import com.mouritech.onlineshoppingsystem.entity.Product;
import com.mouritech.onlineshoppingsystem.exception.ResourceNotFoundException;

public interface ProductService {

	ProductResponseDto insertProduct(ProductDto newProduct);

	Product showProductById(String prodId) throws ResourceNotFoundException;

	List<ProductResponseDto> showAllProducts();

	ProductResponseDto updateProductById(String prodId, ProductDto product) throws ResourceNotFoundException;

	String deleteProductById(String prodId) throws ResourceNotFoundException;

	ResponseEntity<List<ProductResponseDto>> getAllProductsByCategoryId(String catid);

	ResponseEntity<ProductResponseDto> createProduct(String catid, ProductDto productDto);

	ProductResponseDto getCategory_CatIdByProdName(String catid, String productname) throws ResourceNotFoundException;

}
