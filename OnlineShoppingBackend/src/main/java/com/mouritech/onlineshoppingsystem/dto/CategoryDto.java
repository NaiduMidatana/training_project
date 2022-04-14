package com.mouritech.onlineshoppingsystem.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {	

		private String catId;
		@NotBlank(message = "Category Name is required")
		private String catName;	
}
