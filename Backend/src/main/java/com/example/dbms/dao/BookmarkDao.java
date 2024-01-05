package com.example.dbms.dao;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.dbms.entities.bookmarked;
@Component
public class BookmarkDao {
	// book mark part
	RowMapper<bookmarked> bkmap = (rs, rowNum)->{
		bookmarked it = new bookmarked();
		it.setEmail_id(rs.getString("email_id"));
		it.setHouse_id(rs.getInt("house_id"));
		return null;	
	};
	

//	private static final Logger log = LoggerFactory.getLogger(BookmarkDao.class);

	private JdbcTemplate jd;

	public BookmarkDao(JdbcTemplate jd){
		this.jd= jd;
	}
	
	public void bookmarkit(bookmarked b){ 
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	      String name = auth.getName();
		 String sql = "insert into bookmark( email_id, house_id) values (?, ?)";	
		 jd.update(sql,name, b.getHouse_id());
	} 
	
	public void remove_bookmark(bookmarked b){ 
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	  String name = auth.getName();	
		 jd.update("delete from bookmark where email_id = ? and house_id = ?", name, b.getHouse_id());
	} 
	
}
