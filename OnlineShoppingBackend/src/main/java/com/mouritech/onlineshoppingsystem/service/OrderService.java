package com.mouritech.onlineshoppingsystem.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.mouritech.onlineshoppingsystem.dto.OrderDto;
import com.mouritech.onlineshoppingsystem.entity.Order;
import com.mouritech.onlineshoppingsystem.exception.ResourceNotFoundException;


public interface OrderService {

	OrderDto saveOrder(OrderDto orderDto);

	List<OrderDto> getAllOrders();

	ResponseEntity<OrderDto> updateOrders(String orderId, @Valid OrderDto orderDetailsDto) throws ResourceNotFoundException;

	ResponseEntity<?> deleteOrder(String orderId) throws ResourceNotFoundException ;

	ResponseEntity<OrderDto> getOrderById(String orderId) throws ResourceNotFoundException;
	//
	ResponseEntity<List<OrderDto>> getAllOrdersByUserId(String userId) throws ResourceNotFoundException;



	ResponseEntity<OrderDto> createOrder(String userId, OrderDto newOrder) throws ResourceNotFoundException;

}
