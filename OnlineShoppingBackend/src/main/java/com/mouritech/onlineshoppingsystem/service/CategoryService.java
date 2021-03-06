package com.mouritech.onlineshoppingsystem.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mouritech.onlineshoppingsystem.dto.CategoryDto;
import com.mouritech.onlineshoppingsystem.entity.Category;
import com.mouritech.onlineshoppingsystem.exception.ResourceNotFoundException;


public interface CategoryService {
	ResponseEntity<CategoryDto> insertCategory(CategoryDto catDto);

	ResponseEntity<CategoryDto> showCatById(String catId) throws ResourceNotFoundException;

	ResponseEntity<List<CategoryDto>> showAllCategorys();

	ResponseEntity<CategoryDto> updateCatById(String catId, Category category) throws ResourceNotFoundException;

	ResponseEntity<?> deleteCatById(String catId) throws ResourceNotFoundException;

}
