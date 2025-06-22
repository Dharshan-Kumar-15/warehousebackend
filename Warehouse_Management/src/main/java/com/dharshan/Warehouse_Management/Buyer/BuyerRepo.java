package com.dharshan.Warehouse_Management.Buyer;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dharshan.Warehouse_Management.Orders.ProductOrders;
import com.dharshan.Warehouse_Management.userLogin.User;




@Repository
public interface BuyerRepo extends JpaRepository<Buyer, Long> {

	 @Query("SELECT b FROM Buyer b WHERE b.user.user_id = :user_id")
	    Optional<Buyer> findByUserId(@Param("user_id") Long user_id);
	
//	Optional<Buyer> findByUserId(Long user_id);
	 




	

}
