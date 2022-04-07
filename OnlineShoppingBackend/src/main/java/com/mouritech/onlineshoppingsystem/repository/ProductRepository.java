package com.mouritech.onlineshoppingsystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.mouritech.onlineshoppingsystem.entity.Product;

@Repository
public  interface ProductRepository extends JpaRepository<Product, Long> {
	
	Optional<Product> findByProdId(String prodId);
	List<Product> findByProdName(String prodId);
	List<Product> findByCategory(String catid);
	Optional<Product> findByProdNameAndCategory(String catid, String productname);
	List<Product> findByCategory_CatId(String catid);

	Optional<Product> findByCategory_CatIdAndProdName(String catid, String productname);
	
	void deleteByProdId(String prodId);
	
	

}



