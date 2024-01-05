package com.example.dbms.entities;

import java.sql.Blob;
import java.util.Date;

public class ObjectAppointments {
	private int house_id;
	
	private String front;
	
	private String street;

	private String city;

	private String state;
	
	private String house_description;
	
	private String name;

	private String phone_no;
	
	private String email_id;
	
	private Date appoint_day;

	public ObjectAppointments() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ObjectAppointments(int house_id,String front,String street, String city, String state, String house_description,
			String name, String phone_no, String email_id, Date appoint_day) {
		super();
		this.house_id = house_id;
		this.front = front;
		this.street = street;
		this.city = city;
		this.state = state;
		this.house_description = house_description;
		this.name = name;
		this.phone_no = phone_no;
		this.email_id = email_id;
		this.appoint_day = appoint_day;
	}

	public String getFront() {
		return front;
	}

	public void setFront(String front) {
		this.front = front;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public String getHouse_description() {
		return house_description;
	}

	public void setHouse_description(String house_description) {
		this.house_description = house_description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone_no() {
		return phone_no;
	}

	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public Date getAppoint_day() {
		return appoint_day;
	}

	public void setAppoint_day(Date appoint_day) {
		this.appoint_day = appoint_day;
	}

	public int getHouse_id() {
		return house_id;
	}

	public void setHouse_id(int house_id) {
		this.house_id = house_id;
	}
	
}


