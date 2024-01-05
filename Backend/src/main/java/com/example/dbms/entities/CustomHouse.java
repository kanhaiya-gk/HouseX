package com.example.dbms.entities;

public class CustomHouse {
    
    boolean bookmark;
    int house_id;
    private int rent;
    private Double area;
    private int bhk;
    private String city;
    private String front;
    
    public CustomHouse() {
    }
    
    public CustomHouse(int house_id,boolean bookmark, int rent, Double area, int bhk, String city, String front) {
        this.house_id = house_id;
        this.bookmark = bookmark;
        this.rent = rent;
        this.area = area;
        this.bhk = bhk;
        this.city = city;
        this.front = front;
    }
    
    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public int getBhk() {
        return bhk;
    }

    public void setBhk(int bhk) {
        this.bhk = bhk;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFront() {
        return front;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public boolean isBookmark() {
        return bookmark;
    }
    public void setBookmark(boolean bookmark) {
        this.bookmark = bookmark;
    }

    public int getHouse_id() {
        return house_id;
    }

    public void setHouse_id(int house_id) {
        this.house_id = house_id;
    }
}
