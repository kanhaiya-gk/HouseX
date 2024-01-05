package com.example.dbms.services;

import java.util.Date;
import java.util.List;
import java.util.Map;

//import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import com.example.dbms.entities.CustomHouse;
import com.example.dbms.entities.FilterObject;
import com.example.dbms.entities.House;
import com.example.dbms.entities.ImageObject;
import com.example.dbms.entities.ObjectAppointments;
import com.example.dbms.entities.RevieHouseInput;
import com.example.dbms.entities.ReviewHouse;
import com.example.dbms.entities.Slots;
import com.example.dbms.entities.appointment;
import com.example.dbms.entities.bookmarked;
@Service
public interface HouseService {
	
	public List<CustomHouse> getHouses();

	public List<CustomHouse> getmyHouse(String email_id);
    
	public ImageObject getHouse(int house_id);
	
	public void addHouse(ImageObject h);
	
	public void updateHouse(ImageObject h, int house_id);

	public void deleteHouse(int house_id);
	
	// public List<House> getincity(String city);
	
	// public List<House> getinstate(String state);
	
	// public List<House> getbhk(int bhk);
	
	// public List<House> getRentlessthan(int rent);
	
	// public List<House> getdate(int months);
	
	public List<ObjectAppointments> view_appointments_seller(String email_id);
	
	public List<ObjectAppointments> view_appointments_buyer(String email_id);
	
	public Map<String,String> shedule_appointment(appointment a);
	
	public void reshedule_appointment(String email_id, int house_id, appointment a);
	
	public void delete_appointment(String email_id, appointment a);
	
	public void delete_all_my_appointments(String email_id);
	
	public List<CustomHouse> my_bookmarks(String email_id);
	
	public void add_bookmark(bookmarked b);
	
	public void remove_bookmark(bookmarked b);

	public List<Slots> show_slots(int house_id);

	void add_slots(List<Slots> s);

	public void remove_slot(Date date, int house_id);
	
	// public void delete_slot(String email_id, Date date);
	
	public void review_house(ReviewHouse housereview,String email_id);

	public List<ReviewHouse> get_reviews_house(int house_id);

	public List<CustomHouse> filter_house(FilterObject filter);
}
