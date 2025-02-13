package com.foodie.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodie.Model.IngredientCategory;
import com.foodie.Model.IngredientsItem;
import com.foodie.Service.IngredientsService;
import com.foodie.Service.RestaurentService;
import com.foodie.Service.UserService;
import com.foodie.request.IngredientCategoryRequest;
import com.foodie.request.IngredientItemRequest;

@RestController
@RequestMapping("/api/admin/ingredients")
public class IngredientController {
	
	@Autowired
	private IngredientsService ingredientsService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RestaurentService restaurentService;
	
	@PostMapping("/category")
	public ResponseEntity<IngredientCategory>createIngredientCategory(@RequestBody IngredientCategoryRequest categoryRequest) throws Exception
	{
		IngredientCategory category=ingredientsService.createIngredientCategory(categoryRequest.getName(), categoryRequest.getRestaurentId());
		
		return new ResponseEntity<>(category,HttpStatus.CREATED);
	}
	
	@PostMapping()
	public ResponseEntity<IngredientsItem>createIngredientsItem(@RequestBody IngredientItemRequest categoryRequest)throws Exception
	{
		IngredientsItem ingredientsItem=ingredientsService.createIngredientItem(categoryRequest.getCategoryId(),categoryRequest.getName(), categoryRequest.getRestaurentId());
		
		return new ResponseEntity<>(ingredientsItem,HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}/stock")
	public ResponseEntity<IngredientsItem>updateIngredientStock(@PathVariable long id)throws Exception
	{
		IngredientsItem item= ingredientsService.updateIngredientStock(id);
		
		return new ResponseEntity<>(item,HttpStatus.OK);
	}
	
	@GetMapping("/restaurent/{id}")
	public ResponseEntity<List<IngredientsItem>>getAllIngredientOfRestaurent(@PathVariable long id)throws Exception
	{
		List<IngredientsItem> item= ingredientsService.findRestaurentsIngredients(id);
		
		return new ResponseEntity<>(item,HttpStatus.OK);
	}
	
	@GetMapping("/restaurent/{id}/category")
	public ResponseEntity<List<IngredientCategory>>getAllIngredientCategoryOfRestaurent(@PathVariable long id)throws Exception
	{
		List<IngredientCategory> item= ingredientsService.findIngredientCategoryByRestaurentId(id);
		
		return new ResponseEntity<>(item,HttpStatus.OK);
	}



	
	

}
