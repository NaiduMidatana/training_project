package com.mouritech.onlineshoppingsystem.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mouritech.onlineshoppingsystem.dto.CartItemDto;
import com.mouritech.onlineshoppingsystem.entity.CartItem;
@Component
public class CartItemMapper {
	@Autowired
	private ModelMapper modelMapper;
	public CartItem toCartItemEntity(CartItemDto itemDTO) {
		return modelMapper.map(itemDTO, CartItem.class);
	}
	
	public CartItemDto toCartItemDTO(CartItem item) {
		return modelMapper.map(item, CartItemDto.class);
	}

}
