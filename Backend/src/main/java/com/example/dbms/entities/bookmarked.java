package com.example.dbms.entities;

public class bookmarked {
	
	private String email_id;
	
	private int house_id;

	public bookmarked() {
		super();
		// TODO Auto-generated constructor stub
	}

	public bookmarked(String email_id, int house_id) {
		super();
		this.email_id = email_id;
		this.house_id = house_id;
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
	
}
