package com.foodie.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodie.Model.IngredientCategory;

public interface IngredientCategoryRepository extends JpaRepository<IngredientCategory, Long> {
	
	public List<IngredientCategory>findByRestaurentId(long id);

}
