package com.mouritech.onlineshoppingsystem.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.mouritech.onlineshoppingsystem.dto.OrderDetailsDto;
import com.mouritech.onlineshoppingsystem.entity.OrderDetails;
import com.mouritech.onlineshoppingsystem.exception.ResourceNotFoundException;

public interface OrderDetailsService {

	OrderDetailsDto insertOrderDetails(String orderId, @Valid OrderDetailsDto newOrderDetails)
			throws ResourceNotFoundException;

	List<OrderDetailsDto> findByOrder_OrderId(String orderId);

	ResponseEntity<?> deleteOrderDetails(Long orderDetailsId) throws ResourceNotFoundException;

	ResponseEntity<OrderDetailsDto> updateOrderDetails(Long orderDetailsId, @Valid OrderDetailsDto orderDetails)
			throws ResourceNotFoundException;

	List<OrderDetailsDto> getAllOrderDetails();

	OrderDetailsDto saveOrderDetails(@Valid OrderDetailsDto orderdetails);


}
