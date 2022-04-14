package com.mouritech.onlineshoppingsystem.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mouritech.onlineshoppingsystem.dto.UserDetailsDto;
import com.mouritech.onlineshoppingsystem.dto.UserDto;
import com.mouritech.onlineshoppingsystem.dto.UserResponseDto;
import com.mouritech.onlineshoppingsystem.entity.Cart;
import com.mouritech.onlineshoppingsystem.entity.Role;
import com.mouritech.onlineshoppingsystem.entity.User;
import com.mouritech.onlineshoppingsystem.exception.ResourceNotFoundException;

import com.mouritech.onlineshoppingsystem.mapper.UserMapper;
import com.mouritech.onlineshoppingsystem.repository.CartRepository;
import com.mouritech.onlineshoppingsystem.repository.RoleRepo;
//import com.mouritech.onlineshoppingsystem.repository.RoleRepo;
import com.mouritech.onlineshoppingsystem.repository.UserRepository;
//import com.mouritech.onlineshoppingsystem.entity.Role;
import com.mouritech.onlineshoppingsystem.util.Constants;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

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
	public ResponseEntity<?> insertUser(UserDetailsDto userDto) {
		if (checkUserExistance(userDto.getUserName()))

			return new ResponseEntity<>("user already exists",HttpStatus.NO_CONTENT);
		
		User user = userMapper.toUserDetailsEntity(userDto);
		Cart cart=new Cart();

		
		
		Role role = roleRepository.findById(2).get();
		Set<Role> userRoles = new HashSet<>();
		userRoles.add(role);
		user.setRoles(userRoles);
		user.setEnabled(true);
	
		user.setCart(cartService.insertCart(cart));
		user.setPassword(getEncodedPassword(user.getPassword()));
		user.setUserId(generateuserId());
		userRepository.save(user);
		 UserResponseDto userRespDto  =userMapper.convertEntityToDto(user);
		//System.out.println(userDto);
		return ResponseEntity.ok().body(userRespDto);

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
	public boolean checkEmailExistance(String email) {
		Optional<User> user1 = userRepository.findByUserEmail(email);
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
		Optional<User> user = userRepository.findByUserName(userDto.getUsername());
		if (!user.isPresent())
			return ResponseEntity.ok().body("username is invalid");
		String encryptPwd = user.get().getPassword();
		if (passwordEncoder.matches(userDto.getPassword(), encryptPwd)) {
//			String role = user.get(0).
			//userMapper.userResponseDto.setCartId(user.getCart().getCartId());
//			userResponseDto.setUserName(user.get().getUserName());
//			userResponseDto.setUserId(user.get().getUserEmail());
//			userResponseDto.setRole(user.get().get);
			UserResponseDto userRespDto  =userMapper.convertEntityToDto(user.get());
			return ResponseEntity.ok().body(userRespDto);
		} else {
			return ResponseEntity.ok().body(constants.INVALID_CRED);
		}
	}

}
