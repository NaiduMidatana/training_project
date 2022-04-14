package com.mouritech.onlineshoppingsystem.mapper;


import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mouritech.onlineshoppingsystem.dto.UserDetailsDto;
import com.mouritech.onlineshoppingsystem.dto.UserDto;
import com.mouritech.onlineshoppingsystem.dto.UserResponseDto;
import com.mouritech.onlineshoppingsystem.entity.Role;
import com.mouritech.onlineshoppingsystem.entity.User;
@Component
public class UserMapper {
		
	@Autowired
	ModelMapper modelMapper;
				
		public User toUserEntity(UserDto userDto) {
			return modelMapper.map(userDto, User.class);
		}
		
		public UserDto toUserDTO(User user) {
			return modelMapper.map(user, UserDto.class);}
		

			public User toUserDetailsEntity(UserDetailsDto userDto) {
				return modelMapper.map(userDto, User.class);
			}
			
			public UserDetailsDto toUserDetailsDTO(User user) {
				return modelMapper.map(user, UserDetailsDto.class);
				}
			
			public UserResponseDto convertEntityToDto(User user)
			{
				UserResponseDto detailsDto = modelMapper.map(user, UserResponseDto.class);
				detailsDto.setCartId(user.getCart().getCartId());
				
				List<String> roles = new ArrayList<String>();
				
				for(Role role: user.getRoles()) {
					roles.add(role.getName());
				}
				
				detailsDto.setRoles(roles);
				return detailsDto;
			}
			
			public User convertDtoToEntity(UserDetailsDto userDto)
			{
				return modelMapper.map(userDto,User.class);
			}

			
	
}


