package com.foodie.Service;

import org.springframework.stereotype.Service;

import com.foodie.Model.User;

@Service
public interface UserService {
	
	public User findUserByJwtToken(String jwt)throws Exception;
	
	public User findUserByEmail(String email)throws Exception;

}
