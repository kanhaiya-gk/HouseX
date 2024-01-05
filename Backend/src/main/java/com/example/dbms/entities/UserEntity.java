package com.example.dbms.entities;

public class UserEntity {
	
	private String email_id;
	
	private String name;
	
	private String password;
	
//	private Collection<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();
	
	private String role;
	
	private boolean enabled;

	private String phone_no;

	private Double rating;
	
	// super constructor
	public UserEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	
	
	public UserEntity(String name, String password, String role, boolean enabled, String email_id, String phone_no,
			Double rating) {
		super();
		this.name = name;
		this.password = password;
		this.role = role;
		this.enabled = enabled;
		this.email_id = email_id;
		this.phone_no = phone_no;
		this.rating = rating;
	}



	//getter-setter
	
	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}

//	public Collection<GrantedAuthority> getGrantedAuthoritiesList() {
//      return grantedAuthoritiesList;
//   }
//   public void setGrantedAuthoritiesList(Collection<GrantedAuthority> grantedAuthoritiesList) {
//      this.grantedAuthoritiesList = grantedAuthoritiesList;
//   }


	public boolean isEnabled() {
		return enabled;
	}



	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	
	public String getPhone_no() {
		return phone_no;
	}

	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}

	public Double getRating() {
		return rating;
	}
	public void setRating(Double rating) {
		this.rating = rating;
	}
	@Override
    public String toString() {
        return this.email_id + "/" + this.password;
    }


}
