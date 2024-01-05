package com.example.dbms.dao;

import java.sql.Blob;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
//import org.springframework.data.util.Pair;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.dbms.entities.House;
import com.example.dbms.entities.ObjectAppointments;
import com.example.dbms.entities.Slots;
import com.example.dbms.entities.UserEntity;
import com.example.dbms.entities.appointment;


@Component
public class AppointmentsDao{
	
	RowMapper<Slots> slotmapper = (rs, rowNum) -> {
		Slots s = new Slots();
		s.setHouse_id(rs.getInt("house_id"));
		s.setDate(rs.getDate("date"));
	    return s; 
	};
	
	RowMapper<appointment> rowmapper = (rs, rowNum)->{
		appointment a = new appointment();
		a.setEmail_id(rs.getString("email_id"));
		a.setHouse_id(rs.getInt("house_id"));
		a.setAppoint_day(rs.getDate("appoint_day"));
		return a;
	};
	
	RowMapper<House> rowmapperhouse = (rs, rowNum)->{
		House house = new House();
		house.setHouse_id(rs.getInt("house_id"));
		house.setArea(rs.getDouble("area"));
		house.setConstruction_year(rs.getInt("construction_year"));
		house.setStreet(rs.getString("street"));
		house.setCity(rs.getString("city"));
		house.setState(rs.getString("state"));
		house.setHouse_description(rs.getString("house_description"));
		house.setBhk(rs.getInt("bhk"));
		house.setRent(rs.getInt("rent"));
		house.setEnd_date(rs.getDate("end_date"));
		house.setEmail_id(rs.getString("email_id"));
		house.setIsoccupied(rs.getBoolean("isoccupied"));
		Blob front=rs.getBlob("front");
		byte[] bdata = front.getBytes(1, (int) front.length());
		String s = new String(bdata);
		house.setFront(s);
		return house;
	};
	
	RowMapper<UserEntity> rowmapperuser = (rs, rowNum)->{
		UserEntity user = new UserEntity();
		user.setName(rs.getString("name"));
		user.setEmail_id(rs.getString("email_id"));
		user.setPhone_no(rs.getString("phone_no"));
		return user;
	};
	
	RowMapper<ObjectAppointments> rowmapperobj = (rs, rowNum) -> {	
		ObjectAppointments obj = new ObjectAppointments();
		obj.setHouse_id(rs.getInt("house_id"));
		obj.setStreet(rs.getString("street"));
		obj.setCity(rs.getString("city"));
		obj.setState(rs.getString("state"));
		obj.setHouse_description(rs.getString("house_description"));
		// obj.setFront(rs.getBlob("front"));
		obj.setAppoint_day(rs.getDate("appoint_day"));
		obj.setName(rs.getString("name"));
		obj.setPhone_no(rs.getString("phone_no"));
		obj.setEmail_id(rs.getString("email_id"));

		Blob front=rs.getBlob("front");
		if(front!=null) {
			byte[] bdata = front.getBytes(1, (int) front.length());
			String s = new String(bdata);
			obj.setFront(s);
		}


		return obj;
	};
	
	private static final Logger log = LoggerFactory.getLogger(AppointmentsDao.class);

	private JdbcTemplate jdbctemplate;
	
	public AppointmentsDao(JdbcTemplate jdbctemplate){
		this.jdbctemplate = jdbctemplate;
	}

//	@Override
public Map<String,String> create(appointment a) {
	// TODO Auto-generated method stub
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
	Map<String,String> msg = new HashMap<>();
	try {
		String sql = "insert into appointment(email_id, house_id, appoint_day) values(?, ?, ?)";
		jdbctemplate.update(sql, email, a.getHouse_id(), a.getAppoint_day());
		msg.put("message","success");
		return msg;
	}
	catch(DuplicateKeyException e){
		msg.put("message","failure");
		return msg;
	}
}
	
	public List<ObjectAppointments> my_appointments_buyer(String email_id) {
		// TODO Auto-generated method stub
		String sql = "Select house.house_id, house.street, house.city, house.state, house.house_description, house.front, appointment.appoint_day, userEntity.name, userEntity.phone_no, userEntity.email_id "
				+ "from appointment,userEntity,house  where house.email_id = userEntity.email_id "
				+ "AND appointment.email_id = ? AND appointment.house_id = house.house_id";
		List<ObjectAppointments> my_appointment = null;
		try {
			my_appointment = (List<ObjectAppointments>) jdbctemplate.query(sql,rowmapperobj, new Object[]{email_id});
		}
		catch(DataAccessException daex) {
			log.info("You have no appointments sheduled");
		}
		     
		return my_appointment;
	}
	
	public List<ObjectAppointments> my_appointments_seller(String email_id) {
		// TODO Auto-generated method stub
		String sql = "Select house.house_id, house.street, house.city, house.state, house.house_description, house.front, appointment.appoint_day, userEntity.name, userEntity.phone_no, userEntity.email_id "
				+ "from appointment,userEntity,house where house.email_id = ? "
				+ "AND appointment.email_id = userEntity.email_id AND appointment.house_id = house.house_id";
		List<ObjectAppointments> my_appointment = null;
		try {
			my_appointment = (List<ObjectAppointments>) jdbctemplate.query(sql,rowmapperobj, new Object[]{email_id});
		}
		catch(DataAccessException daex) {
			log.info("You have no appointments sheduled");
		}
		     
		return my_appointment;
	}

	public void update_appointment(appointment a, String email_id, int house_id) {
		// TODO Auto-generated method stub
		String sql = "update appointment set appoint_day = ? where email_id = ? and house_id = ?";
		jdbctemplate.update(sql, a.getHouse_id(), email_id, house_id);
	}
	
	public void delete_appointment(String email_id, appointment a) {
		// TODO Auto-generated method stub
		String sql = "delete from appointment where house_id = ? and appoint_day=?";
		jdbctemplate.update(sql, a.getHouse_id(), a.getAppoint_day());
		String sq = "insert into slots(house_id, date)  values ( ?, ?)";
		jdbctemplate.update(sq,a.getHouse_id(), a.getAppoint_day());
	}
	
	public void delete_all_my_appointment(String email_id) {
		// TODO Auto-generated method stub
		String sql = "delete from appointment where email_id = ?";
		jdbctemplate.update(sql, email_id);
	}
}
