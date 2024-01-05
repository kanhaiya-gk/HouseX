package com.example.dbms.dao;

import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

//import com.example.demo.entities.ObjectSlot;
import com.example.dbms.entities.Slots;

@Component
public class SlotDao {
	
	RowMapper<Slots> slotmapper = (rs, rowNum) -> {
		Slots s = new Slots();
		s.setHouse_id(rs.getInt("house_id"));
		s.setDate(rs.getDate("date"));
	    return s; 
	};
	
	private JdbcTemplate jd;

	public SlotDao(JdbcTemplate jd){
		this.jd = jd;
	}
	
	public List<Slots> houseSlots(int house_id) {
		// TODO Auto-generated method stub
		List<Slots> s = null;
		
		String sql = "select * from slots where house_id = ?";
		s = ( List<Slots>) jd.query(sql, slotmapper, new Object[]{house_id});
		
		return s;
	}
	
	 public void addslots(List<Slots> s) {
        for(Slots slot:s){
            String sql = "insert into slots(house_id, date)  values ( ?, ?)";
		    jd.update(sql,slot.getHouse_id(), slot.getDate());
        }
	 }
	 
	 public void removeslot(int house_id, Date date) {
		 jd.update("delete from slots where house_id = ? and date = ?", house_id, date);
	 }
		 
}