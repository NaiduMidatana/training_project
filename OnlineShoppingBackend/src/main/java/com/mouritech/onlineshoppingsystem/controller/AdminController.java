package com.mouritech.onlineshoppingsystem.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mouritech.onlineshoppingsystem.dto.CategoryDto;
import com.mouritech.onlineshoppingsystem.dto.OrderDetailsDto;
import com.mouritech.onlineshoppingsystem.dto.OrderDto;
import com.mouritech.onlineshoppingsystem.dto.ProductDto;
import com.mouritech.onlineshoppingsystem.dto.ProductResponseDto;
import com.mouritech.onlineshoppingsystem.entity.Cart;
import com.mouritech.onlineshoppingsystem.entity.Category;
import com.mouritech.onlineshoppingsystem.entity.Product;
import com.mouritech.onlineshoppingsystem.entity.ProductImage;
import com.mouritech.onlineshoppingsystem.entity.User;
import com.mouritech.onlineshoppingsystem.exception.ResourceNotFoundException;
import com.mouritech.onlineshoppingsystem.service.CartService;
import com.mouritech.onlineshoppingsystem.service.CategoryService;
import com.mouritech.onlineshoppingsystem.service.OrderDetailsService;
import com.mouritech.onlineshoppingsystem.service.OrderService;
import com.mouritech.onlineshoppingsystem.service.ProductImageService;
import com.mouritech.onlineshoppingsystem.service.ProductService;
import com.mouritech.onlineshoppingsystem.service.UserService;

@RestController

@RequestMapping("/admin/")
public class AdminController {
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;
	@Autowired
	CartService cartService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private ProductImageService productimageService;
	@Autowired
	private OrderDetailsService orderdetailsService;
	@Autowired
	UserService userService;

	@PostMapping("add-category")
		public ResponseEntity<CategoryDto> insertCategory(@RequestBody CategoryDto newCategory) {
		return categoryService.insertCategory(newCategory);
	}
	
	
	@PutMapping("update-category/{cid}")
	public ResponseEntity<CategoryDto> updateCatById(@PathVariable("cid") String catId, @RequestBody Category category)
			throws ResourceNotFoundException {
		return categoryService.updateCatById(catId, category);

	}

	
	@DeleteMapping("delete-category/{cid}")
	public ResponseEntity<?> deleteCatById(@PathVariable("cid") String catId) throws ResourceNotFoundException {
		return categoryService.deleteCatById(catId);
		
	}

	
	
	@PostMapping("add-product")
	public ProductResponseDto insertProduct(ProductDto newproduct) throws IOException {

		return productService.insertProduct(newproduct);

	}

	
	@GetMapping("get-product/{pid}")
	public Product showProductById(@PathVariable("pid") String prodId) throws ResourceNotFoundException {
		return productService.showProductById(prodId);

	}

	
	@PutMapping("update-product/{pid}")
	public ProductResponseDto updateProductById(@PathVariable("pid") String prodId, @RequestBody ProductDto product)
			throws ResourceNotFoundException {
		return productService.updateProductById(prodId, product);

	}


	@DeleteMapping("delete-product/{pid}")
	public String deleteProductById(@PathVariable("pid") String prodId) throws ResourceNotFoundException {
		return productService.deleteProductById(prodId);
	}

	
	@PostMapping("add-product/{catid}/category")
	public ResponseEntity<ProductResponseDto> createProduct(@PathVariable("catid") String catid, @RequestBody ProductDto newProduct)
			throws ResourceNotFoundException {
		return productService.createProduct(catid, newProduct);

	}
	


	
	@PostMapping("add-image/{prodId}/prodImage")
	public Optional<ProductImage> addImage(@PathVariable(value = "prodId") String prodId,
			@RequestParam("prodImage") MultipartFile file) throws IOException {

		return productimageService.addImage(prodId, file);

	}


	
	@PutMapping("update-image/{prodId}/prodImages")
	public Optional<Object> updateImage(@PathVariable(value = "prodId") String prodId,
			@RequestParam("prodImage") MultipartFile file) throws IOException {
		return productimageService.updateImage(prodId, file);

	}

	
	@DeleteMapping("delete-image/{prodId}/prodImages/{imageId}")
	public ResponseEntity<?> deleteImage(@PathVariable(value = "prodId") String prodId,
			@PathVariable(value = "imageId") Long imageId) throws ResourceNotFoundException {

		return productimageService.deleteImage(prodId, imageId);

	}

	
	

	
	@DeleteMapping("delete-user/{uid}")
	public String deleteUserById(@PathVariable("uid") String userId) throws ResourceNotFoundException {
		userService.deleteUserById(userId);
		return "deleted the user";

	}



	@GetMapping("/get-user")
	public List<User> showAllUsers() {
		return userService.showAllUsers();

	}

	
	
	//add cart
	@PostMapping("cart")
	public Cart insertCart(@RequestBody Cart newCart) {
		return cartService.insertCart(newCart);

	}
	
	
	@GetMapping("carts")
	public List<Cart> showAllCarts() {
		return cartService.showAllCarts();
	}

	
	@GetMapping("cart/{cid}")
	public Cart showCartById(@PathVariable("cid") Long CartId) throws ResourceNotFoundException {
		return cartService.showCartById(CartId);
	}

	@PutMapping("cart/{cid}")
	public Cart updateCartById(@PathVariable("cid") Long CartId, @RequestBody Cart Cart)
			throws ResourceNotFoundException {
		return cartService.updateCartById(CartId, Cart);
	}
	

	@DeleteMapping("cart/{cid}")
	public String deleteCartById(@PathVariable("cid") Long CartId) throws ResourceNotFoundException {
		cartService.deleteCartById(CartId);
		return "deleted the Cart";

	}

	

	@GetMapping("/orders")
	public List<OrderDto> getAllOrders() {
		return orderService.getAllOrders();
	}
	

	
	@PutMapping("/orders/{id}")
	public ResponseEntity<OrderDto> updateOrder(@PathVariable(value = "id") String orderId,
			@Valid @RequestBody OrderDto orderDetails) throws ResourceNotFoundException {

		return orderService.updateOrders(orderId, orderDetails);

	}

	
	@DeleteMapping("/orders/{orderId}")
	public ResponseEntity<?> deleteOrder(@PathVariable(value = "orderId") String orderId)
			throws ResourceNotFoundException {
		return orderService.deleteOrder(orderId);

	}
	
	
	
	@DeleteMapping("/orders/{orderId}/orderdetails")
	public ResponseEntity<?> deleteOrderDetails(@PathVariable(value = "orderId") Long orderId)
			throws ResourceNotFoundException {

		return orderdetailsService.deleteOrderDetails(orderId);

	}
	
	@PutMapping("/orders/orderdetails/{orderDetailsId}")
	public ResponseEntity<OrderDetailsDto> updateOrderDetails(
			@PathVariable(value = "orderDetailsId") Long orderDetailsId,
			@Valid @RequestBody OrderDetailsDto orderDetails) throws ResourceNotFoundException {

		return orderdetailsService.updateOrderDetails(orderDetailsId, orderDetails);
	}
}
