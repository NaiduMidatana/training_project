package com.mouritech.onlineshoppingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.mouritech.onlineshoppingsystem.util.Constants;

@SpringBootApplication
@EnableJpaAuditing
public class OnlineShoppingSystemApplication {

	public static void main(String[] args) {
	SpringApplication.run(OnlineShoppingSystemApplication.class, args);
		
	}
   
}
