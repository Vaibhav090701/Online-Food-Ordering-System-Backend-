package com.foodie.request;

import lombok.Data;

@Data
public class IngredientItemRequest {
	
	private String name;
	private long categoryId;
	private long restaurentId;

}
