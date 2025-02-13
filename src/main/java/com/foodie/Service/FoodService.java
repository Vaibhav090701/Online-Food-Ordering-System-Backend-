package com.foodie.Service;

import java.util.List;

import com.foodie.Model.Category;
import com.foodie.Model.Food;
import com.foodie.Model.Restaurent;
import com.foodie.request.CreateFoodRequest;

public interface FoodService {
	
	public Food createFood(CreateFoodRequest req, Category category, Restaurent restaurent);
	
	public void deleteFood(long foodId)throws Exception;
	
	public List<Food>getRestaurentFood(long restaurentId, boolean isVeg, boolean isNonVeg, boolean isSeasonal, String foodCategory);
	
	public List<Food>searchFood(String keyword);
	
	public Food findFoodById(long foodId) throws Exception;
	
	public Food updateAvailabilityStatus(long foodId)throws Exception;
	

}
