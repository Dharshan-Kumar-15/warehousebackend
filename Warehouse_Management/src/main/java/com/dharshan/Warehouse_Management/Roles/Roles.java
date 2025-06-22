package com.dharshan.Warehouse_Management.Roles;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="roles")
public class Roles {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int roles_id;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private EnumRoles name;
	
	public Roles() {}
//Constructor
	public Roles(int roles_id, EnumRoles name) {
		
		this.roles_id = roles_id;
		this.name = name;
	}
	//GETTER AND SETTER
	public int getRoles_id() {
		return roles_id;
	}

	public void setRoles_id(int roles_id) {
		this.roles_id = roles_id;
	}

	public EnumRoles getName() {
		return name;
	}

	public void setName(EnumRoles name) {
		this.name = name;
	}
	
	

}
