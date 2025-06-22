package com.dharshan.Warehouse_Management.userLogin;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dharshan.Warehouse_Management.LoginPage.SignUpRequest;
import com.dharshan.Warehouse_Management.Roles.EnumRoles;
import com.dharshan.Warehouse_Management.Roles.Roles;
import com.dharshan.Warehouse_Management.Roles.RolesRepo;

@Service
public class UserService {
	@Autowired
	private UserRepo userrepo;
	
	@Autowired
	private RolesRepo rolesrepo;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	
		public User getUser(Long id) {
			return userrepo.findById(id).orElseThrow(()-> new RuntimeException("Error to get User from User"));
		}
		
		
	public User registerUser(SignUpRequest signuprequest) {
		if(userrepo.existsByUsername(signuprequest.getUsername())) {
			throw new RuntimeException("Error: Username is already taken");
		}
		
		if(userrepo.existsByEmail(signuprequest.getEmail())) {
			throw new RuntimeException("Error: Email is already in use");
		}
		
		User user=new User(signuprequest.getUsername(),
				signuprequest.getEmail(),
				passwordEncoder.encode(signuprequest.getPassword()));
		
		Set<String> strRoles =signuprequest.getRoles();
		
		Set<Roles> roles= new HashSet<>();
		
		
		if(strRoles == null) {
			
				Roles buyerRole= rolesrepo.findByName(EnumRoles.BUYER).orElseThrow(
						()-> new RuntimeException("ErrorS: Role is Not Found"));
				roles.add(buyerRole);
				System.out.println(roles);
		}
		else {
			strRoles.forEach(role->{
				
				switch(role) {
				
				case "ADMIN": 
					
						Roles adminRole=rolesrepo.findByName(EnumRoles.ADMIN).orElseThrow(
								
						()-> new RuntimeException("ErrorAdmin: Role is Not Found"));
						System.out.println(adminRole);
						roles.add(adminRole);
						System.out.println(roles);
						
						break;
				case "WAREHOUSE":
					Roles warehouseRole=rolesrepo.findByName(EnumRoles.WAREHOUSE).orElseThrow(
							
							()-> new RuntimeException("ErrorWareHouse: Role is Not Found"));
							roles.add(warehouseRole);
							
							break;
				
				case "TRANSPORTATION":
					
						Roles transportRole=rolesrepo.findByName(EnumRoles.TRANSPORTATION).orElseThrow(
							
							()-> new RuntimeException("ErrorTransport: Role is Not Found"));
							roles.add(transportRole);
							
							break;
							
				case "BUYER":
					
					Roles buyerRole= rolesrepo.findByName(EnumRoles.BUYER).orElseThrow(
							()-> new RuntimeException("ErrorBuyer: Role is Not Found"));
					roles.add(buyerRole);
					System.out.println(roles);
				
				
				}
								
			});
		}
		
		user.setRoles(roles);
		
		return userrepo.save(user);
		
		
	}
	
	
	

}
