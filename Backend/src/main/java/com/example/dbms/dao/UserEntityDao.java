package com.example.dbms.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.dbms.entities.UserEntity;

//import ch.qos.logback.classic.Logger;

@Repository
@Transactional

public class UserEntityDao{

	private static final Logger log = LoggerFactory.getLogger(UserEntityDao.class);
	
//	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
	public UserEntityDao(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}
	
	RowMapper<UserEntity> rowMapper = (rs,rowNum) -> {
		UserEntity userN = new UserEntity();
//		userN.getUser_id();
        userN.setName(rs.getString("name"));
        userN.setPassword(rs.getString("password"));
        userN.setRole(rs.getString("role"));
        userN.setEnabled(rs.getBoolean("enabled"));
        userN.setEmail_id(rs.getString("email_id"));
        userN.setPhone_no(rs.getString("phone_no"));
        userN.setRating(rs.getDouble("rating"));
        return userN;
	};
	
	public UserEntity findUserAccount(String email_id) {
        String sql = "Select u.name, u.password,u.role,u.enabled,u.email_id,u.phone_no,u.rating From userEntity u" + " where u.email_id = ? ";
        UserEntity user = null;        
        try {
            user = jdbcTemplate.queryForObject(sql,rowMapper,email_id);
            return user;
        } catch (DataAccessException exc) {
			log.info("User not found: "+email_id);
			return null;
		}
		
    }
	
//	@Autowired
//	BCryptPasswordEncoder bCryptEncoder;
	
	public UserEntity save(UserEntity t) {
		
		String sql = "INSERT INTO userEntity(name,password,role, enabled, email_id, phone_no, rating) values(?,?,?,?,?,?,?)";
		// TODO Auto-generated method stub
		int insert = jdbcTemplate.update(sql,t.getName(),t.getPassword(),"USER",true,t.getEmail_id(),t.getPhone_no(),0.0  );
		if(insert==1) {
			log.info(" New User Registered");
		}
		return t; 
	}
	
	public void update(UserEntity u) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String email = auth.getName();
		String sql = "UPDATE userEntity SET name=?,phone_no=? WHERE email_id=?";
		int upd = jdbcTemplate.update(sql,u.getName(),u.getPhone_no(),email);
		if(upd==1) {
			log.info("User Profile Updated");
		}
		String password = u.getPassword();
		if(password!=null) {
			String sqlPass = "UPDATE userEntity SET password=? WHERE email_id=?";
			String encryptedPass = bcryptEncoder.encode(password);
			int updP = jdbcTemplate.update(sqlPass,encryptedPass,email);
			if(updP==1) {
				log.info("User Password Updated");
			}			
		}
//		return u;
	}

//	public void update(UserEntity u) {
//		// TODO Auto-generated method stub
//		
//	}
	
}
