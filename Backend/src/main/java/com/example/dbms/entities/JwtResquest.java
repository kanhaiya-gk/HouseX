package com.example.dbms.entities;

import java.io.Serializable;

public class JwtResquest implements Serializable {

	private static final long serialVersionUID = 5926468583005150707L;
	
	private String email_id;
	private String password;
	
	//need default constructor for JSON Parsing
	public JwtResquest()
	{
		
	}

	public JwtResquest(String email_id, String password) {
		this.setEmail_id(email_id);
		this.setPassword(password);
	}

	public String getEmail_id() {
		return this.email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

