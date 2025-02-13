package com.foodie.Service;

import com.foodie.Model.Cart;
import com.foodie.Model.CartItem;
import com.foodie.request.CreateCartItemRequest;

public interface CartService {
	
	public CartItem addItemToCart(CreateCartItemRequest req, String jwt)throws Exception;
	
	public CartItem updateCartItemQuantity(long cartItemId, int quantity)throws Exception;
	
	public Cart removeItemFromCart(long cartItemId, String jwt)throws Exception;
	
	public long calculateCartTotal(Cart cart)throws Exception;
	
	public Cart findCartByUserId(long userId) throws Exception;
	
	public Cart findCartById(long cartId)throws Exception;
	
	public Cart clearCart(long userId)throws Exception;

}
