package com.example.dbms.services;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;


import com.example.dbms.dao.HouseJdbcDAO;
import com.example.dbms.dao.ReviewHouseDao;
import com.example.dbms.dao.SlotDao;
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
import com.example.dbms.dao.AppointmentsDao;
import com.example.dbms.dao.BookmarkDao;

@Component
public class HouseServiceImpl implements HouseService{
	
	@Autowired
	private HouseJdbcDAO housedao;

	@Override
	public List<CustomHouse> getHouses() {
		// TODO Auto-generated method stub
		return  housedao.list();
	}

	@Override
	public List<CustomHouse> getmyHouse(String email_id) {
		// TODO Auto-generated method stub
		return housedao.myhouse(email_id);
		
	}

	@Override
	public ImageObject getHouse(int house_id) {
		// TODO Auto-generated method stub
		return housedao.get(house_id);
	}

	@Override
	public void addHouse(ImageObject h) {
		// TODO Auto-generated method stub
		housedao.create(h);
	}

	@Override
	public void updateHouse(ImageObject h, int house_id) {
		// TODO Auto-generated method stub
		housedao.update(h, house_id);
	}

	@Override
	public void deleteHouse(int house_id) {
		// TODO Auto-generated method stub
		housedao.delete(house_id);
	}

	// @Override
	// public List<House> getincity(String city) {
	// 	return housedao.housecity(city);
	// }

	// @Override
	// public List<House> getinstate(String state) {
	// 	// TODO Auto-generated method stub
	// 	return housedao.housestate(state);
	// }

	// @Override
	// public List<House> getbhk(int bhk) {
	// 	// TODO Auto-generated method stub
	// 	return housedao.housebhk(bhk);
	// }

	// @Override
	// public List<House> getRentlessthan(int rent) {
	// 	// TODO Auto-generated method stub
	// 	return housedao.houserent(rent);
	// }

	// @Override
	// public List<House> getdate(int months) {
	// 	// TODO Auto-generated method stub
	// 	return housedao.houseDate(months);
	// }
	
	@Autowired
	private AppointmentsDao appointmentdao;


	@Override
	public List<ObjectAppointments> view_appointments_seller(String email_id) {
		// TODO Auto-generated method stub
		return appointmentdao.my_appointments_seller(email_id);
	}
	
	public List<ObjectAppointments> view_appointments_buyer(String email_id) {
		// TODO Auto-generated method stub
		return appointmentdao.my_appointments_buyer(email_id);
	}
	
	@Override
	public Map<String,String> shedule_appointment(appointment a) {
		// TODO Auto-generated method stub
		Map<String,String> m= appointmentdao.create(a);
		int house_id = a.getHouse_id();
		Date slot = a.getAppoint_day();
		if(m.get("message").equals("success")) {
			slotdao.removeslot(house_id, slot);
		}
		return m;
	}

	@Override
	public void reshedule_appointment(String email_id, int house_id, appointment a) {
		// TODO Auto-generated method stub
		appointmentdao.update_appointment(a, email_id, house_id);
	}

	
	public void delete_appointment(String email_id, appointment a) {
		// TODO Auto-generated method stub
		appointmentdao.delete_appointment(email_id, a);
	}

	
	public void delete_all_my_appointments(String email_id) {
		// TODO Auto-generated method stub
		appointmentdao.delete_all_my_appointment(email_id);
	}
	
	@Autowired
	private BookmarkDao bookmarkdao;
	
	public List<CustomHouse> my_bookmarks(String email_id){
		return housedao.bookmarks(email_id);
	}
	
	public void add_bookmark(bookmarked b){
		bookmarkdao.bookmarkit(b);
	}
	
	public void remove_bookmark(bookmarked b){
		bookmarkdao.remove_bookmark(b);
	}

	@Autowired
	private SlotDao slotdao;
	
	public void add_slots(List<Slots> s) {
		// TODO Auto-generated method stub
		slotdao.addslots(s);
	}
	
	@Override
	public void remove_slot(Date date, int house_id) {
		// TODO Auto-generated method stub
		slotdao.removeslot(house_id, date);
	}

	@Override
	public List<Slots> show_slots(int house_id) {
		// TODO Auto-generated method stub
		return slotdao.houseSlots(house_id);
	}

	// @Override
	// public void delete_slot(String email_id, Date date) {
	// 	// TODO Auto-generated method stub
	// 	slotdao.deleteslot(email_id, date);
	// }
	
	@Autowired
	private ReviewHouseDao reviewHouseDao;

	@Override
	public void review_house(ReviewHouse housereview,String email_id) {
		// TODO Auto-generated method stub
		reviewHouseDao.addrating(housereview,email_id);
		reviewHouseDao.update_houserating(housereview.getHouse_id());
	}

	@Override
	public List<ReviewHouse> get_reviews_house(int house_id){
		return reviewHouseDao.get_reviews(house_id);
	}

	@Override
	public List<CustomHouse> filter_house(FilterObject filter){
		return housedao.filter(filter);
	}

} 
