package com.mouritech.onlineshoppingsystem.customanotation;


	
	import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mouritech.onlineshoppingsystem.repository.UserRepository;

	@Component
	public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

	  @Autowired
	  UserRepository userRepository;
	    @Override
	    public void initialize(UniqueUsername constraintAnnotation ) {
	    	
	    }
		   

	    @Override
	    public boolean isValid(String  userName, ConstraintValidatorContext context) {
	        if(userRepository==null) {
	        	return true;
	        }
	    	return (userRepository.findByUserName( userName)==null);
	    }
	}

