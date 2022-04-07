package com.mouritech.onlineshoppingsystem.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
import com.mouritech.onlineshoppingsystem.dto.UserDto;
import com.mouritech.onlineshoppingsystem.entity.Cart;
import com.mouritech.onlineshoppingsystem.entity.CartItem;
import com.mouritech.onlineshoppingsystem.entity.Category;
import com.mouritech.onlineshoppingsystem.entity.Order;
import com.mouritech.onlineshoppingsystem.entity.OrderDetails;
import com.mouritech.onlineshoppingsystem.entity.Product;
import com.mouritech.onlineshoppingsystem.entity.ProductImage;
import com.mouritech.onlineshoppingsystem.entity.User;
import com.mouritech.onlineshoppingsystem.exception.ResourceNotFoundException;
import com.mouritech.onlineshoppingsystem.service.CartItemService;
import com.mouritech.onlineshoppingsystem.service.CartService;
import com.mouritech.onlineshoppingsystem.service.CategoryService;
import com.mouritech.onlineshoppingsystem.service.OrderDetailsService;
import com.mouritech.onlineshoppingsystem.service.OrderService;
import com.mouritech.onlineshoppingsystem.service.ProductImageService;
import com.mouritech.onlineshoppingsystem.service.ProductService;
import com.mouritech.onlineshoppingsystem.service.UserService;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/user/")
public class UserController {

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

	@Autowired
	private OrderService orderService;
	
	///////////////////////////////////USER//////////////////////////////////////////////////////////
	//add user

	

	// get a user by id
	@GetMapping("get-user/{uid}")
	public User showUserById(@PathVariable("uid") String userId) throws ResourceNotFoundException {
		return userService.showUserById(userId);

	}
	//update a user by id
	@PutMapping("update-user/{uid}")
	public User updateUserById(@PathVariable("uid") String userId, @RequestBody User user)
			throws ResourceNotFoundException {
		return userService.updateUserById(userId, user);

	}
	@PostMapping("getemailandpasswordcheck")
	public ResponseEntity<?>checkUserEmailAndPassword(@RequestBody UserDto userDto){
		if(userDto==null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("please Enter Email and password");
		}
		else {
			
			ResponseEntity<?> ckeckLogin = userService.checkUserEmailAndPassword(userDto);
			return ckeckLogin;
			
		}
		
	}
	/////////////////////////////////////////////////Category////////////////////////////
	
	//get all category
	@GetMapping("get-all-Categorys")
	 	public ResponseEntity<List<Category>> getCategorys() {
		return categoryService.showAllCategorys();
	}
	//get category by category id
	@GetMapping("get-category/{cid}")
	public ResponseEntity<Category> showCatById(@PathVariable("cid") String catId) throws ResourceNotFoundException {
		return categoryService.showCatById(catId);

	}
///////////////////////////////////Product//////////////////////////////////////////////////////////
	
	// get product by cat- id and product name
	@GetMapping("get-products/{catid}/{productname}")
	public Product getCategory_CatIdByProdName(@PathVariable("catid") String catId,
			@PathVariable("productname") String prodName)
			throws ResourceNotFoundException {
		return productService.getCategory_CatIdByProdName(catId, prodName);
	}
////////Cart///////
	
	
	//get cart data by useromer id 
//	@GetMapping("/cart/{userId}/user")
//	public Cart getCartByUserId(@PathVariable("userId") String userId) throws UserNotFoundException {
//		return cartService.getCartByUserId(userId);
//	}
	//////////////////////////ProductImage///////////////////
	
	
	// get all images
//	@GetMapping("get-allproducts-details")
//	public List<ProductImage> getAllImages() {
//
//		return productimageService.getAllImages();
//
//	}

//////////////CartItem/////////
	

	
	//add cart items 
	@PostMapping("add-to-CartItem")
	public CartItem insertCartItem(@RequestBody CartItem newCartItem) {
		return cartitemService.insertCartItem(newCartItem);
	}
	
	// get Cart Item 
	@GetMapping("get-CartItems")
	public List<CartItem> showAllCarts() {
		return cartitemService.showAllCartItems();
	}
	
	//add cart item data by cart id 
	@PostMapping("add-CartItem/{cartId}/items")
	public ResponseEntity<CartItem> insertCartItembyCartId(@PathVariable(value = "cartId")Long cartId,
			@RequestBody CartItem newCartItem) throws ResourceNotFoundException {
		return cartitemService.insertCartItembyCartId(cartId, newCartItem);

	}
	//get cart items by cart id  
	@GetMapping("get-CartItem/{cartId}/items")
	public List<CartItem> getAllCartItemByOrderId(@PathVariable(value = "cartId") Long cartId) {
		return cartitemService.findByCart_cartId(cartId);
	}
	@DeleteMapping("remove-from-cart")
	public ResponseEntity<String> removeFromCart(@RequestParam Long id) {
		try {
			cartitemService.removeFromCart(id);
			return ResponseEntity.ok("Removed from cart successfully");
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}
	
	
	///////order////


	// save an order
	@PostMapping("save-order")
	public Order saveOrder(@Valid @RequestBody Order order) {
		return orderService.saveOrder(order);
	}
	//get order by customer id
	@GetMapping("get-orders/{customerid}")
	public ResponseEntity<List<Order>> getAllOrdersByUserId(@PathVariable("customerid") String userId)
			throws ResourceNotFoundException {
		return orderService.getAllOrdersByUserId(userId);
	}
	//add order by customer id
	@PostMapping("save-orders/{customerid}/customer")
	public ResponseEntity<Order> createOrder(@PathVariable("customerid") String userId, @RequestBody Order newOrder)
			throws ResourceNotFoundException {
		return orderService.createOrder(userId, newOrder);

	}
	///////Order details///////
	@Autowired
	private OrderDetailsService orderdetailsService;

	// get all orders details
	@GetMapping("get-orderdetails")
	public List<OrderDetails> getAllOrders() {
		return orderdetailsService.getAllOrderDetails();
	}
	// Post all orders details
		@PostMapping("save-orderdetails")
		public OrderDetails saveOrderDetails(@Valid @RequestBody OrderDetails orderdetails) {
			return orderdetailsService.saveOrderDetails(orderdetails);
		}

		// save order details with corresponded orderId
		@PostMapping("save-orderDetails/{orderId}/orderdetails")
		public OrderDetails insertOrderDetails(@PathVariable(value = "orderId") String orderId,
				@Valid @RequestBody OrderDetails newOrderDetails) throws ResourceNotFoundException {

			return orderdetailsService.insertOrderDetails(orderId, newOrderDetails);

		}
		// get all the order details by corresponding orderId
		@GetMapping("get-orders/{orderId}/orderdetails")
		public List<OrderDetails> getAllOrderDetailsByOrderId(@PathVariable(value = "orderId") String orderId) {
			return orderdetailsService.findByOrder_OrderId(orderId);
		}

		
		
}
