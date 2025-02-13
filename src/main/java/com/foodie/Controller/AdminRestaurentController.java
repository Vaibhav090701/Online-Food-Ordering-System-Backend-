package com.foodie.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodie.Model.Restaurent;
import com.foodie.Model.User;
import com.foodie.Response.MessageResponse;
import com.foodie.Service.RestaurentService;
import com.foodie.Service.UserService;
import com.foodie.request.CreateRestaurantRequest;

@RestController
@RequestMapping("/api/admin/restaurents")
public class AdminRestaurentController {
	
	@Autowired
	private RestaurentService restaurentService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping()
	public ResponseEntity<Restaurent> createRestaurent(@RequestBody CreateRestaurantRequest req,@RequestHeader("Authorization")String jwt) throws Exception
	{
		User user=userService.findUserByJwtToken(jwt);
		Restaurent restaurent= restaurentService.createRestaurent(req, user);
		return new ResponseEntity<>(restaurent, HttpStatus.CREATED);	
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Restaurent> updateRestaurent(@RequestBody CreateRestaurantRequest req,@RequestHeader("Authorization")String jwt, @PathVariable long id) throws Exception
	{
		User user=userService.findUserByJwtToken(jwt);
		Restaurent restaurent= restaurentService.updateRestaurent(id, req);
		return new ResponseEntity<>(restaurent, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<MessageResponse> deleteRestaurent(@RequestHeader("Authorization")String jwt, @PathVariable long id) throws Exception
	{
		User user=userService.findUserByJwtToken(jwt);
		restaurentService.deleteRestaurent(id);
		MessageResponse res=new MessageResponse();
		res.setMessage("Restaurent deleted successfully...");
		return new ResponseEntity<>(res,HttpStatus.GONE);
		
	}
	
	@PutMapping("/{id}/status")
	public ResponseEntity<Restaurent> updateRestaurentStatus(@RequestHeader("Authorization")String jwt, @PathVariable long id) throws Exception
	{
		User user=userService.findUserByJwtToken(jwt);
		Restaurent restaurent= restaurentService.updatesRestaurentStatus(id);
		
		return new ResponseEntity<>(restaurent,HttpStatus.OK);	
	}
	
	@GetMapping("/user")
	public ResponseEntity<Restaurent> findRestaurentByUserId(@RequestHeader("Authorization")String jwt) throws Exception
	{
		User user=userService.findUserByJwtToken(jwt);
		Restaurent restaurent= restaurentService.getRestaurentByUserId(user.getId());
		
		return new ResponseEntity<>(restaurent,HttpStatus.FOUND);
		
	}
}
