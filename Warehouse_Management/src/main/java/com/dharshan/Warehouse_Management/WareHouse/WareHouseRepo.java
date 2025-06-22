package com.dharshan.Warehouse_Management.WareHouse;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WareHouseRepo  extends JpaRepository<WarehouseModel, Long>{
	
	//Optional<WarehouseModel> findByUserId(@Param("product_id") Long product_id);

}
