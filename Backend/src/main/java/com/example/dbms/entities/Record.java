package com.example.dbms.entities;

import java.util.Date;

public class Record {
	private String email_id;
	private int house_id;
	private Date date;
	public Record() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Record(String email_id, int house_id, Date date) {
		super();
		this.email_id = email_id;
		this.house_id = house_id;
		this.date = date;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
