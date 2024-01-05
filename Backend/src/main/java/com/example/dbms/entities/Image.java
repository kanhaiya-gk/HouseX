package com.example.dbms.entities;

//import java.sql.Blob;


public class Image {
	private int image_id;
	private int house_id;
	private String house_image;
	public Image() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Image(int image_id, int house_id, String house_image) {
		super();
		this.image_id = image_id;
		this.house_id = house_id;
		this.house_image = house_image;
	}
	public int getImage_id() {
		return image_id;
	}
	public void setImage_id(int image_id) {
		this.image_id = image_id;
	}
	public int getHouse_id() {
		return house_id;
	}
	public void setHouse_id(int house_id) {
		this.house_id = house_id;
	}
	public String getHouse_image() {
		return house_image;
	}
	public void setHouse_image(String house_image) {
		this.house_image = house_image;
	}

}
