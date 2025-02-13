package com.foodie.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodie.Model.Category;
import com.foodie.Model.Food;
import com.foodie.Model.Restaurent;
import com.foodie.Model.User;
import com.foodie.Response.MessageResponse;
import com.foodie.Service.FoodService;
import com.foodie.Service.RestaurentService;
import com.foodie.Service.UserService;
import com.foodie.request.CreateFoodRequest;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {
	
	@Autowired
	private FoodService foodService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RestaurentService restaurentService;
	
	@PostMapping()
	public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest req,@RequestHeader("Authorization")String jwt) throws Exception
	{
		User user=userService.findUserByJwtToken(jwt);
		Restaurent restaurent=restaurentService.findRestaurentById(req.getResturentId());
		Category category=req.getCategory();
		
		Food food=foodService.createFood(req, category, restaurent);
		return new ResponseEntity<>(food, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<MessageResponse> deleteFood(@PathVariable long id,@RequestHeader("Authorization")String jwt) throws Exception
	{
		User user=userService.findUserByJwtToken(jwt);
		foodService.deleteFood(id);
		MessageResponse res=new MessageResponse();
		res.setMessage("Food item deleted successfully...");
		return new ResponseEntity<>(res,HttpStatus.GONE);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Food> updateFoodAvailability(@PathVariable long id,@RequestHeader("Authorization")String jwt) throws Exception
	{
		User user=userService.findUserByJwtToken(jwt);
		Food food= foodService.updateAvailabilityStatus(id);
		return new ResponseEntity<>(food,HttpStatus.OK);
		
	}



}
