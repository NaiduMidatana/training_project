package com.mouritech.onlineshoppingsystem.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import com.mouritech.onlineshoppingsystem.entity.OrderDetails;
import com.mouritech.onlineshoppingsystem.exception.ResourceNotFoundException;

public interface OrderDetailsService {

	OrderDetails insertOrderDetails(String orderId, @Valid OrderDetails newOrderDetails)
			throws ResourceNotFoundException;

	List<OrderDetails> findByOrder_OrderId(String orderId);

	ResponseEntity<?> deleteOrderDetails(Long orderDetailsId) throws ResourceNotFoundException;

	ResponseEntity<OrderDetails> updateOrderDetails(Long orderDetailsId, @Valid OrderDetails orderDetails)
			throws ResourceNotFoundException;

	List<OrderDetails> getAllOrderDetails();

	OrderDetails saveOrderDetails(OrderDetails OrderDetails);


}
