package com.mouritech.onlineshoppingsystem.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mouritech.onlineshoppingsystem.dto.UserDto;
import com.mouritech.onlineshoppingsystem.entity.Category;
import com.mouritech.onlineshoppingsystem.entity.Product;
import com.mouritech.onlineshoppingsystem.entity.ProductImage;
import com.mouritech.onlineshoppingsystem.entity.User;
import com.mouritech.onlineshoppingsystem.exception.ResourceNotFoundException;
import com.mouritech.onlineshoppingsystem.service.CartItemService;
import com.mouritech.onlineshoppingsystem.service.CartService;
import com.mouritech.onlineshoppingsystem.service.CategoryService;
import com.mouritech.onlineshoppingsystem.service.ProductImageService;
import com.mouritech.onlineshoppingsystem.service.ProductService;
import com.mouritech.onlineshoppingsystem.service.UserService;

@RestController
@RequestMapping("/api/v1/")
public class GuestController {
	
	
	@Autowired
	UserService userService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;
	@Autowired
	CartItemService cartitemService;
	@Autowired
	CartService cartService;
	@Autowired
	private ProductImageService productimageService;
	

	
	/////////////////////////user/////////////////
	//add user
	@PostMapping("/registerNewUser")
	public ResponseEntity<?> insertUser(@Valid @RequestBody User newUser) throws ResourceNotFoundException {
		return userService.insertUser(newUser);

	}
	/////////////////category//////////////////////
	//get all category
		@GetMapping("/get-all-categorys")
		 	public ResponseEntity<List<Category>> getCategorys() {
			return categoryService.showAllCategorys();
		}
		//get category by category id
		@GetMapping("/get-category/{cid}")
		public ResponseEntity<Category> showCatById(@PathVariable("cid") String catId) throws ResourceNotFoundException {
			return categoryService.showCatById(catId);

		}
		
		
		////////////////////product//////////////////////
		// get all  products 
		@GetMapping("get-all-products")
		public List<Product> showAllProducts() {
			return productService.showAllProducts();

		}
		// get all images
		@GetMapping("/get-all-product-details")
		public List<ProductImage> getAllImages() {
	
			return productimageService.getAllImages();
	
		}
		// get corresponding image for product id
		@GetMapping("get-image/{prodId}/prodImages")
		public ProductImage getImageByProdId(@PathVariable(value = "prodId") String prodId) {
			return productimageService.getImageByProdId(prodId);
		}

		// get product by cat id 
		@GetMapping("get-product/{catid}")

		public ResponseEntity<List<Product>> getAllProductsByCategoryId(@PathVariable("catid") String catid)
				throws ResourceNotFoundException {
			return productService.getAllProductsByCategoryId(catid);
		}
		// get product by cat-id and product name
		@GetMapping("/get-product/{catid}/{productname}")
		public Product getCategory_CatIdByProdName(@PathVariable("catid") String catId,
				@PathVariable("productname") String prodName)
				throws ResourceNotFoundException {
			return productService.getCategory_CatIdByProdName(catId, prodName);
		}
		///image 
		@GetMapping("/images/{imageid}")
		public ProductImage getImageById(@PathVariable("imageid") Long imageId) throws ResourceNotFoundException {
			return productimageService.getImageById(imageId);
		}
		
		@PostMapping("/login")
		public ResponseEntity<?> login(@RequestBody UserDto userDto) {
		 
			return userService.login(userDto);
		}

		
}
