package com.mouritech.onlineshoppingsystem.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mouritech.onlineshoppingsystem.dto.UserDto;
import com.mouritech.onlineshoppingsystem.entity.Cart;
import com.mouritech.onlineshoppingsystem.entity.Role;
import com.mouritech.onlineshoppingsystem.entity.User;
import com.mouritech.onlineshoppingsystem.exception.ResourceNotFoundException;
import com.mouritech.onlineshoppingsystem.mapper.Usermapper;
import com.mouritech.onlineshoppingsystem.repository.CartRepository;
import com.mouritech.onlineshoppingsystem.repository.RoleRepo;
//import com.mouritech.onlineshoppingsystem.repository.RoleRepo;
import com.mouritech.onlineshoppingsystem.repository.UserRepository;
//import com.mouritech.onlineshoppingsystem.entity.Role;
import com.mouritech.onlineshoppingsystem.util.Constants;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepository;
	@Autowired
	CartRepository cartRepository;
	@Autowired
	CartServiceImpl cartService;
	@Autowired
	private Constants constants;

	/**
	 * encoding password
	 * 
	 * @param password
	 * @return encoded password
	 */
	public String getEncodedPassword(String password) {
		return passwordEncoder.encode(password);
	}

	/**
	 * insert a new user
	 */
	@Override
	public ResponseEntity<?> insertUser(User user) {
		if (checkUserExistance(user.getUserName()))
			return new ResponseEntity<>("user already exists",HttpStatus.BAD_REQUEST);
		user.setUserId(generateuserId());
		Role role = roleRepository.findById(2).get();
		Set<Role> userRoles = new HashSet<>();
		Cart cart = new Cart();
		userRoles.add(role);
		user.setRoles(userRoles);
		user.setEnabled(true);
		user.setCart(cartService.insertCart(cart));
		user.setPassword(getEncodedPassword(user.getPassword()));
		userRepository.save(user);
		return ResponseEntity.ok().body(user);

	}

	/**
	 * check user exists or not
	 * 
	 * @param username
	 * @return true or false
	 */
	public boolean checkUserExistance(String username) {
		Optional<User> user1 = userRepository.findByUserName(username);
		if (!user1.isPresent()) {
			return false;
		} else {
			return true;
		}

	}

	/**
	 * generate user id by using random number method
	 * 
	 * @return
	 */
	public String generateuserId() {
		Random rand = new Random(); // instance of random class
		int upperbound = 255;
		// generate random values from 0-254
		Long cId = (long) rand.nextInt(upperbound);
		return "U" + cId;

	}

	/**
	 * show user by id
	 */
	@Override
	public User showUserById(String userId) throws ResourceNotFoundException {
		return userRepository.findByUserId(userId)
				.orElseThrow(() -> new ResourceNotFoundException(constants.USER + userId));
	}

	/**
	 * show all users
	 */
	@Override
	public List<User> showAllUsers() {
		return userRepository.findAll();
	}

	/**
	 * update user by id
	 */
	@Override
	public User updateUserById(String userId, User User) throws ResourceNotFoundException {
		User existingUser = userRepository.findByUserId(userId)
				.orElseThrow(() -> new ResourceNotFoundException(constants.USER + userId));
		existingUser.setUserName(User.getUserName());
		existingUser.setUserAddress(User.getUserAddress());
		existingUser.setUserEmail(User.getUserEmail());
		existingUser.setUserPhn(User.getUserPhn());
		userRepository.save(existingUser);
		return existingUser;
	}

	/**
	 * delete user by id
	 */
	@Override
	public String deleteUserById(String userId) throws ResourceNotFoundException {
		User existingUser = userRepository.findByUserId(userId)
				.orElseThrow(() -> new ResourceNotFoundException(constants.USER + userId));
		userRepository.delete(existingUser);
		return "user deleted";
	}


	/**
	 * login 
	 */
	@Override
	public ResponseEntity<?> login(UserDto userDto) {
		User user = userRepository.findByUserName(userDto.getUsername())
				.orElseThrow(()->new ResourceNotFoundException("invalid username"));
//		if (!user.isPresent())
//			return new ResponseEntity<>("invalid username",HttpStatus.BAD_REQUEST);
		String encryptPwd = user.getPassword();
		if (passwordEncoder.matches(userDto.getPassword(), encryptPwd)) {
			return ResponseEntity.ok().body(user);
		} else {
			return new ResponseEntity<>(constants.INVALID_CRED, HttpStatus.UNAUTHORIZED);
		}
	}

}
