package com.foodie.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodie.Model.IngredientsItem;

public interface IngredientItemsRepository extends JpaRepository<IngredientsItem, Long> {

	List<IngredientsItem>findByRestaurentId(long id);
}
