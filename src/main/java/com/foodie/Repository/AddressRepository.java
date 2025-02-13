package com.foodie.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodie.Model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
	

}
