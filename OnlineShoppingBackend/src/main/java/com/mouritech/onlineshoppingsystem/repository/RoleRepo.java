package com.mouritech.onlineshoppingsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mouritech.onlineshoppingsystem.entity.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, String> {

	Optional<Role> findById(int i);

}
