package com.example.dbms.entities;

public class FilterObject {
    
    String state;
    String city;
    Integer bhk;
    Integer rent;
    Integer months;

    
    public FilterObject() {
    }

    public FilterObject(String state, String city, Integer bhk, Integer rent, Integer months) {
        this.state = state;
        this.city = city;
        this.bhk = bhk;
        this.rent = rent;
        this.months = months;
    }

    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public Integer getBhk() {
        return bhk;
    }
    public void setBhk(Integer bhk) {
        this.bhk = bhk;
    }
    public Integer getRent() {
        return rent;
    }
    public void setRent(Integer rent) {
        this.rent = rent;
    }
    public Integer getMonths() {
        return months;
    }
    public void setMonths(Integer months) {
        this.months = months;
    }

    

}
