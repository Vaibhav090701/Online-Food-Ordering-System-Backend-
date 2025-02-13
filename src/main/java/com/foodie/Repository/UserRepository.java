package com.foodie.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodie.Model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findByEmail(String username);

}
