package com.foodie.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foodie.DTO.RestaurentDTO;
import com.foodie.Model.Restaurent;
import com.foodie.Model.User;
import com.foodie.Service.RestaurentService;
import com.foodie.Service.UserService;

@RestController
@RequestMapping("/api/restaurents")
public class RestaurentController {
	
	@Autowired
	private RestaurentService restaurentService;
	
	@Autowired
	private UserService userService;

	@GetMapping("/search")
	public ResponseEntity<List<Restaurent>>searchRestaurent(@RequestHeader("Authorization")String jwt, @RequestParam String keyword) throws Exception
	{
		User user=userService.findUserByJwtToken(jwt);
		List<Restaurent>restaurents= restaurentService.searchRestaurent(keyword);
		return new ResponseEntity<>(restaurents,HttpStatus.FOUND);
	}
	
	@GetMapping()
	public ResponseEntity<List<Restaurent>>getAllRestaurents(@RequestHeader("Authorization")String jwt) throws Exception
	{
		User user=userService.findUserByJwtToken(jwt);
		List<Restaurent>restaurents= restaurentService.getAllRestaurent();
		return new ResponseEntity<>(restaurents,HttpStatus.FOUND);	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Restaurent>getRestaurentById(@RequestHeader("Authorization")String jwt, @PathVariable long id) throws Exception
	{
		User user=userService.findUserByJwtToken(jwt);
		Restaurent restaurents= restaurentService.findRestaurentById(id);
		return new ResponseEntity<>(restaurents,HttpStatus.FOUND);
		
	}
	
	@PutMapping("/{id}/add-favourites")
	public ResponseEntity<RestaurentDTO>addToFavourites(@RequestHeader("Authorization")String jwt, @PathVariable long id) throws Exception
	{
		User user=userService.findUserByJwtToken(jwt);
		RestaurentDTO dto=restaurentService.addFavourites(id, user);
		return new ResponseEntity<>(dto,HttpStatus.OK);
		
	}


}
