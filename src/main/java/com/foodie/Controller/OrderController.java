package com.foodie.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodie.Model.CartItem;
import com.foodie.Model.Order;
import com.foodie.Model.User;
import com.foodie.Service.OrderService;
import com.foodie.Service.UserService;
import com.foodie.request.CreateCartItemRequest;
import com.foodie.request.OrderRequest;

@RestController
@RequestMapping("/api")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/order")
	public ResponseEntity<Order>createOrder(@RequestBody OrderRequest req, @RequestHeader("Authorization")String jwt) throws Exception
	{
		User user=userService.findUserByJwtToken(jwt);
		Order order=orderService.createOrder(req, user);
		
		return new ResponseEntity<>(order,HttpStatus.CREATED);	
	}
	
	@GetMapping("/order/user")
	public ResponseEntity<List<Order>>getOrderHistoryOfUser(@RequestHeader("Authorization")String jwt) throws Exception
	{
		User user=userService.findUserByJwtToken(jwt);
		List<Order> order=orderService.getUserOrders(user.getId());
		
		return new ResponseEntity<>(order,HttpStatus.CREATED);	
	}


}
