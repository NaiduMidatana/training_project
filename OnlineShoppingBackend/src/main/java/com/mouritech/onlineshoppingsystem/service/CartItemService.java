package com.mouritech.onlineshoppingsystem.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mouritech.onlineshoppingsystem.entity.CartItem;

import com.mouritech.onlineshoppingsystem.exception.ResourceNotFoundException;

public interface CartItemService {

	CartItem showCartItemById(Long itemId) throws ResourceNotFoundException;

	CartItem insertCartItem(CartItem newCartItem);

	List<CartItem> showAllCartItems();

	ResponseEntity<CartItem> insertCartItembyCartId(Long cartId, CartItem newCartItem) throws ResourceNotFoundException;

	List<CartItem> findByCart_cartId(Long cartId);

	void removeFromCart(Long id) throws ResourceNotFoundException;
}
