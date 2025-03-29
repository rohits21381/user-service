package com.user.app.entity;

import lombok.Data;

@Data
public class Product {

	private Long productId;
	private String name;
	private Double price;
	private String description;
	private Long userId;
	
}
