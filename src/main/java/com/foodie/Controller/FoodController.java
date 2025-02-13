
package com.foodie.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foodie.Model.Food;
import com.foodie.Model.User;
import com.foodie.Service.FoodService;
import com.foodie.Service.RestaurentService;
import com.foodie.Service.UserService;

@RestController
@RequestMapping("/api/food")
public class FoodController {

	@Autowired
	private FoodService foodService;
	
	@Autowired
	private UserService userService;
	
	private RestaurentService restaurentService;
	
	@GetMapping("/search")
	public ResponseEntity<List<Food>>searchFood(@RequestParam String keyword, @RequestHeader("Authorization")String jwt) throws Exception
	{
		User user=userService.findUserByJwtToken(jwt);
		List<Food>foodList= foodService.searchFood(keyword);
		return new ResponseEntity<>(foodList, HttpStatus.FOUND);
		
	}
	
	@GetMapping("/restaurent/{restaurentId}")
	public ResponseEntity<List<Food>>getRestaurentFood(@RequestParam boolean isVegetarian,
			@RequestParam boolean isNonVegetarian,
			@RequestParam boolean isSeasonal,
			@RequestParam (required = false)String foodCategory,
			@PathVariable long restaurentId,
			@RequestHeader("Authorization")String jwt) throws Exception
	{
		User user=userService.findUserByJwtToken(jwt);
		List<Food> foods= foodService.getRestaurentFood(restaurentId, isVegetarian, isNonVegetarian, isSeasonal, foodCategory);
		return new ResponseEntity<>(foods, HttpStatus.FOUND);
	}

}
