package com.foodie.Service;

import java.util.List;

import com.foodie.Model.IngredientCategory;
import com.foodie.Model.IngredientsItem;

public interface IngredientsService {
	
	//Below all method for IngredientCategory
	public IngredientCategory createIngredientCategory(String name, long restaurentId)throws Exception;
	
	public IngredientCategory findIngredientCategoryById(long id)throws Exception;
	
	public List<IngredientCategory> findIngredientCategoryByRestaurentId(long id)throws Exception;
	
	
	//Below all method for IngredientItems 
	public IngredientsItem createIngredientItem(long categoryId, String ingredientName, long restaurentId)throws Exception;
	
	public List<IngredientsItem> findRestaurentsIngredients(long restaurentId) throws Exception;
	
	public IngredientsItem updateIngredientStock(long id)throws Exception;
	

}
