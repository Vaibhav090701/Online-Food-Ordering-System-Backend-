package com.foodie.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodie.Model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
	
	public Cart findByCustomerId(long customerId);

}
