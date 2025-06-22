package com.dharshan.Warehouse_Management.Orders;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepo extends JpaRepository<OrderItem, Long> {
	

}
