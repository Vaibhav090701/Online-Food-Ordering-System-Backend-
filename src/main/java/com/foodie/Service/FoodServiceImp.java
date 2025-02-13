package com.foodie.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodie.Model.Category;
import com.foodie.Model.Food;
import com.foodie.Model.Restaurent;
import com.foodie.Repository.FoodRepository;
import com.foodie.request.CreateFoodRequest;

@Service
public class FoodServiceImp implements FoodService {

	@Autowired
	private FoodRepository foodRepository;
	
	@Override
	public Food createFood(CreateFoodRequest req, Category category, Restaurent restaurent) {
		
		Food food=new Food();
		food.setName(req.getName());
		food.setDescription(req.getDescription());
		food.setImages(req.getImages());
		food.setFoodCategory(category);
		food.setPrice(req.getPrice());
		food.setRestaurent(restaurent);
		food.setIngredients(req.getIngredients());
		food.setVegetarian(req.isVagetrian());
		food.setSeasonal(req.isSeasional());
	
		Food savedFood=foodRepository.save(food);
		restaurent.getFoods().add(savedFood);
		
		return savedFood;
	}

	@Override
	public void deleteFood(long foodId) throws Exception {
		//this food don't have any restaurant so it wont appear in search or in restaurant but it is present in our database
		Food food=findFoodById(foodId);
		food.setRestaurent(null);
		foodRepository.save(food);
		
	}

	@Override
	public List<Food> getRestaurentFood(long restaurentId, boolean isVeg, boolean isNonVeg, boolean isSeasonal,
			String foodCategory) {
		List<Food> foods= foodRepository.findByRestaurentId(restaurentId);
		
		if(isVeg)
		{
			foods=filterByisVeg(foods,isVeg);
		}
		if(isNonVeg)
		{
			foods=filtetByIsNonVeg(foods, isNonVeg);
		}
		if(isSeasonal)
		{
			foods=filterByisSeasonal(foods, isSeasonal);
		}
		if(foodCategory!=null || foodCategory.equals(""))
		{
			foods=filterByfoodCategory(foods,foodCategory);
		}
		return foods;
	}

	private List<Food> filterByfoodCategory(List<Food> foods, String foodCategory) {		
		return foods.stream().filter(food ->{
			if(food.getFoodCategory()!=null)
			{
				return food.getFoodCategory().getName().equals(foodCategory);
			}
			return false;
		}).collect(Collectors.toList());
	}

	private List<Food> filterByisSeasonal(List<Food> foods, boolean isSeasonal) {
		return foods.stream().filter(food ->food.isSeasonal()==isSeasonal).collect(Collectors.toList());
	}

	private List<Food> filtetByIsNonVeg(List<Food> foods, boolean isNonVeg) {
		return foods.stream().filter((food -> food.isVegetarian()==false)).collect(Collectors.toList());
	}

	private List<Food> filterByisVeg(List<Food> foods, boolean isVeg) {
		return foods.stream().filter((food -> food.isVegetarian()==isVeg)).collect(Collectors.toList());
	}
	
	@Override
	public List<Food> searchFood(String keyword) {
		return foodRepository.searchFood(keyword);
	}

	@Override
	public Food findFoodById(long foodId) throws Exception {
		Optional<Food> optFood= foodRepository.findById(foodId);
		
		if(optFood.isEmpty())
		{
			throw new Exception("Food not found by id "+foodId);
		}
		return optFood.get();
	}

	@Override
	public Food updateAvailabilityStatus(long foodId) throws Exception {
		Food food=findFoodById(foodId);
		food.setAvailable(!food.isAvailable());
		return foodRepository.save(food);
	}
}

