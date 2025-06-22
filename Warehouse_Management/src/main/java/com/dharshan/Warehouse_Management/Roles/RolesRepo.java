package com.dharshan.Warehouse_Management.Roles;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepo extends JpaRepository<Roles,Integer> {
		
		Optional<Roles> findByName(EnumRoles role);
}
