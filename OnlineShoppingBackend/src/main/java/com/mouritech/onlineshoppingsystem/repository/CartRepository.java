package com.mouritech.onlineshoppingsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mouritech.onlineshoppingsystem.entity.Cart;


@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

	Optional<Cart> findByCartId(Long cartId);

	//Optional<Cart> findByUser_UserId(Long custId) throws UserNotFoundException;

}
