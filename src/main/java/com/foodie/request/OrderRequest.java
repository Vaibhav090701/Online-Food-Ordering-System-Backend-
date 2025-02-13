package com.foodie.request;

import com.foodie.Model.Address;

import lombok.Data;

@Data
public class OrderRequest {
	
	private long restaurentId;
	private Address address;

}
