package com.mouritech.onlineshoppingsystem.service;

import java.util.List;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mouritech.onlineshoppingsystem.entity.Order;
import com.mouritech.onlineshoppingsystem.exception.ResourceNotFoundException;
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
	private Constants constants;
	
	@Override
	public Order saveOrder(Order order) {
		
		order.setOrderId(generateOrderId());
		return orderRepository.save(order);
	}


	public String generateOrderId() {
		Random rand = new Random(); //instance of random class
	      int upperbound = 255;
	        //generate random values from 0-254
	      Long cId = (long) rand.nextInt(upperbound);
		return "O" + cId; 
	
	}
	
	@Override
	public List<Order> getAllOrders() {
		return  orderRepository.findAll();
	}


	@Override
	public  ResponseEntity<Order> updateOrders(String orderId, @Valid Order orderDetails) throws ResourceNotFoundException {
		  Order order = orderRepository.findByOrderId(orderId)
        .orElseThrow(() -> new ResourceNotFoundException(constants.ORDER + orderId));

		
		        order.setOrderedOn(orderDetails.getOrderedOn());
		        order.setOrderStatus(orderDetails.getOrderStatus());
			        final Order updatedOrder = orderRepository.save(order);
			        return ResponseEntity.ok(updatedOrder);

	}


	@Override
	public ResponseEntity<?> deleteOrder(String orderId) throws ResourceNotFoundException {
		
		return orderRepository.findByOrderId(orderId).map(order -> {
			orderRepository.delete(order);
		return ResponseEntity.ok().build();
		}).orElseThrow(()->new ResourceNotFoundException(constants.ORDER + orderId));
	}


	@Override
	public ResponseEntity<Order> getOrderById(String orderId) throws ResourceNotFoundException {
		 Order order = orderRepository.findByOrderId(orderId)
		        .orElseThrow(() -> new ResourceNotFoundException(constants.ORDER + orderId));
		      return ResponseEntity.ok().body(order);
	}
	
	@Override
	public ResponseEntity<Order> createOrder(String userId,Order newOrder) throws ResourceNotFoundException {
	
		Order order = userRepository.findByUserId(userId).map(
				user ->{
					newOrder.setUser(user);
					newOrder.setOrderId(generateOrderId());
					return orderRepository.save(newOrder);
				}).orElseThrow(()-> new ResourceNotFoundException(constants.USER  + userId));
		return new ResponseEntity<Order>(newOrder,HttpStatus.CREATED);
	}
	@Override
	public ResponseEntity<List<Order>> getAllOrdersByUserId(String userId) throws ResourceNotFoundException{
		if(!userRepository.existsUserByUserId(userId)) {
			throw new ResourceNotFoundException(constants.USER + userId);
		}
		List<Order> orders = orderRepository.findByUser_UserId(userId);
		return new ResponseEntity<List<Order>>(orders,HttpStatus.OK);
	}

}


	
