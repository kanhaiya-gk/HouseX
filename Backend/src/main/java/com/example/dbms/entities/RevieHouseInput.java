package com.example.dbms.entities;

public class RevieHouseInput {
	private String description;
	private int rating;
	public RevieHouseInput(String description, int rating) {
		super();
		this.description = description;
		this.rating = rating;
	}
	public RevieHouseInput() {
		super();
		// TODO Auto-generated constructor stub
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
