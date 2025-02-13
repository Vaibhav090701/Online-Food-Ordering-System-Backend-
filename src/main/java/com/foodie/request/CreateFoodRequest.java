package com.foodie.request;

import java.util.List;

import com.foodie.Model.Category;
import com.foodie.Model.IngredientsItem;
import com.foodie.Model.Restaurent;

import lombok.Data;

@Data
public class CreateFoodRequest {
	
	private String name;
	private String description;
	private Category category;
	private long price;
	private List<String>images;
	private List<IngredientsItem>ingredients;
	private long resturentId;
	private boolean vagetrian;
	private boolean seasional;
	

}
