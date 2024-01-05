package com.example.dbms.entities;

import java.sql.Date;
import java.util.List;

public class ImageObject{
	House house;
	String email_id;
	String phone_no;
	String name;
	List<String> images;
	List<Date> dates;
	boolean owner;
	public ImageObject() {
	}
	public ImageObject(House house, String email_id, String phone_no, String name, List<String> images,
			List<Date> dates, boolean owner) {
		this.house = house;
		this.email_id = email_id;
		this.phone_no = phone_no;
		this.name = name;
		this.images = images;
		this.dates = dates;
		this.owner = owner;
	}
	
	public House getHouse() {
		return house;
	}
	public void setHouse(House house) {
		this.house = house;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public String getPhone_no() {
		return phone_no;
	}
	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getImages() {
		return images;
	}
	public void setImages(List<String> images) {
		this.images = images;
	}
	public List<Date> getDates() {
		return dates;
	}
	public void setDates(List<Date> dates) {
		this.dates = dates;
	}
	public boolean isOwner() {
		return owner;
	}
	public void setOwner(boolean owner) {
		this.owner = owner;
	}
}