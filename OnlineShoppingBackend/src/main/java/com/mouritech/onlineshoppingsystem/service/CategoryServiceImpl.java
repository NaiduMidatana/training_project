package com.mouritech.onlineshoppingsystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mouritech.onlineshoppingsystem.dto.CategoryDto;
import com.mouritech.onlineshoppingsystem.entity.Category;
import com.mouritech.onlineshoppingsystem.exception.ResourceNotFoundException;
import com.mouritech.onlineshoppingsystem.mapper.CategoryMapper;
import com.mouritech.onlineshoppingsystem.repository.CategoryRepository;
import com.mouritech.onlineshoppingsystem.util.Constants;


@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	CategoryMapper catMapper;
	@Autowired
	private Constants constants;
	
	/**
	 * insert category
	 */
	@Override
	public ResponseEntity<CategoryDto> insertCategory(CategoryDto catDto) {
		
		Category category = catMapper.toCatEntity(catDto);
		category.setCatId(generateCatId());
		Category newcategory=categoryRepository.save(category);
		return new ResponseEntity<>(catMapper.toCatDTO(newcategory), HttpStatus.CREATED);
	}
	

	/**
	 * Generate category id by using random number
	 * @return category id in string format.
	 */
	public String generateCatId() {
		Random rand = new Random(); // instance of random class
		int upperbound = 255;
		// generate random values from 0-254
		Long cId = (long) rand.nextInt(upperbound);
		return "C" + cId;

	}

    /**
     * show category by Id
     */

	@Override
	public ResponseEntity<CategoryDto> showCatById(String catId) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		Category category = categoryRepository.findByCatId(catId)
				.orElseThrow(() -> new ResourceNotFoundException(constants.CATEGORY + catId));
		CategoryDto catdto = catMapper.toCatDTO(category);
	return new ResponseEntity<>(catdto,HttpStatus.OK);
	}


	/**
	 * show all categories
	 */

	@Override
	public ResponseEntity<List<CategoryDto>> showAllCategorys() {
		List<Category> categories = new ArrayList<Category>();
		categoryRepository.findAll().forEach(categories::add);
		if (categories.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>( categories.stream().map(e->catMapper.toCatDTO(e)).collect(Collectors.toList()), HttpStatus.OK);

	}
	

	/**
	 * update category by id
	 */

	@Override
	public ResponseEntity<CategoryDto> updateCatById(String catId, Category category) throws ResourceNotFoundException {
		Category existingCategory = categoryRepository.findByCatId(catId)
				.orElseThrow(() -> new ResourceNotFoundException(constants.CATEGORY + catId));
		existingCategory.setCatName(category.getCatName());
		categoryRepository.save(existingCategory);
		CategoryDto categorydto = catMapper.toCatDTO(existingCategory);
		return new ResponseEntity<>(categorydto ,HttpStatus.OK);
	}

    
	/**
	 * delete category by id

	 */
	@Override
	public ResponseEntity<?> deleteCatById(String catId) throws ResourceNotFoundException {
		Category existingCategory = categoryRepository.findByCatId(catId)
				.orElseThrow(() -> new ResourceNotFoundException(constants.CATEGORY + catId));
		categoryRepository.delete(existingCategory);
		return new ResponseEntity<>("deleted",HttpStatus.OK);
		
	}
	

}
