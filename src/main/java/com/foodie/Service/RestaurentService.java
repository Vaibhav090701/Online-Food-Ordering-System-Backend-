 package com.foodie.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.foodie.DTO.RestaurentDTO;
import com.foodie.Model.Restaurent;
import com.foodie.Model.User;
import com.foodie.request.CreateRestaurantRequest;

@Service
public interface RestaurentService {
	
	public Restaurent createRestaurent(CreateRestaurantRequest req, User user);
	
	public Restaurent updateRestaurent(long restaurentId, CreateRestaurantRequest updateRestaurent)throws Exception;
	
	public void deleteRestaurent(long restaurentId)throws Exception;
	
	public List<Restaurent>getAllRestaurent();
	
	public List<Restaurent>searchRestaurent(String keyword);
	
	public Restaurent findRestaurentById(long id)throws Exception;
	
	public Restaurent getRestaurentByUserId(long userId)throws Exception;
	
	public RestaurentDTO addFavourites(long restaurentId, User user)throws Exception;
	
	public Restaurent updatesRestaurentStatus(long id)throws Exception;

}




