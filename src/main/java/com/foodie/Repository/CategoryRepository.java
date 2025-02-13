package com.foodie.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodie.Model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	
	public List<Category>findByRestaurentId(long id);

}
