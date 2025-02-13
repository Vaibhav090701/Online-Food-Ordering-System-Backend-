package com.foodie.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodie.Model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
