package com.mouritech.onlineshoppingsystem.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mouritech.onlineshoppingsystem.dto.CartItemDto;
import com.mouritech.onlineshoppingsystem.entity.CartItem;
import com.mouritech.onlineshoppingsystem.entity.OrderDetails;
import com.mouritech.onlineshoppingsystem.exception.ResourceNotFoundException;
import com.mouritech.onlineshoppingsystem.mapper.CartItemMapper;
import com.mouritech.onlineshoppingsystem.repository.CartItemRepository;
import com.mouritech.onlineshoppingsystem.repository.CartRepository;
import com.mouritech.onlineshoppingsystem.util.Constants;
@Service
public class CartItemServiceImpl implements CartItemService {
	@Autowired
	private Constants constants;
		@Autowired
		private CartItemRepository cartItemRepository;
		
		@Autowired
		private CartRepository cartRepository;
		@Autowired
		CartItemMapper itemMapper;
		
		
		@Override
		public CartItemDto insertCartItem(CartItemDto newCartItem) {
			CartItem req =itemMapper.toCartItemEntity(newCartItem);
			 CartItem newItem =cartItemRepository.save(req);
			 CartItemDto Resp=itemMapper.toCartItemDTO(newItem);
			return Resp;
		}

		/**
		 * 
		 */

		@Override
		public List<CartItemDto> showAllCartItems() {
			List<CartItem> list = (List<CartItem>) cartItemRepository.findAll();
			
			return  list.stream().map(e->itemMapper.toCartItemDTO(e)).collect(Collectors.toList());
			
				
			// TODO Auto-generated method stub
			
		}
		/**
		 * 
		 */
		@Override
		public CartItem showCartItemById(Long itemId) throws ResourceNotFoundException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public ResponseEntity<CartItemDto> insertCartItembyCartId(Long cartId, CartItemDto newCartItem)throws ResourceNotFoundException {
			CartItem req =itemMapper.toCartItemEntity(newCartItem);
			CartItem cartItem = cartRepository.findByCartId(cartId).map(cart -> {
			
				
				req.setCart(cart);
				return cartItemRepository.save(req);
			}).orElseThrow(() -> new ResourceNotFoundException(constants.CART + cartId));
			 CartItemDto Resp=itemMapper.toCartItemDTO(cartItem);
		return new ResponseEntity<CartItemDto>(Resp, HttpStatus.CREATED);
			}
		/**
		 * 
		 */
		@Override
		public void removeFromCart(Long id) throws ResourceNotFoundException {
			if (cartItemRepository.existsById(id)){
				cartItemRepository.deleteById(id);
			} else {
				throw new  ResourceNotFoundException(constants.CART_ITEM + id );
			}
		}
		
		/**
		 * 
		 */
		@Override
		public List<CartItemDto> findByCart_cartId(Long cartId) {
			List<CartItem> list = (List<CartItem>) cartItemRepository.findByCart_CartId(cartId);
			return  list.stream().map(e->itemMapper.toCartItemDTO(e)).collect(Collectors.toList());
		}
	
		
}
