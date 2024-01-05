package com.example.dbms.dao;

import java.util.List;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserRoleDao {

	private static final Logger log = LoggerFactory.getLogger(UserRoleDao.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<String> getRoleNames(String email_id) {
        String sql = "Select r.role " //
                + " from userEntity r " //
                + " where r.email_id = ? ";

        Object[] params = new Object[] { email_id };

        List<String> roles = jdbcTemplate.queryForList(sql,String.class ,params);

        return roles;
    }
	
}
