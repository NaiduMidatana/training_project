package com.mouritech.onlineshoppingsystem.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.mouritech.onlineshoppingsystem.dto.CartItemDto;
import com.mouritech.onlineshoppingsystem.dto.CategoryDto;
import com.mouritech.onlineshoppingsystem.dto.OrderDetailsDto;
import com.mouritech.onlineshoppingsystem.dto.OrderDto;
import com.mouritech.onlineshoppingsystem.dto.ProductDto;
import com.mouritech.onlineshoppingsystem.dto.ProductResponseDto;
import com.mouritech.onlineshoppingsystem.dto.UserDto;
import com.mouritech.onlineshoppingsystem.entity.CartItem;
import com.mouritech.onlineshoppingsystem.entity.Order;
import com.mouritech.onlineshoppingsystem.entity.OrderDetails;
import com.mouritech.onlineshoppingsystem.entity.Product;
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
	
	/////////////////////////////////////////////////Category////////////////////////////
	
	//get all category
	@GetMapping("get-all-Categorys")
	 	public ResponseEntity<List<CategoryDto>> getCategorys() {
		return categoryService.showAllCategorys();
	}
	//get category by category id
	@GetMapping("get-category/{cid}")
	public ResponseEntity<CategoryDto> showCatById(@PathVariable("cid") String catId) throws ResourceNotFoundException {
		return categoryService.showCatById(catId);

	}
///////////////////////////////////Product//////////////////////////////////////////////////////////
	
	// get product by cat- id and product name
	@GetMapping("get-products/{catid}/{productname}")
	public ProductResponseDto getCategory_CatIdByProdName(@PathVariable("catid") String catId,
			@PathVariable("productname") String prodName)
			throws ResourceNotFoundException {
		return productService.getCategory_CatIdByProdName(catId, prodName);
	}


//////////////CartItem/////////
	

	
	//add cart items 
	@PostMapping("add-to-CartItem")
	public CartItemDto insertCartItem(@RequestBody CartItemDto newCartItem) {
		return cartitemService.insertCartItem(newCartItem);
	}
	
	// get Cart Item 
	@GetMapping("get-CartItems")
	public List<CartItemDto> showAllCarts() {
		return cartitemService.showAllCartItems();
	}
	
	//add cart item data by cart id 
	@PostMapping("add-CartItem/{cartId}/items")
	public ResponseEntity<CartItemDto> insertCartItembyCartId(@PathVariable(value = "cartId")Long cartId,
			@RequestBody CartItemDto newCartItem) throws ResourceNotFoundException {
		return cartitemService.insertCartItembyCartId(cartId, newCartItem);

	}
	//get cart items by cart id  
	@GetMapping("get-CartItem/{cartId}/items")
	public List<CartItemDto> getAllCartItemByOrderId(@PathVariable(value = "cartId") Long cartId) {
		return cartitemService.findByCart_cartId(cartId);
	}
	@DeleteMapping("remove-from-cart")
	public ResponseEntity<?> removeFromCart(@RequestParam Long id) {
			return cartitemService.removeFromCart(id);
			
	}
	
	
	///////order////


	// save an order
	@PostMapping("save-order")
	public OrderDto saveOrder(@Valid @RequestBody OrderDto orderdto) {
		return orderService.saveOrder(orderdto);
	}
	//get order by customer id
	@GetMapping("get-orders/{customerid}")
	public ResponseEntity<List<OrderDto>> getAllOrdersByUserId(@PathVariable("customerid") String userId)
			throws ResourceNotFoundException {
		return orderService.getAllOrdersByUserId(userId);
	}
	//add order by customer id
	@PostMapping("save-orders/{customerid}/customer")
	public ResponseEntity<OrderDto> createOrder(@PathVariable("customerid") String userId, @RequestBody OrderDto newOrder)
			throws ResourceNotFoundException {
		return orderService.createOrder(userId, newOrder);

	}
	///////Order details///////
	@Autowired
	private OrderDetailsService orderdetailsService;

	// get all orders details
	@GetMapping("get-orderdetails")
	public List<OrderDetailsDto> getAllOrders() {
		return orderdetailsService.getAllOrderDetails();
	}
	// Post all orders details
		@PostMapping("save-orderdetails")
		public OrderDetailsDto saveOrderDetails( @RequestBody  @Valid OrderDetailsDto orderdetails) {
			return orderdetailsService.saveOrderDetails(orderdetails);
		}

		// save order details with corresponded orderId
		@PostMapping("save-orderDetails/{orderId}/orderdetails")
		public OrderDetailsDto insertOrderDetails(@PathVariable(value = "orderId") String orderId,
				@Valid @RequestBody OrderDetailsDto newOrderDetails) throws ResourceNotFoundException {

			return orderdetailsService.insertOrderDetails(orderId, newOrderDetails);

		}
		// get all the order details by corresponding orderId
		@GetMapping("get-orders/{orderId}/orderdetails")
		public List<OrderDetailsDto> getAllOrderDetailsByOrderId(@PathVariable(value = "orderId") String orderId) {
			return orderdetailsService.findByOrder_OrderId(orderId);
		}

		
		
}
