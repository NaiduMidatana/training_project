package com.mouritech.onlineshoppingsystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mouritech.onlineshoppingsystem.dto.CategoryDto;
import com.mouritech.onlineshoppingsystem.dto.OrderDto;
import com.mouritech.onlineshoppingsystem.entity.Category;
import com.mouritech.onlineshoppingsystem.entity.Order;
import com.mouritech.onlineshoppingsystem.exception.ResourceNotFoundException;
import com.mouritech.onlineshoppingsystem.mapper.OrderMapper;
import com.mouritech.onlineshoppingsystem.repository.OrderRepository;
import com.mouritech.onlineshoppingsystem.repository.UserRepository;
import com.mouritech.onlineshoppingsystem.util.Constants;


@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	OrderMapper orderMapper;
	@Autowired
	private Constants constants;
	
	@Override
	public OrderDto saveOrder(OrderDto orderdto) {
		Order order = orderMapper.toOrderEntity(orderdto);
		order.setOrderId(generateOrderId());
		Order orderCreated = orderRepository.save(order);
		OrderDto resp=orderMapper.toOrderDTO(orderCreated);
		 return resp;
		
	}


	public String generateOrderId() {
		Random rand = new Random(); //instance of random class
	      int upperbound = 255;
	        //generate random values from 0-254
	      Long cId = (long) rand.nextInt(upperbound);
		return "O" + cId; 
	
	}
	
	@Override
	public List<OrderDto> getAllOrders() {
		List<Order> list = (List<Order>) orderRepository.findAll();
		
		return  list.stream().map(e->orderMapper.toOrderDTO(e)).collect(Collectors.toList());
	}


	@Override
	public  ResponseEntity<OrderDto> updateOrders(String orderId, @Valid OrderDto orderDto) throws ResourceNotFoundException {
		  Order order = orderRepository.findByOrderId(orderId)
        .orElseThrow(() -> new ResourceNotFoundException(constants.ORDER + orderId));
		  Order postRequest = orderMapper. toOrderEntity(orderDto);
		
		  postRequest.setOrderedOn(orderDto.getOrderedOn());
		  postRequest.setOrderStatus(orderDto.getOrderStatus());
			        final Order updatedOrder = orderRepository.save(order);
			        OrderDto postResponse = orderMapper.toOrderDTO(updatedOrder);
			        return ResponseEntity.ok(postResponse);

	}


	@Override
	public ResponseEntity<?> deleteOrder(String orderId) throws ResourceNotFoundException {
		
		return orderRepository.findByOrderId(orderId).map(order -> {
			orderRepository.delete(order);
		return ResponseEntity.ok().build();
		}).orElseThrow(()->new ResourceNotFoundException(constants.ORDER + orderId));
	}


	@Override
	public ResponseEntity<OrderDto> getOrderById(String orderId) throws ResourceNotFoundException {
		 Order order = orderRepository.findByOrderId(orderId)
		        .orElseThrow(() -> new ResourceNotFoundException(constants.ORDER + orderId));
		 OrderDto postResponse = orderMapper.toOrderDTO(order);
		      return ResponseEntity.ok().body(postResponse);
	}
	
	@Override
	public ResponseEntity<OrderDto> createOrder(String userId,OrderDto newOrder) throws ResourceNotFoundException {
		Order ordercreated = orderMapper.toOrderEntity(newOrder);
		Order order = userRepository.findByUserId(userId).map(
				user ->{
					ordercreated.setUser(user);
					ordercreated.setOrderId(generateOrderId());
					return orderRepository.save(ordercreated);
				}).orElseThrow(()-> new ResourceNotFoundException(constants.USER  + userId));
		OrderDto resp=orderMapper.toOrderDTO(order);
		return new ResponseEntity<OrderDto>(resp,HttpStatus.CREATED);
	}
	@Override

	public ResponseEntity<List<OrderDto>> getAllOrdersByUserId(String userId) throws ResourceNotFoundException{
		if(!userRepository.existsUserByUserId(userId)) {
			throw new ResourceNotFoundException(constants.USER + userId);
		}
		List<Order> orders = orderRepository.findByUser_UserId(userId);
		
		return new ResponseEntity<List<OrderDto>>(orders.stream().map(e->orderMapper.toOrderDTO(e)).collect(Collectors.toList()), HttpStatus.OK);
	}
	
}


	
