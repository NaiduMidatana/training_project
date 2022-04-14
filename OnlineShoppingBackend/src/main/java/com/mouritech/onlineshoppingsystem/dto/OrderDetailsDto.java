package com.mouritech.onlineshoppingsystem.dto;



import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.mouritech.onlineshoppingsystem.entity.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class OrderDetailsDto {

	

	private Long orderDetailsId;

	@Min(100)
	private BigDecimal price;

	
	@Min(1)
	@Max(1000)
	private BigDecimal quantity;

	@Min(100)
	private BigDecimal totalPrice;


	@NotBlank(message = "Product Id required")
	private String prodId;


	@NotBlank(message = "Product Name is required")
	private String prodName;
	@NotBlank()
	private String comments;
	
	private OrderDto Order;
	



}

