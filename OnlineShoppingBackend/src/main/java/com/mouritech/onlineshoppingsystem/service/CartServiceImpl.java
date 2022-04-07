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

	@Override
	public Cart insertCart(Cart newCart) {
	
		return cartRepository.save(newCart);
	}

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

	@Override
	public List<Cart> showAllCarts() {
		return cartRepository.findAll();
	}

	@Override
	public Cart updateCartById(Long cartId, Cart cart) throws ResourceNotFoundException{
		Cart existingCart = cartRepository.findByCartId(cartId)
				.orElseThrow(() -> new ResourceNotFoundException(constants.CART + cartId));
		cartRepository.save(existingCart);
		return existingCart;
	}

	@Override
	public void deleteCartById(Long cartId) throws ResourceNotFoundException {
		Cart existingCart = cartRepository.findByCartId(cartId)
				.orElseThrow(() -> new ResourceNotFoundException(constants.CART + cartId));
		cartRepository.delete(existingCart);
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
