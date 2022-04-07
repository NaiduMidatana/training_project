package com.mouritech.onlineshoppingsystem.mapper;


import com.mouritech.onlineshoppingsystem.dto.UserDto;
import com.mouritech.onlineshoppingsystem.entity.User;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class Usermapper {
	
	
		
		public class CustomeMapper{
	@Autowired
	ModelMapper modelMapper;
		
		public UserDto convertEntityToDto(User user)
		{
			return modelMapper.map(user, UserDto.class);
		}
		
		public User convertDtoToEntity(UserDto userDto)
		{
			return modelMapper.map(userDto,User.class);
		}

	}
}


