package com.mouritech.onlineshoppingsystem.service;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.mouritech.onlineshoppingsystem.entity.Product;
import com.mouritech.onlineshoppingsystem.exception.ResourceNotFoundException;

public interface ProductService {

	Product insertProduct(Product newProduct);

	Product showProductById(String prodId) throws ResourceNotFoundException;

	List<Product> showAllProducts();

	Product updateProductById(String prodId, Product product) throws ResourceNotFoundException;

	String deleteProductById(String prodId) throws ResourceNotFoundException;

	ResponseEntity<List<Product>> getAllProductsByCategoryId(String catid);

	ResponseEntity<Product> createProduct(String catid, Product newProduct);

	Product getCategory_CatIdByProdName(String catid, String productname) throws ResourceNotFoundException;

}
