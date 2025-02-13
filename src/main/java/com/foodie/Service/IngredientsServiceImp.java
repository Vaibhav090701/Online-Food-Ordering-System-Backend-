package com.foodie.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodie.Model.IngredientCategory;
import com.foodie.Model.IngredientsItem;
import com.foodie.Model.Restaurent;
import com.foodie.Repository.IngredientCategoryRepository;
import com.foodie.Repository.IngredientItemsRepository;

@Service
public class IngredientsServiceImp implements IngredientsService {
		
	@Autowired
	private RestaurentService restaurentService;
	
	@Autowired
	private IngredientCategoryRepository ingredientCategoryRepository;
	
	@Autowired
	private IngredientItemsRepository ingredientItemsRepository;

	@Override
	public IngredientCategory createIngredientCategory(String name, long restaurentId) throws Exception {
		Restaurent restaurent=restaurentService.findRestaurentById(restaurentId);
		List<IngredientsItem>ingredientsItems= findRestaurentsIngredients(restaurentId);
		
		if(restaurent==null)
		{
			throw new Exception("Restaurent not found by id "+restaurentId);
		}
		
		IngredientCategory ingredientCategory=new IngredientCategory();
		ingredientCategory.setName(name);
		ingredientCategory.setRestaurent(restaurent);
		ingredientCategory.setIngredientsItems(ingredientsItems);
		
		return ingredientCategoryRepository.save(ingredientCategory);
	}

	@Override
	public IngredientCategory findIngredientCategoryById(long id) throws Exception {
		Optional<IngredientCategory>optIngredient= ingredientCategoryRepository.findById(id);
		
		if(optIngredient.isEmpty())
		{
			throw new Exception("Ingredient category not found by id "+id);
		}
		return optIngredient.get();
	}

	@Override
	public List<IngredientCategory> findIngredientCategoryByRestaurentId(long id) throws Exception {
		
		restaurentService.findRestaurentById(id);
		
		return ingredientCategoryRepository.findByRestaurentId(id);
	}

	@Override
	public IngredientsItem createIngredientItem(long categoryId, String ingredientName, long restaurentId)
			throws Exception {
		Restaurent restaurent=restaurentService.findRestaurentById(restaurentId);
		IngredientCategory ingredientCategory=findIngredientCategoryById(categoryId);
		
		IngredientsItem item=new IngredientsItem();
		item.setName(ingredientName);
		item.setRestaurent(restaurent);
		item.setCategory(ingredientCategory);
		
		IngredientsItem ingredientsItem=ingredientItemsRepository.save(item);
		ingredientCategory.getIngredientsItems().add(ingredientsItem);
		
		return ingredientsItem;
	}

	@Override
	public List<IngredientsItem> findRestaurentsIngredients(long restaurentId) {
		
		return 	ingredientItemsRepository.findByRestaurentId(restaurentId);

	}

	@Override
	public IngredientsItem updateIngredientStock(long id) throws Exception {
		Optional<IngredientsItem> ingredientsItem=ingredientItemsRepository.findById(id);
		
		if(ingredientsItem.isEmpty())
		{
			throw new Exception("Inredient not found...");
		}
		IngredientsItem item=ingredientsItem.get();
		
		item.setInStock(!item.isInStock());
		return ingredientItemsRepository.save(item);
	}

}
