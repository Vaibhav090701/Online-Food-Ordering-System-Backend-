package com.foodie.request;

import lombok.Data;

@Data
public class UpdateCartItemRequest {
	
	private long cartItemId;
	private int quantity;

}
