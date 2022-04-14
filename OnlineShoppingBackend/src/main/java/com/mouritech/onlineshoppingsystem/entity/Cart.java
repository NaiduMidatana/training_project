package com.mouritech.onlineshoppingsystem.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data	
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "cart")
public class Cart {

	@Id
	@Column(name = "cart_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long cartId;
	
//
//
////	@OneToOne(cascade = CascadeType.ALL,mappedBy = "cart", fetch = FetchType.EAGER)	
//	private User user;
	
}
