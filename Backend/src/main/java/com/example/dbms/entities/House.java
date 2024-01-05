package com.example.dbms.entities;

//import java.sql.Blob;
import java.sql.Date;

public class House {
	
	private int house_id;
	
	private Double area;
	private int construction_year;

	private String street;

	private String city;

	private String state;

	private String house_description;
	private int bhk;
	private int rent;

	private Date end_date;
	
	private String email_id;
	
	private Double rating;
	
	private Boolean isoccupied;
	
	private String front;

	public House() {
		super();
		// TODO Auto-generated constructor stub
	}

	public House(Double area, int construction_year, String street, String city, String state, String house_description,
			int bhk, int rent, Date end_date, String email_id, Double rating, Boolean isoccupied, String front) {
		super();
		this.area = area;
		this.construction_year = construction_year;
		this.street = street;
		this.city = city;
		this.state = state;
		this.house_description = house_description;
		this.bhk = bhk;
		this.rent = rent;
		this.end_date = end_date;
		this.email_id = email_id;
		this.rating = rating;
		this.isoccupied = isoccupied;
		this.front = front;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public int getHouse_id() {
		return house_id;
	}

	public void setHouse_id(int house_id) {
		this.house_id = house_id;
	}

	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	public int getConstruction_year() {
		return construction_year;
	}

	public void setConstruction_year(int construction_year) {
		this.construction_year = construction_year;
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

	public int getBhk() {
		return bhk;
	}

	public void setBhk(int bhk) {
		this.bhk = bhk;
	}

	public int getRent() {
		return rent;
	}

	public void setRent(int rent) {
		this.rent = rent;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public Boolean getIsoccupied() {
		return isoccupied;
	}

	public void setIsoccupied(Boolean isoccupied) {
		this.isoccupied = isoccupied;
	}
	
	public String getFront() {
		return front;
	}

	public void setFront(String front) {
		this.front = front;
	}

	@Override
	public String toString() {
		return "House [house_id=" + house_id + ", area=" + area + ", construction_year=" + construction_year
				+ ", street=" + street + ", city=" + city + ", state=" + state + ", house_description="
				+ house_description + ", bhk=" + bhk + ", rent=" + rent + ", end_date=" + end_date + ", email_id="
				+ email_id + ", rating=" + rating + ", isoccupied=" + isoccupied + "]";
	}
	
}