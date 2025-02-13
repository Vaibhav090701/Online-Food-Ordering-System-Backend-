package com.foodie.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foodie.Model.Order;
import com.foodie.Model.User;
import com.foodie.Service.OrderService;
import com.foodie.Service.UserService;
import com.foodie.request.OrderRequest;

@RestController
@RequestMapping("/api/admin")
public class AdminOrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/order/restaurent/{id}")
	public ResponseEntity<List<Order>>getOrderHistoryOfRestaurent(@PathVariable long id, @RequestParam(required = false)String order_status,  @RequestHeader("Authorization")String jwt) throws Exception
	{
		User user=userService.findUserByJwtToken(jwt);
		
		List<Order> order=orderService.getRestaurentOrders(id, order_status);
		
		return new ResponseEntity<>(order,HttpStatus.OK);	
	}
	
	//path variable attribute and url attribute name should be same
	@PutMapping("/order/{id}/{orderStatus}")
	public ResponseEntity<Order>updateOrderStatus(@PathVariable String orderStatus, @PathVariable long id,  @RequestHeader("Authorization")String jwt) throws Exception
	{
		User user=userService.findUserByJwtToken(jwt);
		
		Order order=orderService.updateOrder(id, orderStatus);
		
		return new ResponseEntity<>(order,HttpStatus.OK);	
	}
	
	
}
