package com.foodie.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodie.Model.Cart;
import com.foodie.Model.CartItem;
import com.foodie.Model.Food;
import com.foodie.Model.User;
import com.foodie.Repository.CartItemRepository;
import com.foodie.Repository.CartRepository;
import com.foodie.Repository.FoodRepository;
import com.foodie.request.CreateCartItemRequest;

@Service
public class CartServiceImp implements CartService {
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FoodService foodService;

	@Override
	public CartItem addItemToCart(CreateCartItemRequest req, String jwt) throws Exception {
		User user=userService.findUserByJwtToken(jwt);
		
		Food food=foodService.findFoodById(req.getFoodId());
		
		Cart cart=cartRepository.findByCustomerId(user.getId());
		
		//to check the food is already present in the cart or not is it is present then just update the quantity
		for(CartItem cartItem:cart.getItem())
		{
			if(cartItem.getFood().equals(food))
			{
				int newQuantity=cartItem.getQuantity()+req.getQuantity();
				return updateCartItemQuantity(cartItem.getId(), newQuantity);
			}
		}
		//if food is not present then create new cartItem
		CartItem cartItem=new CartItem();
		cartItem.setFood(food);
		cartItem.setCart(cart);
		cartItem.setIngredients(req.getIngredients());
		cartItem.setQuantity(req.getQuantity());
		cartItem.setTotalPrice(req.getQuantity()*food.getPrice());
		
		CartItem savedCartItem= cartItemRepository.save(cartItem);
		
		//to add cartItems to list of cartItems in Cart Model
		cart.getItem().add(savedCartItem);
		
		return savedCartItem;
	}

	@Override
	public CartItem updateCartItemQuantity(long cartItemId, int quantity) throws Exception {
		
		Optional<CartItem> cartItem= cartItemRepository.findById(cartItemId);
		
		if(cartItem.isEmpty())
		{
			throw new Exception("Cart Item not found with id+ "+cartItemId);
		}
		
		CartItem foundedCartItem=cartItem.get();
		foundedCartItem.setQuantity(quantity);
		//update the price also
		foundedCartItem.setTotalPrice(foundedCartItem.getQuantity()*foundedCartItem.getFood().getPrice());
		return cartItemRepository.save(foundedCartItem) ;
	}

	@Override
	public Cart removeItemFromCart(long cartItemId, String jwt) throws Exception {
		
		User user=userService.findUserByJwtToken(jwt);
		Cart cart=cartRepository.findByCustomerId(user.getId());
		
		Optional<CartItem> optCartItem=cartItemRepository.findById(cartItemId);
		
		if(optCartItem.isEmpty())
		{
			throw new Exception("Cart Item not exist with id+ "+cartItemId);
		}
		
		CartItem items=optCartItem.get();
		
		cart.getItem().remove(items);
		
		return cartRepository.save(cart);
	}

	@Override
	public long calculateCartTotal(Cart cart) throws Exception {
		long total=0L;
		
		for(CartItem item:cart.getItem())
		{
			total+=item.getFood().getPrice()*item.getQuantity();
		}
		return total;
	}

	@Override
	public Cart findCartByUserId(long userId) throws Exception {
		
		Cart cart= cartRepository.findByCustomerId(userId);
		if(cart==null)
		{
			throw new Exception("Cart not found by user id "+userId);
		}	
		cart.setTotal(calculateCartTotal(cart));
		return cart;
	}

	@Override
	public Cart findCartById(long cartId) throws Exception {
		
		Optional<Cart> optCart=cartRepository.findById(cartId);
		
		if(optCart.isEmpty())
		{
			throw new Exception("Cart not exist with cart id "+cartId);
		}
		return optCart.get();
	}

	@Override
	public Cart clearCart(long userId) throws Exception {
		Cart cart=findCartByUserId(userId);
		cart.getItem().clear();
		return cartRepository.save(cart);		
	}

}
