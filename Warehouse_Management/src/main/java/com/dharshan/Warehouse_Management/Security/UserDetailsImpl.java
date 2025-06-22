package com.dharshan.Warehouse_Management.Security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.dharshan.Warehouse_Management.userLogin.User;

public class UserDetailsImpl implements UserDetails {
	
	
	private static final long serialVersionUID=1L;
	
	private Long id;
	private String username;
	private String email;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	
	//Contructors
	public UserDetailsImpl(Long id,String username,String email,String password,Collection<? extends GrantedAuthority> authorities) {
		this.id=id;
		this.username=username;
		this.email=email;
		this.password=password;
		this.authorities=authorities;
	}
	
	public static UserDetailsImpl build(User user) {
		List<GrantedAuthority> authorities=user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority("ROLE_"+role.getName().name()))
				.collect(Collectors.toList());
				
		System.out.println("Authorities: " + authorities);
		return new UserDetailsImpl(
				user.getUser_id(),
				user.getUsername(),
				user.getEmail(),
				user.getPassword(),
				authorities			
				);
	}
	//Getters 
	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		
		return authorities;
	}
	
	public boolean isAccountNonExpired() {
		return true;
	}
	public boolean isAccountNonLockec() {
		return true;
	}
	public boolean isCredentialsNonExpired() {
		return true;
	}
	public boolean isEnabled() {
			return true;
	}

	
	
	

}
