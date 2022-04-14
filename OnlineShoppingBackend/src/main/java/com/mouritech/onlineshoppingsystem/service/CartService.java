package com.mouritech.onlineshoppingsystem.service;

import java.util.List;
import com.mouritech.onlineshoppingsystem.entity.Cart;
import com.mouritech.onlineshoppingsystem.exception.ResourceNotFoundException;


public interface CartService {

	Cart insertCart(Cart newCart);

	Cart showCartById(Long cartId) throws ResourceNotFoundException;

	List<Cart> showAllCarts();

	Cart updateCartById(Long cartId, Cart cart) throws ResourceNotFoundException;

	//Cart insertCartwithUserId(Long userId, Cart newCart);

	//Cart getCartByUserId(Long userId) throws UserNotFoundException;

	String deleteCartById(Long cartId);

	

	

}
