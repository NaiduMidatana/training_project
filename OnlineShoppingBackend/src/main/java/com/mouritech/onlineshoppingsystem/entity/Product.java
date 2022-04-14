package com.mouritech.onlineshoppingsystem.entity;

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
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Products")
public class Product {
	@Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "product_id", length = 64)
	private String prodId;
	
	 
	@Column(name = "Name", length = 255, nullable = false)
	@NotBlank(message = "Product Name is required")
	private String prodName;

	@Column(name = "Price", nullable = false)
	@NotNull
	@Min(1)
	private double price;
	
	@NotBlank(message = "Description is required")
	@Column(name = "Description", nullable = false)
	private String description;
	
	@Column(name = "available_quantity", nullable = false)
	@NotNull
	@Min(1)
	@Max(1000)
	private int availableQuantity;

	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "category_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Category category;

	
	

}
