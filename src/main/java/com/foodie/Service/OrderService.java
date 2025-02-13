package com.foodie.Service;

import java.util.List;

import com.foodie.Model.Order;
import com.foodie.Model.User;
import com.foodie.request.OrderRequest;

public interface OrderService {
	
	public Order createOrder(OrderRequest req, User user) throws Exception;
	
	public Order updateOrder(long orderId, String orderStatus)throws Exception;
	
	public void cancleOrder(long orderId)throws Exception;
	
	public List<Order>getUserOrders(long userId)throws Exception;
	
	public List<Order>getRestaurentOrders(long restaurentId, String orderStatus)throws Exception;
	

}
