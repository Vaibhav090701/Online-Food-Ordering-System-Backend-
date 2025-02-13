package com.foodie.request;

import java.util.List;

import com.foodie.Model.Address;
import com.foodie.Model.ContactInformation;

import lombok.Data;

@Data
public class CreateRestaurantRequest {
	
	private long id;
	private String name;
	private String description;
	private String cuisineType;
	private Address address;
	private ContactInformation contactInformation;
	private String openingHours;
	private List<String>images;

}
