package com.example.dbms.entities;

public class ReviewOwner {
	
	private String tenant;
	
	private String owner;
	
	private String description;
	private int rating;
	
	public ReviewOwner() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ReviewOwner(String tenant, String owner, String description, int rating) {
		super();
		this.tenant = tenant;
		this.owner = owner;
		this.description = description;
		this.rating = rating;
	}
	
	public String getTenant() {
		return tenant;
	}
	public void setTenant(String tenant) {
		this.tenant = tenant;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
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
