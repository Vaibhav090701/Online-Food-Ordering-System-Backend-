package com.foodie.Response;

import com.foodie.Model.USER_ROLE;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {
	
	private String jwt;
	private String message;
	private USER_ROLE roles;

}
