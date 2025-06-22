package com.dharshan.Warehouse_Management.userLogin;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
	
	Optional<User>  findByUsername(String username);
	
	Boolean existsByUsername(String username);
	
	Boolean existsByEmail(String email);

}
