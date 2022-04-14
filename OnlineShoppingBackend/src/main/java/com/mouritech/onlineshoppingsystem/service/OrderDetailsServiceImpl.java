package com.mouritech.onlineshoppingsystem.service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mouritech.onlineshoppingsystem.dto.CartItemDto;
import com.mouritech.onlineshoppingsystem.dto.OrderDetailsDto;
import com.mouritech.onlineshoppingsystem.dto.OrderDto;
import com.mouritech.onlineshoppingsystem.entity.Order;
import com.mouritech.onlineshoppingsystem.entity.OrderDetails;
import com.mouritech.onlineshoppingsystem.exception.ResourceNotFoundException;
import com.mouritech.onlineshoppingsystem.mapper.OrderMapper;
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
	OrderMapper orderMapper;
	@Autowired
	private Constants constants;
	
	@Override
	public List<OrderDetailsDto> getAllOrderDetails() {
	List<OrderDetails> list = (List<OrderDetails>) orderdetailsRepository.findAll();
		
		return  list.stream().map(e->orderMapper.toOrderDetailsDTO(e)).collect(Collectors.toList());
		
	}

	@Override
	public OrderDetailsDto saveOrderDetails(OrderDetailsDto OrderDetails) {
		OrderDetails orderdetails = orderMapper.toOrderDetailsEntity(OrderDetails);
		
		OrderDetails orderCreated= orderdetailsRepository.save(orderdetails);
		//Order orderCreated = orderRepository.save(order);
		OrderDetailsDto resp=orderMapper. toOrderDetailsDTO(orderCreated);
		 return resp;
	}

//DTO not added

	@Override
	public OrderDetailsDto insertOrderDetails(String orderId, @Valid OrderDetailsDto newOrderDetails)
			throws ResourceNotFoundException {
		OrderDetails req = orderMapper.toOrderDetailsEntity(newOrderDetails);
		OrderDetails orderDetails= orderRepository.findByOrderId(orderId).map(order -> {
			req.setOrder(order);
			//newOrderDetails.setOrderDetailsId(generateOrderDetailsId());
			
			return orderdetailsRepository.save(req);
			
		}).orElseThrow(() -> new ResourceNotFoundException(constants.ORDER + orderId));
		OrderDetailsDto resp=orderMapper. toOrderDetailsDTO(orderDetails);
		return resp;
	}

	@Override
	public List<OrderDetailsDto> findByOrder_OrderId(String orderId) {
		List<OrderDetails> list = (List<OrderDetails>) orderdetailsRepository.findByOrder_OrderId(orderId);
	
		return  list.stream().map(e->orderMapper.toOrderDetailsDTO(e)).collect(Collectors.toList());
		
				 
	}

	
	
	@Override
	public ResponseEntity<?> deleteOrderDetails(Long orderDetailsId) throws ResourceNotFoundException {
		return orderdetailsRepository.findByOrderDetailsId(orderDetailsId).map(orderDetails -> {
			orderdetailsRepository.delete(orderDetails);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException(constants.ORDER_DETAILS + orderDetailsId));
	}

	@Override
	public ResponseEntity<OrderDetailsDto> updateOrderDetails(Long orderDetailsId, @Valid OrderDetailsDto orderDetails)
			throws ResourceNotFoundException {
		
		OrderDetails newOrderDetails = orderdetailsRepository.findByOrderDetailsId(orderDetailsId).orElseThrow(
				() -> new ResourceNotFoundException(constants.ORDER_DETAILS + orderDetailsId));
		  OrderDetails postRequest = orderMapper. toOrderDetailsEntity(orderDetails);
		  
		  postRequest.setComments(orderDetails.getComments());

		final OrderDetails updatedOrerDetails = orderdetailsRepository.save(newOrderDetails);
		  OrderDetailsDto postResponse = orderMapper.toOrderDetailsDTO(updatedOrerDetails);
		return ResponseEntity.ok(postResponse);
	}

}
