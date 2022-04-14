package com.mouritech.onlineshoppingsystem.entity;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.CascadeType;
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
@Table(name = "order_details")
public class OrderDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "orderDetails_id")
	private Long orderDetailsId;

	@Column(name = "unit_price", nullable = false)
	@Min(100)
	private BigDecimal price;

	@Column(name = "quantity")
	@Min(1)
	@Max(1000)
	private BigDecimal quantity;

	@Column(name = "total_price")
	@Min(100)
	private BigDecimal totalPrice;

	@Column(name = "prodId")
	@NotBlank(message = "Product Id required")
	private String prodId;

	@Column(name = "prodName")
	@NotBlank(message = "Product Name is required")
	private String prodName;

	@Column(name = "comments")
	@NotBlank()
	private String comments;

	@ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "orderId")
	private Order order;



}
