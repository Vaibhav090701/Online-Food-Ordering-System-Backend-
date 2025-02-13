package com.foodie.request;

import java.util.List;

import lombok.Data;

@Data
public class CreateCartItemRequest {
	
	private long foodId;
	private int quantity;
	private List<String> ingredients;


}
