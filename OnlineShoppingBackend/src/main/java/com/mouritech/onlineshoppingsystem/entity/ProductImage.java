package com.mouritech.onlineshoppingsystem.entity;

import java.util.Arrays;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "product_image")
public class ProductImage {

	@Id
	@Column(name = "image_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long imageId;


    @NonNull
	@Column(name = "picByte", length = Integer.MAX_VALUE)
	private byte[] picByte;

	
	@OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
	@JoinColumn(name = "product_id")
	private Product product;


	@Override
	public String toString() {
		return "ProductImage [imageId=" + imageId + ", picByte=" + Arrays.toString(picByte) + ", product=" + product
				+ "]";
	}
	

}