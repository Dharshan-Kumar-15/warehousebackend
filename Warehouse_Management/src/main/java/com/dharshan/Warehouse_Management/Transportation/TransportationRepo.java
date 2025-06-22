package com.dharshan.Warehouse_Management.Transportation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransportationRepo  extends JpaRepository<Transportation, Long>{

	List<Transportation> findByMethod(TransportMethod method);

}
