package com.mouritech.onlineshoppingsystem.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.mouritech.onlineshoppingsystem.entity.Order;
import com.mouritech.onlineshoppingsystem.exception.ResourceNotFoundException;


public interface OrderService {

	Order saveOrder(Order order);

	List<Order> getAllOrders();

	ResponseEntity<Order> updateOrders(String orderId, @Valid Order orderDetails) throws ResourceNotFoundException;

	ResponseEntity<?> deleteOrder(String orderId) throws ResourceNotFoundException ;

	ResponseEntity<Order> getOrderById(String orderId) throws ResourceNotFoundException;
	//
	ResponseEntity<List<Order>> getAllOrdersByUserId(String userId) throws ResourceNotFoundException;



	ResponseEntity<Order> createOrder(String userId, Order newOrder) throws ResourceNotFoundException;

}
