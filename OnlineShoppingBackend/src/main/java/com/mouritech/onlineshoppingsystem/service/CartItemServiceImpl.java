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
		
		/**
		 * insert cart item
		 */
		@Override
		public CartItem insertCartItem(CartItem newCartItem) {
			return cartItemRepository.save(newCartItem);
		}

		/**
		 * show all cart items
		 */
		@Override
		public List<CartItem> showAllCartItems() {
			return cartItemRepository.findAll();
		}
		
        /**
         * show cart item by Id
         */
		@Override
		public CartItem showCartItemById(Long itemId) throws ResourceNotFoundException {
			return cartItemRepository.findById(itemId).orElseThrow(()-> new ResourceNotFoundException(constants.CART_ITEM + itemId));
		}

		/**
		 * insert cart item by cart Id
		 */
		@Override
		public ResponseEntity<CartItem> insertCartItembyCartId(Long cartId, CartItem newCartItem)throws ResourceNotFoundException {
		   CartItem cartItem = cartRepository.findByCartId(cartId).map(cart -> {
			
				
				newCartItem.setCart(cart);
				return cartItemRepository.save(newCartItem);
			}).orElseThrow(() -> new ResourceNotFoundException(constants.CART + cartId));
		return new ResponseEntity<CartItem>(newCartItem, HttpStatus.CREATED);
			}

		/**
		 * remove cart item from cart
		 */
		@Override
		public ResponseEntity<?> removeFromCart(Long id) throws ResourceNotFoundException {
			if (cartItemRepository.existsById(id)){
				cartItemRepository.deleteById(id);
				return new ResponseEntity<>("cartItem deleted", HttpStatus.OK);
			} else {
				throw new  ResourceNotFoundException(constants.CART_ITEM + id );
			}
		}
		
		/**
		 * find cart item by cart Id
		 */
		@Override
		public List<CartItem> findByCart_cartId(Long cartId) {
			return cartItemRepository.findByCart_CartId(cartId);
		}
		
}
