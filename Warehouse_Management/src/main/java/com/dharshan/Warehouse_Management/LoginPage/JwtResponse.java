package com.dharshan.Warehouse_Management.LoginPage;

import java.util.List;

public class JwtResponse {
	private Long user_id;
	private String token;
	private List<String> roles;
	private String username;
	private String email;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	private String type="Bearer";
	
	public JwtResponse(String token,List<String> roles,Long user_id,String username,String email) {
		this.token=token;
		this.roles=roles;
		this.user_id=user_id;
		this.username=username;
		this.email=email;
	}
	// Geter and Setter
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
	

}
