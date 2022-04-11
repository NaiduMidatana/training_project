package com.mouritech.onlineshoppingsystem.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mouritech.onlineshoppingsystem.entity.Product;
import com.mouritech.onlineshoppingsystem.exception.ResourceNotFoundException;
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
	private Constants constants;

	/**
	 * Insert product
	 */
	@Override
	public Product insertProduct(Product newProduct) {
		newProduct.setProdId(generateProductId());
		return productRepository.save(newProduct);
	}

	/**
	 * Generate product id by using random number
	 * 
	 * @return product id in string format.
	 */
	public String generateProductId() {
		Random rand = new Random(); // instance of random class
		int upperbound = 255;
		// generate random values from 0-254
		Long pId = (long) rand.nextInt(upperbound);
		return "P00" + pId;

	}

	/**
	 * Show product by id
	 */
	@Override
	public Product showProductById(String prodId) throws ResourceNotFoundException {
		return productRepository.findByProdId(prodId)
				.orElseThrow(() -> new ResourceNotFoundException(constants.PRODUCT + prodId));
	}

	/**
	 * Show all products
	 */
	@Override
	public List<Product> showAllProducts() {
		return productRepository.findAll();

	}

	/**
	 * Update product by id
	 */
	@Override
	public Product updateProductById(String prodId, Product product) throws ResourceNotFoundException {
		Product existingProduct = productRepository.findByProdId(prodId)
				.orElseThrow(() -> new ResourceNotFoundException(constants.PRODUCT + prodId));
		existingProduct.setProdName(product.getProdName());
		existingProduct.setPrice(product.getPrice());
		existingProduct.setDescription(product.getDescription());
		existingProduct.setAvailableQuantity(product.getAvailableQuantity());
		productRepository.save(existingProduct);
		return existingProduct;
	}

	/**
	 * Delete product by id
	 */
	@Override
	public String deleteProductById(String prodId) throws ResourceNotFoundException {
		Product existingProduct = productRepository.findByProdId(prodId)
				.orElseThrow(() -> new ResourceNotFoundException(constants.PRODUCT + prodId));
		productRepository.delete(existingProduct);
		return "product deleted";

	}

	/**
	 * Get all products by category
	 */
	@Override
	public ResponseEntity<List<Product>> getAllProductsByCategoryId(String catid) {
		if (!categoryRepository.existsCategoryByCatId(catid)) {
			throw new ResourceNotFoundException(constants.CATEGORY + catid);
		}
		List<Product> products = productRepository.findByCategory_CatId(catid);
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	/**
	 * Create product with category
	 */
	@Override
	public ResponseEntity<Product> createProduct(String catid, Product newProduct) throws ResourceNotFoundException {

		Product product = categoryRepository.findByCatId(catid).map(category -> {
			newProduct.setCategory(category);
			newProduct.setProdId(generateProductId());
			return productRepository.save(newProduct);
		}).orElseThrow(() -> new ResourceNotFoundException(constants.CATEGORY + catid));
		return new ResponseEntity<Product>(newProduct, HttpStatus.CREATED);
	}

	/**
	 * Get product by category id and product name
	 */
	@Override
	public Product getCategory_CatIdByProdName(String catid, String productname) throws ResourceNotFoundException {
		return productRepository.findByCategory_CatIdAndProdName(catid, productname)
				.orElseThrow(() -> new ResourceNotFoundException(constants.PRODUCT + productname));
	}

}
