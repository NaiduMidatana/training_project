package com.mouritech.onlineshoppingsystem.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mouritech.onlineshoppingsystem.entity.User;
import com.mouritech.onlineshoppingsystem.repository.UserRepository;



@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepo.findByUserName(username);
		user.orElseThrow(() -> new UsernameNotFoundException(username + "not found."));
		return user.map(MyUserDetails::new).get();
	}
	
	

}
