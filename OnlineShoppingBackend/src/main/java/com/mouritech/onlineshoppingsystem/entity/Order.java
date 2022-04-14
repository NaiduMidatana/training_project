package com.mouritech.onlineshoppingsystem.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

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
import org.springframework.data.annotation.LastModifiedDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Orders")
public class Order {

	@Id
	@Column(name = "order_id", length = 64)
	private String orderId;
	
	@Column(name = "amount")
	@Min(100)
	@Max(100000)
	private BigDecimal amount;
	
	@Column(name = "shipping_address")
	@NotBlank(message = "Address is required")
	private String  shippingAddress;
	
	@Column(name = "ordered_on")
	@LastModifiedDate
	private LocalDate orderedOn;
	
	@Column(name = "ordered_status")
	@NotBlank(message = "Status is required")
	private String orderStatus;

	@ManyToOne(cascade = CascadeType.ALL,optional = false,fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "USER_ID")
	private User user;



}
