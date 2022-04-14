package com.mouritech.onlineshoppingsystem.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mouritech.onlineshoppingsystem.dto.OrderDetailsDto;
import com.mouritech.onlineshoppingsystem.dto.OrderDto;
import com.mouritech.onlineshoppingsystem.entity.Order;
import com.mouritech.onlineshoppingsystem.entity.OrderDetails;
@Component
public class OrderMapper {

	
		@Autowired
		private ModelMapper modelMapper;
		public Order toOrderEntity(OrderDto orderDto) {
			return modelMapper.map(orderDto, Order.class);
		}
		
		public OrderDto toOrderDTO(Order order) {
			return modelMapper.map(order, OrderDto.class);
		}
		
		public OrderDetails toOrderDetailsEntity(OrderDetailsDto orderDetailsDto) {
			return modelMapper.map(orderDetailsDto, OrderDetails.class);
		}
		
		public OrderDetailsDto toOrderDetailsDTO(OrderDetails orderDetails) {
			return modelMapper.map(orderDetails, OrderDetailsDto.class);
		}
//		public OrderDetails toOrderSetailsEntity(OrderDetailsDto orderDto) {
//			return modelMapper.map(orderDto, OrderDetails.class);
//		}
//		
//		public OrderDetailsResponseDto toOrderDetailsDTO(OrderDetails orderDetails) {
//			OrderDetailsResponseDto detailsDto = modelMapper.map( orderDetails, OrderDetailsResponseDto.class);
//			
//			detailsDto.setUserId(orderDetails.getOrder().getUser().getUserId());
//			detailsDto.setUserName(orderDetails.getOrder().getUser().getUserName());
//			detailsDto.setOrderId(orderDetails.getOrder().getOrderId());
//			detailsDto.setOrderDate(orderDetails.getOrder().getOrderedOn());
//			
//					
//			return detailsDto;
//			
//		}

		
	
}
