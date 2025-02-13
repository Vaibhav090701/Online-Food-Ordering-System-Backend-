package com.foodie.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodie.Model.Address;
import com.foodie.Model.Cart;
import com.foodie.Model.CartItem;
import com.foodie.Model.Order;
import com.foodie.Model.OrderItem;
import com.foodie.Model.Restaurent;
import com.foodie.Model.User;
import com.foodie.Repository.AddressRepository;
import com.foodie.Repository.OrderItemRepository;
import com.foodie.Repository.OrderRepository;
import com.foodie.Repository.UserRepository;
import com.foodie.request.OrderRequest;

@Service
public class OrderServiceImp implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestaurentService restaurentService;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private CartService cartService;
	

	@Override
	public Order createOrder(OrderRequest req, User user) throws Exception {
		
		Address shippingAddress=req.getAddress();
		Address savedAddress= addressRepository.save(shippingAddress);
		
		//it will check weather user address is already present or not
		//if it present then no need to change, but if it is no present then we need to add in the list of user addresses
		if(!user.getAddresses().contains(savedAddress))
		{
			user.getAddresses().add(savedAddress);
			userRepository.save(user);
		}
		
		Restaurent restaurent=restaurentService.findRestaurentById(req.getRestaurentId());
		
		Order order=new Order();
		order.setCustomer(user);
		order.setRestaurent(restaurent);
		order.setDeliveryAddress(savedAddress);
		order.setCreateAt(new Date());
		//By default orderStatus is pending
		order.setOrderStatus("PENDING");
		
		//to find order items we have to use cart, we find Cart of the user by using userId
		// after finding cart we created order item and set data as cart item beacuse 
		//orderItem and CartItem attribute is same.
		Cart cart=cartService.findCartByUserId(user.getId());
		
		List<OrderItem> items=new ArrayList<>();
		
		for(CartItem item:cart.getItem())
		{
			OrderItem orderItem=new OrderItem();
			orderItem.setFood(item.getFood());
			orderItem.setIngredients(item.getIngredients());
			orderItem.setQuantity(item.getQuantity());
			orderItem.setTotalPrice(item.getTotalPrice());
			
			OrderItem savedOrderItem=orderItemRepository.save(orderItem);
			
			items.add(savedOrderItem);
		}
		
		long totalPrice=cartService.calculateCartTotal(cart);
		
		//I need to check by using cart.getTotal()
		order.setTotalPrice(totalPrice);
		order.setItems(items);
		
		Order savedOrder=orderRepository.save(order);
		
		//add order to the restaurant
		restaurent.getOrders().add(savedOrder);
		
		return savedOrder;
	}

	@Override
	public Order updateOrder(long orderId, String orderStatus) throws Exception {
		
		Optional<Order> optOrder=orderRepository.findById(orderId);
		Order order=optOrder.get();
		
		if(orderStatus.equals("OUT_FOR_DELIVERY") || orderStatus.equals("DELIVERED") || 
				orderStatus.equals("COMPLETED") || orderStatus.equals("PENDING"))
		{
			order.setOrderStatus(orderStatus);
			return orderRepository.save(order);
		}
		throw new Exception("Select valid order status");
		
	}

	@Override
	public void cancleOrder(long orderId) throws Exception {
		
		Optional<Order> optOrder=orderRepository.findById(orderId);
		
		if(optOrder.isEmpty())
		{
			throw new Exception("Order not found with id "+orderId);
		}
		orderRepository.deleteById(orderId);
		
	}

	@Override
	public List<Order> getUserOrders(long userId) throws Exception {
		List<Order>orders=orderRepository.findByCustomerId(userId);
		
		if(orders.isEmpty())
		{
			throw new Exception("Customer didn't order any thing");
		}
		return orders;
		
	}

	@Override
	public List<Order> getRestaurentOrders(long restaurentId, String orderStatus) throws Exception {
		List<Order>orders=orderRepository.findByRestaurentId(restaurentId);
		
		//if user provided orderStatus then we filter the record according to that
		//else we returned all the orders.
		if(orderStatus!=null)
		{
			orders=orders.stream().filter(order->order.getOrderStatus().equals(orderStatus)).toList();
		}
		return orders;
	
	}

}
