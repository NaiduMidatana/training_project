package com.mouritech.onlineshoppingsystem.dto;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.mouritech.onlineshoppingsystem.entity.Cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDto {

    private Long itemId;
	
	
	

	@NotBlank(message = "Product Id is required")
	private String prodId;
	

	@NotBlank(message = "Product Name is required")
	private String pname;


	
	@Min(100)
	@Max(100000)
	private BigDecimal price;

	@Min(1)
	@Max(1000)
	private int qty;
	

	@Min(1)
	private BigDecimal totalAmount;

	private Cart cart;

}
