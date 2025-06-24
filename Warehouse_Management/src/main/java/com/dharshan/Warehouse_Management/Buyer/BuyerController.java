package com.dharshan.Warehouse_Management.Buyer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.dharshan.Warehouse_Management.Orders.OrderItem;
import com.dharshan.Warehouse_Management.Orders.OrderService;
import com.dharshan.Warehouse_Management.Orders.ProductOrders;
import com.dharshan.Warehouse_Management.Roles.EnumRoles;
import com.dharshan.Warehouse_Management.Roles.Roles;
import com.dharshan.Warehouse_Management.userLogin.User;

@RestController
@RequestMapping("/buyer")
@CrossOrigin(origins = "http://localhost:4200")
//@PreAuthorize("hasRole('BUYER')")
public class BuyerController {
	// Buyer and Order Controller 2um ethula iruku
	@Autowired
	private BuyerService buyerservice;
	@Autowired
	private OrderService orderservice;
	
	//Buyer Controller
	
	@GetMapping("/profile")
	public List<Buyer> getAllBuyer(){
		return buyerservice.getAllBuyers();
	}
	
	//get buyer by buyer id
	@GetMapping("/profiles/{buyerId}")
	public ResponseEntity<Buyer> getBuyerById(@PathVariable Long buyerId){
		 Buyer buyer=buyerservice.getByIdBuyer(buyerId);
		 return ResponseEntity.ok(buyer);
	}
	
	@GetMapping("/profile/{user_id}")
	public ResponseEntity<Buyer> getBuyerUserId(@PathVariable Long user_id){
		
		Buyer buyer=buyerservice.getByUserId(user_id);
		
		return ResponseEntity.ok(buyer);
	}
	
	//create Buyer 
	@PostMapping("/createprofile")
	public ResponseEntity<Buyer> createBuyer(@RequestBody Buyer buyer) {
				
		return  ResponseEntity.ok(buyerservice.createBuyer(buyer));
	}
	
	//Update Buyer
	@PutMapping("/updateprofile/{buyerId}")
	public  ResponseEntity<Buyer> updateBuyer(@PathVariable Long buyerId,@RequestBody Buyer buyer){
		Buyer update= buyerservice.updateBuyer(buyerId, buyer);
		 return ResponseEntity.ok(update);
	}
	///Orders Controllers
	// get orders by buyer id
	@GetMapping("/buyerorders/{buyerId}")
	public ResponseEntity<List<ProductOrders>> getOrderByBuyer(@PathVariable Long buyerId){
			List<ProductOrders> orders=orderservice.getOrdersByBuyer(buyerId);
			return ResponseEntity.ok(orders);
	}
	// Get all Orders
	@GetMapping("/orders")
	public List<ProductOrders> getAllOrders(){
		System.out.println("Get all ORder:  ");
		return orderservice.getAllOrders();
	}
	//Get order by Order_ID
	@GetMapping("/orders/{order_id}")
	public ResponseEntity<ProductOrders> getOrderById(@PathVariable long order_id){
		System.out.println("Get all ORderID:  ");
		ProductOrders orders=orderservice.getByIdOrders(order_id);
		return ResponseEntity.ok(orders);
	}
	
	//create orders by buyer
	@PostMapping("/createorder")
	public ResponseEntity<ProductOrders> createOrders(@RequestBody ProductOrders orders){
		System.out.println("Incoming Order Request:");
	    		
		ProductOrders created=orderservice.createOrders(orders);
		
			
		return ResponseEntity.ok(created);
	}
	//get order details by id
	@GetMapping("/orders/details/{id}")
	public ResponseEntity<ProductOrders> getOrderDetails(@PathVariable Long id){
		System.out.println("OrderDetails: "+id);
		return ResponseEntity.ok(orderservice.getByIdOrders(id));
	}
	
	@PutMapping("/orderstatus/{id}")
	public ResponseEntity<ProductOrders> updateOrderStatus(@PathVariable Long id, @RequestBody String status) {
			
		
		return  ResponseEntity.ok(orderservice.updateOrderStatus(id, status));
	}
	@DeleteMapping("/deleteorders/{orderItemId}")
	public ResponseEntity<?> deleteOrder(@PathVariable Long orderItemId){
		System.out.println("Check from order");
		orderservice.deleteOrder(orderItemId);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/deleteordersitem/{orderItemId}")
	public ResponseEntity<?> deleteOrderItem(@PathVariable Long orderItemId){
		System.out.println("Check from order");
		orderservice.deleteOrderItem(orderItemId);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/getorderitem/{order_id}")
	public ResponseEntity<OrderItem> getOrderItemId(@PathVariable long order_id){
		System.out.println("Get all ORderITEMID:  ");
		
		return ResponseEntity.ok(orderservice.getOrderItem(order_id));
	}
	

}
