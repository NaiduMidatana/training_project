package com.mouritech.onlineshoppingsystem.service;

import java.util.List;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.mouritech.onlineshoppingsystem.entity.Cart;

import com.mouritech.onlineshoppingsystem.exception.ResourceNotFoundException;

import com.mouritech.onlineshoppingsystem.repository.CartRepository;

import com.mouritech.onlineshoppingsystem.util.Constants;

@Service
public class CartServiceImpl implements CartService {
	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private Constants constants;

	/**
	 * create a cart
	 */
	@Override
	public Cart insertCart(Cart newCart) {
		return cartRepository.save(newCart);
	}

	/**
	 * show cart by Id
	 */
	@Override
	public Cart showCartById(Long cartId) throws ResourceNotFoundException {
		return cartRepository.findByCartId(cartId)
				.orElseThrow(() -> new ResourceNotFoundException(constants.CART + cartId));
	}

//	@Override
//	public Cart getCartByUserId(String userId) throws UserNotFoundException {
//		return cartRepository.findByUser_UserId(userId)
//				.orElseThrow(() -> new UserNotFoundException("User not found with id " + userId));
//	}

	/**
	 * show all carts in our application
	 */
	@Override
	public List<Cart> showAllCarts() {
		return cartRepository.findAll();
	}

	/**
	 * update cart by Id
	 */
	@Override
	public Cart updateCartById(Long cartId, Cart cart) throws ResourceNotFoundException{
		Cart existingCart = cartRepository.findByCartId(cartId)
				.orElseThrow(() -> new ResourceNotFoundException(constants.CART + cartId));
		cartRepository.save(existingCart);
		return existingCart;
	}

	/**
	 * delete cart by ID
	 */
	@Override
	public String deleteCartById(Long cartId) throws ResourceNotFoundException {
		Cart existingCart = cartRepository.findByCartId(cartId)
				.orElseThrow(() -> new ResourceNotFoundException(constants.CART + cartId));
		cartRepository.delete(existingCart);
		return "cart deleted";
	}

//	@Override
//	public Cart insertCartwithUserId(String userId, @Valid Cart newCart) throws CartNotFoundException {
//		return customerRepository.findByUserId(userId).map(cust -> {
//			newCart.setUser(cust);
//			newCart.setCartId(generateCartId());
//			return cartRepository.save(newCart);
//		}).orElseThrow(() -> new CartNotFoundException("Cart not found"));
//	}





}
