package com.foodie.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodie.Model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
	//it will return all orders of particular customer
	public List<Order> findByCustomerId(long userId);
	
	//it will return all orders of particular restaurant
	public List<Order> findByRestaurentId(long restaurentId);
	

}
