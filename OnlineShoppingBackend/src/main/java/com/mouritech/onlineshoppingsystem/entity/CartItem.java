package com.mouritech.onlineshoppingsystem.entity;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cart_Items")
public class CartItem {

	@Id
	@Column(name = "Item_Id")
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long itemId;
	
	
	
	
	@Column(name = "Product_Id")
	@NotBlank(message = "Product Id is required")
	private String prodId;
	
	@Column(name = "Name")
	@NotBlank(message = "Product Name is required")
	private String pname;

	@Column(name = "Product_Price", nullable = false)
	
	@Min(100)
	@Max(100000)
	private BigDecimal price;

	@Column(name = "Quantity", nullable = false)
	@Min(1)
	@Max(1000)
	private int qty;
	
	@Column(name = "Total_Amount", nullable = false)
	@Min(1)
	private BigDecimal totalAmount;

	@ManyToOne( optional = false, fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "CartId")
	private Cart cart;

	

	
}
