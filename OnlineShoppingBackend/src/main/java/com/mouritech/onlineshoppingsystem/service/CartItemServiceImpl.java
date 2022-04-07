package com.mouritech.onlineshoppingsystem.service;

import java.util.List;
import java.util.Random;

import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mouritech.onlineshoppingsystem.entity.CartItem;
import com.mouritech.onlineshoppingsystem.exception.ResourceNotFoundException;
import com.mouritech.onlineshoppingsystem.repository.CartItemRepository;
import com.mouritech.onlineshoppingsystem.repository.CartRepository;
import com.mouritech.onlineshoppingsystem.util.Constants;
@Service
public class CartItemServiceImpl implements CartItemService {
	
		@Autowired
		private CartItemRepository cartItemRepository;
		
		@Autowired
		private CartRepository cartRepository;
   
		@Autowired
		private Constants constants;
		
		@Override
		public CartItem insertCartItem(CartItem newCartItem) {
			
			return cartItemRepository.save(newCartItem);
		}

		

		@Override
		public List<CartItem> showAllCartItems() {
			// TODO Auto-generated method stub
			return cartItemRepository.findAll();
		}

		@Override
		public CartItem showCartItemById(Long itemId) throws ResourceNotFoundException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public ResponseEntity<CartItem> insertCartItembyCartId(Long cartId, CartItem newCartItem)throws ResourceNotFoundException {
		   CartItem cartItem = cartRepository.findByCartId(cartId).map(cart -> {
			
				
				newCartItem.setCart(cart);
				return cartItemRepository.save(newCartItem);
			}).orElseThrow(() -> new ResourceNotFoundException(constants.CART + cartId));
		return new ResponseEntity<CartItem>(newCartItem, HttpStatus.CREATED);
			}

		@Override
		public void removeFromCart(Long id) throws ResourceNotFoundException {
			if (cartItemRepository.existsById(id)){
				cartItemRepository.deleteById(id);
			} else {
				throw new  ResourceNotFoundException(constants.CART_ITEM + id );
			}
		}
		
		
		@Override
		public List<CartItem> findByCart_cartId(Long cartId) {
			return cartItemRepository.findByCart_CartId(cartId);
		}
		
}
