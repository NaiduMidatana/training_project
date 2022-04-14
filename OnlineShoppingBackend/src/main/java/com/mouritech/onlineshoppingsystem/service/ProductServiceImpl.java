package com.mouritech.onlineshoppingsystem.service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mouritech.onlineshoppingsystem.dto.ProductDto;
import com.mouritech.onlineshoppingsystem.dto.ProductResponseDto;
import com.mouritech.onlineshoppingsystem.entity.Product;
import com.mouritech.onlineshoppingsystem.exception.ResourceNotFoundException;
import com.mouritech.onlineshoppingsystem.mapper.ProductMapper;
import com.mouritech.onlineshoppingsystem.repository.CategoryRepository;
import com.mouritech.onlineshoppingsystem.repository.ProductRepository;
import com.mouritech.onlineshoppingsystem.util.Constants;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	ProductMapper productMapper;
	@Autowired
	private Constants constants;
	
	@Override
	public ProductResponseDto insertProduct(ProductDto productDto) {
		Product product = productMapper.toProductEntity(productDto);
		product.setProdId(generateProductId());
		Product newProduct=productRepository.save(product);
		 return productMapper.toProductDTO(newProduct);
	}

	public String generateProductId() {
		Random rand = new Random(); // instance of random class
		int upperbound = 255;
		// generate random values from 0-254
		Long pId = (long) rand.nextInt(upperbound);
		return "P00" + pId;

	}

	@Override
	public Product showProductById(String prodId) throws ResourceNotFoundException {
	
		return productRepository.findByProdId(prodId)
				.orElseThrow(() -> new ResourceNotFoundException(constants.PRODUCT + prodId));
	}

	@Override
	public List<ProductResponseDto> showAllProducts() {
		List<Product> list = (List<Product>) productRepository.findAll();
		return list.stream().map(e->productMapper.toProductDTO(e)).collect(Collectors.toList());

	}

	@Override
	public ProductResponseDto updateProductById(String prodId, ProductDto productDto) throws ResourceNotFoundException {
		  Product existingProduct = productMapper. toProductEntity(productDto);
		//Product
existingProduct = productRepository.findByProdId(prodId)
				.orElseThrow(() -> new ResourceNotFoundException(constants.PRODUCT + prodId));
		existingProduct.setProdName(productDto.getProdName());
		existingProduct.setPrice(productDto.getPrice());
		existingProduct.setDescription(productDto.getDescription());
		existingProduct.setAvailableQuantity(productDto.getAvailableQuantity());
		Product updatedProduct=productRepository.save(existingProduct);
		ProductResponseDto postResponse = productMapper.toProductDTO(updatedProduct);
		 return postResponse;
//		return existingProduct;
//		  Product postRequest = productMapper. toProductEntity(productDto);
//			
//		  postRequest.setProductedOn(productDto.getProductedOn());
//		  postRequest.setProductStatus(productDto.getProductStatus());
//			        final Product updatedProduct = productRepository.save(product);
//			        ProductDto postResponse = productMapper.toProductDTO(updatedProduct);
			        
	}

	@Override
	public String deleteProductById(String prodId) throws ResourceNotFoundException {
		Product existingProduct = productRepository.findByProdId(prodId)
				.orElseThrow(() -> new ResourceNotFoundException(constants.PRODUCT + prodId));
		productRepository.delete(existingProduct);
		return "product deleted";
	
	}

	@Override
	public ResponseEntity<List<ProductResponseDto>> getAllProductsByCategoryId(String catid) {
		if (!categoryRepository.existsCategoryByCatId(catid)) {
			throw new ResourceNotFoundException(constants.CATEGORY + catid);
		}
		List<Product> products = productRepository.findByCategory_CatId(catid);
		return new ResponseEntity<List<ProductResponseDto>>(products.stream().map(e->productMapper.toProductDTO(e)).collect(Collectors.toList()), HttpStatus.OK);
	}


	@Override
	public ResponseEntity<ProductResponseDto> createProduct(String catid, ProductDto productDto) throws ResourceNotFoundException {
		Product product = productMapper. toProductEntity(productDto);
		Product existingProduct = categoryRepository.findByCatId(catid).map(category -> {
			product.setCategory(category);
			product.setProdId(generateProductId());
			return productRepository.save(product);
			 
		}).orElseThrow(() -> new ResourceNotFoundException(constants.CATEGORY + catid));
		
		return new ResponseEntity<ProductResponseDto>(productMapper.toProductDTO(product), HttpStatus.CREATED);
	}

	@Override
	public ProductResponseDto getCategory_CatIdByProdName(String catid, String productname)
			throws ResourceNotFoundException {
		Product product=productRepository.findByCategory_CatIdAndProdName(catid, productname).orElseThrow(
				() -> new ResourceNotFoundException(constants.PRODUCT + productname));
		
		return productMapper.toProductDTO(product);
	}

}
