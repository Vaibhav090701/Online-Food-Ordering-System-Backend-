package com.foodie.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	private User customer;
	
	@Column(length = 3000)
	private String customerAdddress;
	
	
}
