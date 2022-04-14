package com.mouritech.onlineshoppingsystem.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mouritech.onlineshoppingsystem.dto.UserDetailsDto;
import com.mouritech.onlineshoppingsystem.dto.UserDto;
import com.mouritech.onlineshoppingsystem.entity.User;
import com.mouritech.onlineshoppingsystem.exception.ResourceNotFoundException;


public interface UserService {
	ResponseEntity<?> insertUser(UserDetailsDto newUser);

	User showUserById(String userId) throws ResourceNotFoundException;

	List<User> showAllUsers();


	//boolean findUserByEmailAndPassword(String email, String password);

	ResponseEntity<?> checkUserEmailAndPassword(UserDto userDto);
	User updateUserById(String userId, User User) throws ResourceNotFoundException;

	void deleteUserById(String userId) throws ResourceNotFoundException;

	ResponseEntity<?> login(UserDto userDto);

	//ResponseEntity<User> getUserByEmailAndPassword(String email, String password) throws UserNotFoundException;

	

}
