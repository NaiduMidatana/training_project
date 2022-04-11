package com.mouritech.onlineshoppingsystem.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import com.mouritech.onlineshoppingsystem.entity.Cart;
import com.mouritech.onlineshoppingsystem.entity.Category;
import com.mouritech.onlineshoppingsystem.entity.Order;
import com.mouritech.onlineshoppingsystem.entity.OrderDetails;
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

	/**
	 * add new category
	 * @param newCategory
	 * @return insert category method response from category service.
	 */
	@PostMapping("add-category")
		public ResponseEntity<Category> insertCategory(@RequestBody Category newCategory) {
		return categoryService.insertCategory(newCategory);
	}
	

	/**
	 * update category
	 * @param catId
	 * @param category
	 * @return update category response from category service.
	 * @throws ResourceNotFoundException
	 */
	@PutMapping("update-category/{cid}")
	public ResponseEntity<Category> updateCatById(@PathVariable("cid") String catId, @RequestBody Category category)
			throws ResourceNotFoundException {
		return categoryService.updateCatById(catId, category);

	}
	
	/**
	 * delete category by id
	 * @param catId
	 * @return item deleted 
	 * @throws ResourceNotFoundException
	 */
	@DeleteMapping("delete-category/{cid}")
	public ResponseEntity<?> deleteCatById(@PathVariable("cid") String catId) throws ResourceNotFoundException {
		return categoryService.deleteCatById(catId);
		
	}

	
	/**
	 * get products by product id
	 * @param prodId
	 * @return response
	 * @throws ResourceNotFoundException
	 */
	@GetMapping("get-product/{pid}")
	public Product showProductById(@PathVariable("pid") String prodId) throws ResourceNotFoundException {
		return productService.showProductById(prodId);

	}
	
	/**
	 * update product by product id
	 * @param prodId
	 * @param product
	 * @return response
	 * @throws ResourceNotFoundException
	 */
	@PutMapping("update-product/{pid}")
	public Product updateProductById(@PathVariable("pid") String prodId, @RequestBody Product product)
			throws ResourceNotFoundException {
		return productService.updateProductById(prodId, product);

	}
	
	/**
	 * delete product by product id
	 * @param prodId
	 * @return response
	 * @throws ResourceNotFoundException
	 */
	@DeleteMapping("delete-product/{pid}")
	public String deleteProductById(@PathVariable("pid") String prodId) throws ResourceNotFoundException {
		return productService.deleteProductById(prodId);
	}

	
	// add product by cat id 
	/**
	 * add product by category id
	 * @param catid
	 * @param newProduct
	 * @return response
	 * @throws ResourceNotFoundException
	 */
	@PostMapping("add-product/{catid}/category")
	public ResponseEntity<Product> createProduct(@PathVariable("catid") String catid, @RequestBody Product newProduct)
			throws ResourceNotFoundException {
		return productService.createProduct(catid, newProduct);

	}
	
	/**
	 * add image for corresponding product
	 * @param prodId
	 * @param file
	 * @return response
	 * @throws IOException
	 */
	@PostMapping("add-image/{prodId}/prodImage")
	public Optional<ProductImage> addImage(@PathVariable(value = "prodId") String prodId,
			@RequestParam("prodImage") MultipartFile file) throws ResourceNotFoundException {
		return productimageService.addImage(prodId, file);
	}


	
	/**
	 * update corresponding image by product id
	 * @param prodId
	 * @param file
	 * @return update image method response
	 * @throws ResourceNotFoundException
	 */
	@PutMapping("update-image/{prodId}/prodImages")
	public Optional<Object> updateImage(@PathVariable(value = "prodId") String prodId,
			@RequestParam("prodImage") MultipartFile file) throws ResourceNotFoundException {
		return productimageService.updateImage(prodId, file);

	}
 
	/**
	 * delete image for corresponding product
	 * @param prodId
	 * @param imageId
	 * @return image deleted 
	 * @throws ResourceNotFoundException
	 */
	@DeleteMapping("delete-image/{prodId}/prodImages/{imageId}")
	public ResponseEntity<?> deleteImage(@PathVariable(value = "prodId") String prodId,
			@PathVariable(value = "imageId") Long imageId) throws ResourceNotFoundException {
		return productimageService.deleteImage(prodId, imageId);

	}

	
	/**
	 * delete a user by id
	 * @param userId
	 * @return user deleted
	 * @throws ResourceNotFoundException
	 */
	@DeleteMapping("delete-user/{uid}")
	public String deleteUserById(@PathVariable("uid") String userId) throws ResourceNotFoundException {
		return userService.deleteUserById(userId);
	}
	

	/**
	 * get all users details
	 * @return users list
	 */
	@GetMapping("/get-user")
	public List<User> showAllUsers() {
		return userService.showAllUsers();

	}

	/**
	 * insert cart
	 * @param newCart
	 * @return cart inserted
	 */
	@PostMapping("cart")
	public Cart insertCart(@RequestBody Cart newCart) {
		return cartService.insertCart(newCart);

	}

	/**
	 * get all carts
	 * @return carts list
	 */
	@GetMapping("carts")
	public List<Cart> showAllCarts() {
		return cartService.showAllCarts();
	}
	
	/**
	 * show cart by cart id
	 * @param CartId
	 * @return cart response
	 * @throws ResourceNotFoundException
	 */
	@GetMapping("cart/{cid}")
	public Cart showCartById(@PathVariable("cid") Long CartId) throws ResourceNotFoundException {
		return cartService.showCartById(CartId);
	}

	/**
	 * update cart by cart id
	 * @param CartId
	 * @param Cart
	 * @return update cart response
	 * @throws ResourceNotFoundException
	 */
	@PutMapping("cart/{cid}")
	public Cart updateCartById(@PathVariable("cid") Long CartId, @RequestBody Cart Cart)
			throws ResourceNotFoundException {
		return cartService.updateCartById(CartId, Cart);
	}

	/**
	 * delete cart by cart id
	 * @param CartId
	 * @return delete cart by id response
	 * @throws ResourceNotFoundException
	 */
	@DeleteMapping("cart/{cid}")
	public String deleteCartById(@PathVariable("cid") Long CartId) throws ResourceNotFoundException {
		return cartService.deleteCartById(CartId);
	}
	

	/**
	 * get all orders
	 * @return orders list
	 */
	@GetMapping("/orders")
	public List<Order> getAllOrders() {
		return orderService.getAllOrders();
	}

	/**
	 * update orders
	 * @param orderId
	 * @param orderDetails
	 * @return update order response
	 * @throws ResourceNotFoundException
	 */
	@PutMapping("/orders/{id}")
	public ResponseEntity<Order> updateOrder(@PathVariable(value = "id") String orderId,
			@Valid @RequestBody Order orderDetails) throws ResourceNotFoundException {

		return orderService.updateOrders(orderId, orderDetails);

	}

	/**
	 * delete order by order id
	 * @param orderId
	 * @return response
	 * @throws ResourceNotFoundException
	 */
	@DeleteMapping("/orders/{orderId}")
	public ResponseEntity<?> deleteOrder(@PathVariable(value = "orderId") String orderId)
			throws ResourceNotFoundException {
		return orderService.deleteOrder(orderId);

	}
	
	//////orderDetails////
	/**
	 * delete order details by corresponding orderId
	 * @param orderId
	 * @return response
	 * @throws ResourceNotFoundException
	 */
	@DeleteMapping("/orders/{orderId}/orderdetails")
	public ResponseEntity<?> deleteOrderDetails(@PathVariable(value = "orderId") Long orderId)
			throws ResourceNotFoundException {

		return orderdetailsService.deleteOrderDetails(orderId);

	}
	
	/**
	 * update order details by order details id 
	 * @param orderDetailsId
	 * @param orderDetails
	 * @return update order details response
	 * @throws ResourceNotFoundException
	 */
	@PutMapping("/orders/orderdetails/{orderDetailsId}")
	public ResponseEntity<OrderDetails> updateOrderDetails(
			@PathVariable(value = "orderDetailsId") Long orderDetailsId,
			@Valid @RequestBody OrderDetails orderDetails) throws ResourceNotFoundException {
		return orderdetailsService.updateOrderDetails(orderDetailsId, orderDetails);
	}
}
