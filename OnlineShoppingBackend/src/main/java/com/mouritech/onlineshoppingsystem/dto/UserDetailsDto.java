package com.mouritech.onlineshoppingsystem.dto;


import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.mouritech.onlineshoppingsystem.entity.Cart;
import com.mouritech.onlineshoppingsystem.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserDetailsDto {
	
	    private String userId;

	    @NotEmpty
		@Size(min=2, message="User name should have at least 2 characters")
		private String userName;
	    
	 
	    @Email(message = "Not a valid email")
		private String userEmail;
	    

	    @NotBlank(message = "Contact Number is required")
	    @Size(min = 10, max = 10,message = "Invalid Number")
		private String userPhn;
	    

	    @NotEmpty
		private String userAddress;
	   
	    @NotEmpty
		@Size(min=8, message="Password should have at least 8 characters")
		private String password;
	   
	    private boolean enabled;
	 

	
	    private Set<Role> roles = new HashSet<>();
	    
	    private Cart cart;
		
				
		
	}


