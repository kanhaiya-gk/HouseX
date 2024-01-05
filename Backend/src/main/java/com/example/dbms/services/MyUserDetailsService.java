package com.example.dbms.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.dbms.dao.UserEntityDao;
import com.example.dbms.dao.UserRoleDao;
import com.example.dbms.entities.UserEntity;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserEntityDao userEntityDao;
	
	@Autowired
	private UserRoleDao userRoleDao;
	
//	@Bean
//	public BCryptPasswordEncoder bCryptEncoder() {
//		return new BCryptPasswordEncoder();
//	}
	
////	@Autowired
//	private BCryptPasswordEncoder bCryptEncoder;
	
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String email_id) throws UsernameNotFoundException {
		UserEntity userEntity = this.userEntityDao.findUserAccount(email_id);
		if(userEntity==null) {
			System.out.println("User not found! " + email_id);
			throw new UsernameNotFoundException("User " + email_id + " was not found in the database");
		}
		
		System.out.println("Found User: " + userEntity);
		
		List<String> roleNames = this.userRoleDao.getRoleNames(userEntity.getEmail_id());
		
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roleNames != null) {
            for (String role : roleNames) {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }
		
		UserDetails userDetails = (UserDetails) new User(userEntity.getEmail_id(), //
                userEntity.getPassword(), grantList);

        return userDetails;	
	}
	
	public UserEntity save(UserEntity user) {
		UserEntity newUser = new UserEntity();
		newUser.setEmail_id(user.getEmail_id());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setName(user.getName());
		newUser.setEnabled(true);
		newUser.setPhone_no(user.getPhone_no());
		newUser.setRating(0.0);
		newUser.setRole("USER");
		return userEntityDao.save(newUser);
	}
	
	public UserEntity getMyProfile(String email_id) {
		return userEntityDao.findUserAccount(email_id);
	}
	
	public void updateMyProfile(UserEntity u) {
		userEntityDao.update(u);
	}
	
}
