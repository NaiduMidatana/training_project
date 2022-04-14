package com.mouritech.onlineshoppingsystem.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mouritech.onlineshoppingsystem.dto.CategoryDto;
import com.mouritech.onlineshoppingsystem.entity.Category;


@Component
public class CategoryMapper {
	@Autowired
	private ModelMapper modelMapper;
	public Category toCatEntity(CategoryDto catDTO) {
		return modelMapper.map(catDTO, Category.class);
	}
	
	public CategoryDto toCatDTO(Category cat) {
		return modelMapper.map(cat, CategoryDto.class);
	}

	
}


