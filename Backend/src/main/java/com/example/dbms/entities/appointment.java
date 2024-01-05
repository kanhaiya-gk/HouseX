package com.example.dbms.entities;

import java.sql.Date;

public class appointment {
	
	private String email_id;
	
	private int house_id;
	
	private Date appoint_day;
	public appointment(String email_id, int house_id, Date appoint_day) {
		super();
		this.email_id = email_id;
		this.house_id = house_id;
		this.appoint_day = appoint_day;
	}
	public appointment() {
		super();
		// TODO Auto-generated constructor stub
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
	public Date getAppoint_day() {
		return appoint_day;
	}
	public void setAppoint_day(Date appoint_day) {
		this.appoint_day = appoint_day;
	}
}
