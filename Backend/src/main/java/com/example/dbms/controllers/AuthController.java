package com.example.dbms.controllers;

import com.example.dbms.entities.JwtResponse;
import com.example.dbms.entities.JwtResquest;
import com.example.dbms.entities.UserEntity;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dbms.config.JwtRequestFilter;
import com.example.dbms.config.JwtTokenUtil;
import com.example.dbms.services.MyUserDetailsService;


@RestController
@CrossOrigin
public class AuthController {

	
		
		@Autowired
		private AuthenticationManager authenticationManager;

		@Autowired
		private JwtTokenUtil jwtTokenUtil;
		
		@Autowired
		private MyUserDetailsService userDetailsService;
		
		@RequestMapping(value = "/", method = RequestMethod.POST)
		public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtResquest authenticationRequest) throws Exception {
			
//			System.out.println(authenticationRequest.getEmail_id() + ' ' + authenticationRequest.getPassword());
			authenticate(authenticationRequest.getEmail_id(), authenticationRequest.getPassword());

			final UserDetails userDetails = userDetailsService
					.loadUserByUsername(authenticationRequest.getEmail_id());

			final String token = jwtTokenUtil.generateToken(userDetails);
			System.out.println("Token is "+token);
			return ResponseEntity.ok(new JwtResponse(token));
		}
		
		@RequestMapping(value = "/signup", method = RequestMethod.POST)
		public ResponseEntity<?> saveUser(@RequestBody UserEntity user) throws Exception {
			return ResponseEntity.ok(userDetailsService.save(user));
		}

		private void authenticate(String email_id, String password) throws Exception {
			try {
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email_id, password));
//				System.out.println(3);
			} 
//			catch(Exception e) {
//				System.out.println(e.getMessage());
//			}
			catch (DisabledException e) {
				throw new Exception("USER_DISABLED", e);
			} catch (BadCredentialsException e) {
				throw new Exception("INVALID_CREDENTIALS", e);
			}
		}
	
}
