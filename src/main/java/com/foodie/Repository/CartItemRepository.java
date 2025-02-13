package com.foodie.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodie.Model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
