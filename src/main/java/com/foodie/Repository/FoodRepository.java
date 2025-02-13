package com.foodie.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.foodie.Model.Food;

public interface FoodRepository extends JpaRepository<Food, Long> {
	
	public List<Food> findByRestaurentId(long restaurentId);
	
	@Query("SELECT f FROM Food f WHERE f.name LIKE %:keyword% OR f.foodCategory.name LIKE %:keyword%")
	public List<Food>searchFood(@Param("keyword")String keyword);
	

}
