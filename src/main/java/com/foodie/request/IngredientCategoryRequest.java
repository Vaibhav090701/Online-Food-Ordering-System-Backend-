package com.foodie.request;

import lombok.Data;

@Data
public class IngredientCategoryRequest {
	
	private String name;
	private long restaurentId;

}
