package com.mouritech.onlineshoppingsystem.controller;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.mouritech.onlineshoppingsystem.entity.Category;
import com.mouritech.onlineshoppingsystem.repository.CategoryRepository;
import com.mouritech.onlineshoppingsystem.service.CategoryServiceImpl;



@SpringBootTest(classes=CategoryControllerTest.class)
public class CategoryControllerTest {
	 
	    @Mock
	    CategoryRepository categoryRepository;
	    @InjectMocks 
	    CategoryServiceImpl  categoryService;
	  
	    
	    @Test
	    @Order(1)
	    public void test_showAllCategorys() throws Exception {
	        List<Category> records = new ArrayList<>();
	        records.add(new Category("CAT123","Women"));
	        records.add(new Category("CAT223","Men"));
	        when(categoryRepository.findAll()).thenReturn(records);//Mocking
	       
	        assertEquals(2,((List<Category>) categoryService.showAllCategorys()).size());
	       
	    }
	
}
