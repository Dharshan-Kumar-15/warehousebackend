package com.dharshan.Warehouse_Management.userLogin;


import java.util.HashSet;
import java.util.Set;



import com.dharshan.Warehouse_Management.Roles.Roles;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name="users", uniqueConstraints = {
		@UniqueConstraint(columnNames = "username"),
		@UniqueConstraint(columnNames = "email")
})
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long user_id;
	
	@Column(nullable = false,length = 50)
	private String username;
	
	@Column(nullable = false,length = 100)
	private String email;
	@Column(nullable = false,length = 100)
	private String password;
	
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name="users_roles",
			joinColumns = @JoinColumn(name="user_id"),
			inverseJoinColumns = @JoinColumn(name="roles_id")
			)
	@Column
	private Set<Roles> roles=new HashSet<>();
	
	
	//Constructors
		public User() {}
		
	public User( String username, String email, String password, Set<Roles> roles) {
		
		
		this.username = username;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	public User(String username, String email, String password) {
		this.username=username;
		this.email=email;
		this.password=password;
	}

	///Getter and setter
	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

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

	public Set<Roles> getRoles() {
		return roles;
	}
	
	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}
	
	
	
	
	

}
