package com.mouritech.onlineshoppingsystem.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mouritech.onlineshoppingsystem.dto.CartItemDto;
import com.mouritech.onlineshoppingsystem.entity.CartItem;

import com.mouritech.onlineshoppingsystem.exception.ResourceNotFoundException;

public interface CartItemService {

	CartItem showCartItemById(Long itemId) throws ResourceNotFoundException;

	CartItemDto insertCartItem(CartItemDto newCartItem);

	List<CartItemDto> showAllCartItems();

	ResponseEntity<CartItemDto> insertCartItembyCartId(Long cartId, CartItemDto newCartItem) throws ResourceNotFoundException;

	List<CartItemDto> findByCart_cartId(Long cartId);

	void removeFromCart(Long id) throws ResourceNotFoundException;
}
