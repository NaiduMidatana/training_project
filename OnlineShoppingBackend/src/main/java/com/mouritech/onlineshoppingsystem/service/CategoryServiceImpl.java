package com.mouritech.onlineshoppingsystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mouritech.onlineshoppingsystem.entity.Category;
import com.mouritech.onlineshoppingsystem.exception.ResourceNotFoundException;
import com.mouritech.onlineshoppingsystem.repository.CategoryRepository;
import com.mouritech.onlineshoppingsystem.util.Constants;


@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private Constants constants;
	
	/**
	 * insert category
	 */
	@Override
	public ResponseEntity<Category> insertCategory(Category newCategory) {
		newCategory.setCatId(generateCatId());
		categoryRepository.save(newCategory);
		return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
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
	public ResponseEntity<Category> showCatById(String catId) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		Category category = categoryRepository.findByCatId(catId)
				.orElseThrow(() -> new ResourceNotFoundException(constants.CATEGORY + catId));
	return new ResponseEntity<>(category,HttpStatus.OK);
	}

	/**
	 * show all categories
	 */
	@Override
	public ResponseEntity<List<Category>> showAllCategorys() {
		List<Category> categories = new ArrayList<Category>();
		categoryRepository.findAll().forEach(categories::add);
		if (categories.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}
    
	/**
	 * update category by id
	 */
	@Override
	public ResponseEntity<Category> updateCatById(String catId, Category category) throws ResourceNotFoundException {
		Category existingCategory = categoryRepository.findByCatId(catId)
				.orElseThrow(() -> new ResourceNotFoundException(constants.CATEGORY + catId));
		existingCategory.setCatName(category.getCatName());
		categoryRepository.save(existingCategory);
		return new ResponseEntity<>(existingCategory,HttpStatus.OK);
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
