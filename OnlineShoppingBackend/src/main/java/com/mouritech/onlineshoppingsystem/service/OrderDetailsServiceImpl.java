package com.mouritech.onlineshoppingsystem.service;

import java.util.List;
import java.util.Random;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mouritech.onlineshoppingsystem.entity.OrderDetails;
import com.mouritech.onlineshoppingsystem.exception.ResourceNotFoundException;
import com.mouritech.onlineshoppingsystem.repository.OrderDetailsRepository;
import com.mouritech.onlineshoppingsystem.repository.OrderRepository;
import com.mouritech.onlineshoppingsystem.util.Constants;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {

	@Autowired
	private OrderDetailsRepository orderdetailsRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private Constants constants;
	
	/**
	 * get all order details 
	 */
	@Override
	public List<OrderDetails> getAllOrderDetails() {
		return orderdetailsRepository.findAll();
	}
 
	/**
	 * save order details
	 */
	@Override
	public OrderDetails saveOrderDetails(OrderDetails OrderDetails) {
		return orderdetailsRepository.save(OrderDetails);
	}

	/**
	 * insert order details
	 */
	@Override
	public OrderDetails insertOrderDetails(String orderId, @Valid OrderDetails newOrderDetails)
			throws ResourceNotFoundException {
		return orderRepository.findByOrderId(orderId).map(order -> {
			newOrderDetails.setOrder(order);
			//newOrderDetails.setOrderDetailsId(generateOrderDetailsId());
			return orderdetailsRepository.save(newOrderDetails);
		}).orElseThrow(() -> new ResourceNotFoundException(constants.ORDER + orderId));
	}

	/**
	 * find order details by order id
	 */
	@Override
	public List<OrderDetails> findByOrder_OrderId(String orderId) {
		return orderdetailsRepository.findByOrder_OrderId(orderId);
	}

	/**
	 * delete order details by id
	 */
	@Override
	public ResponseEntity<?> deleteOrderDetails(Long orderDetailsId) throws ResourceNotFoundException {
		return orderdetailsRepository.findByOrderDetailsId(orderDetailsId).map(orderDetails -> {
			orderdetailsRepository.delete(orderDetails);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException(constants.ORDER_DETAILS + orderDetailsId));
	}

	/**
	 * update order details by id
	 */
	@Override
	public ResponseEntity<OrderDetails> updateOrderDetails(Long orderDetailsId, @Valid OrderDetails orderDetails)
			throws ResourceNotFoundException {
		OrderDetails newOrderDetails = orderdetailsRepository.findByOrderDetailsId(orderDetailsId).orElseThrow(
				() -> new ResourceNotFoundException(constants.ORDER_DETAILS + orderDetailsId));

		newOrderDetails.setComments(orderDetails.getComments());

		final OrderDetails updatedOrerDetails = orderdetailsRepository.save(newOrderDetails);
		return ResponseEntity.ok(updatedOrerDetails);
	}

}
