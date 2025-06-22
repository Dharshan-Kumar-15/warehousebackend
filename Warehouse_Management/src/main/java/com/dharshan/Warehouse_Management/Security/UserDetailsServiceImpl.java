package com.dharshan.Warehouse_Management.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dharshan.Warehouse_Management.userLogin.User;
import com.dharshan.Warehouse_Management.userLogin.UserRepo;



@Service
public class UserDetailsServiceImpl implements UserDetailsService{
		@Autowired
		UserRepo userrepo;

		@Override
		@Transactional
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			// TODO Auto-generated method stub
			
			User user= userrepo.findByUsername(username).orElseThrow(
					()-> new UsernameNotFoundException("Username Not Found : "+username));
					
			return UserDetailsImpl.build(user);
		}
		
		
	
	

}
