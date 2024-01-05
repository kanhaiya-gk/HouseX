package com.example.dbms.entities;

public class ReviewHouse {
	
	private String email_id;
	
	private int house_id;
	
	private String description;
	private int rating;
	
	public ReviewHouse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReviewHouse(String email_id, int house_id, String description, int rating) {
		super();
		this.email_id = email_id;
		this.house_id = house_id;
		this.description = description;
		this.rating = rating;
	}

	

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public int getHouse_id() {
		return house_id;
	}

	public void setHouse_id(int house_id) {
		this.house_id = house_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	
}
