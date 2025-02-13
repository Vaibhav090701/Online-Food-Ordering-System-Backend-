package com.foodie.Service;

import java.util.List;

import com.foodie.Model.Category;

public interface CategoryService {
	
	public Category createCategory(String name, long userId) throws Exception;
	
	public List<Category> findCategoryByRestaurentId(long id) throws Exception;
	
	public Category findCategoryById(long id)throws Exception;
	
	public void deleteCategory(long id) throws Exception;
	
	public Category updateCategory(long id) throws Exception;

}
