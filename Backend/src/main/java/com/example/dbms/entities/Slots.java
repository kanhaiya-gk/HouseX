package com.example.dbms.entities;

import java.sql.Date;

public class Slots {
	private int house_id;
	private Date date;
	public Slots() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Slots(int house_id, Date date) {
		super();
		this.house_id = house_id;
		this.date = date;
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
