package com.dharshan.Warehouse_Management.LoginPage;

import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SignUpRequest {
	
	@NotBlank
	@Size(min=3,max=20)
	private String username;
	
	@NotBlank
	@Email
	@Size(max=50)
	private String email;
	
	@NotBlank
	@Size(min=6,max=30)
	private String password;
	
	
	private Set<String> roles;
	
	//Getter and Setter

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
	

}
