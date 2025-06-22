package com.dharshan.Warehouse_Management.Orders;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dharshan.Warehouse_Management.Buyer.Buyer;



public interface OrderRepo  extends JpaRepository<ProductOrders,Long>{

	List<ProductOrders> findByBuyer(Buyer buyer);

}
