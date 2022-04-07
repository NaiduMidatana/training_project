package com.mouritech.onlineshoppingsystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mouritech.onlineshoppingsystem.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUserId(String userId);
	//List<User> findByUserName(String userId);
	Optional<User> findByUserName(String userName);
	Optional<User> findByUserEmailAndPassword(String email, String password);
	
	boolean existsUserByUserId(String userId);
	Optional<User>findByuserEmail(String email);
	User findByPassword(String password);
}
