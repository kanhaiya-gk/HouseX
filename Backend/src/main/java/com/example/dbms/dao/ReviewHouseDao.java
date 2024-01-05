package com.example.dbms.dao;

import java.io.Console;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.example.dbms.entities.House;
import com.example.dbms.entities.RevieHouseInput;
import com.example.dbms.entities.ReviewHouse;

@Component
public class ReviewHouseDao {
	
	RowMapper<ReviewHouse>	rowMapper = (rs, rowNum)->{
		ReviewHouse rh = new ReviewHouse();
		rh.setEmail_id(rs.getString("email_id"));
		rh.setHouse_id(rs.getInt("house_id"));
		rh.setRating(rs.getInt("rating"));
		rh.setDescription(rs.getString("decription"));
		
		return rh;
	};
	
	RowMapper<House> rowmapper = (rs, rowNum)->{
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
		house.setRating(rs.getDouble("rating"));
		house.setIsoccupied(rs.getBoolean("isoccupied"));
		house.setFront(rs.getString("front"));
		return house;
	};
 	
	private static final Logger log = LoggerFactory.getLogger(ReviewHouseDao.class);

	private JdbcTemplate jdbctemplate;
	
	@Autowired
	public ReviewHouseDao(JdbcTemplate jdbctemplate){
		super();
		this.jdbctemplate = jdbctemplate;
	}
	
	public void addrating(ReviewHouse housereview,String email_id) {
		String sql = "insert into reviewHouse(email_id, house_id, rating, decription) values(?, ?, ?, ?)";
		jdbctemplate.update(sql, email_id, housereview.getHouse_id(), housereview.getRating(), housereview.getDescription() );
	}
	
	@Autowired
    private HouseJdbcDAO houseJdbcDAO;
	
	public void update_houserating(int house_id) {
		// TODO Auto-generated method stub
		String sql = "update house set rating = "
				+ "(select AVG(rating) from reviewHouse where house_id = ? ) "
				+ "where house_id = ?";
		System.out.println(house_id);
		House h = houseJdbcDAO.returnhouse(house_id);
		System.out.println(house_id);
		jdbctemplate.update(sql, house_id, house_id);
	} 

	public List<ReviewHouse> get_reviews(int house_id) {
			String sql = "select * from reviewHouse where house_id = ?";
			List<ReviewHouse> reviews = null;
			try {
				reviews = (List<ReviewHouse>) jdbctemplate.query(sql,rowMapper, new Object[]{house_id});
			}
			catch(DataAccessException daex) {
				log.info("No reviews");
			}
					 
			return reviews;
		
	}
}
