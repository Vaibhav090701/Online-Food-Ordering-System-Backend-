package com.foodie.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodie.Config.JwtProvider;
import com.foodie.Model.User;
import com.foodie.Repository.UserRepository;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	public User findUserByJwtToken(String jwt) throws Exception {	
		String email= jwtProvider.getEmailFromJwtToken(jwt);
		User user=findUserByEmail(email);
		return user;
	}

	public User findUserByEmail(String email) throws Exception {
		User user=userRepository.findByEmail(email);	
		if(user==null){
			throw new Exception("user not found");
		}	
		return user;
	}

}
