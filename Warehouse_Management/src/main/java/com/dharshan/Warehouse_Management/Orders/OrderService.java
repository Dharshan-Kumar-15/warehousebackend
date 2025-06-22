package com.dharshan.Warehouse_Management.Orders;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dharshan.Warehouse_Management.Buyer.Buyer;
import com.dharshan.Warehouse_Management.Buyer.BuyerRepo;
import com.dharshan.Warehouse_Management.WareHouse.WareHouseRepo;
import com.dharshan.Warehouse_Management.WareHouse.WarehouseService;
import com.dharshan.Warehouse_Management.admin.Product;
import com.dharshan.Warehouse_Management.admin.ProductRepo;
import com.dharshan.Warehouse_Management.admin.ProductService;
import com.dharshan.Warehouse_Management.exception.ResourceNotFoundException;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepo orderrepo;
	@Autowired
	private BuyerRepo buyerrepo;
	@Autowired
	private ProductRepo productrepo;
	@Autowired
	private ProductService productservice;
	
	
	@Autowired
	private OrderItemRepo orderitemrepo;
	
	//get all orders
	public List<ProductOrders> getAllOrders(){
		return orderrepo.findAll();
	}
	
	//get order by id
	public ProductOrders getByIdOrders(Long id) {
		return orderrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order", "id", id));
	}
	
	//create Orders
	public ProductOrders createOrders(ProductOrders orders) {
		
		//check if buyer exists
		Buyer buyer=buyerrepo.findById(orders.getBuyer().getBuyer_id()).orElseThrow(
				() -> new ResourceNotFoundException("Buyer", "id",orders.getBuyer().getBuyer_id()));
		
		orders.setBuyer(buyer);
		
		
		// Calculate total amount and validate stock	
		
		double totalAmount=0;
		
		for(OrderItem item: orders.getItems()) {
			item.setOrder(orders);
			Product product=productrepo.findById(item.getProduct().getProduct_id()).orElseThrow(
					() -> new ResourceNotFoundException("Product", "id", item.getProduct().getProduct_id()));
			
			
			if(product.getProduct_stocks() < item.getQuantity()) {
				 throw new RuntimeException("Insufficient stocks for products: "+product.getProduct_name());
			}
			
			item.setPrice(product.getProduct_price());
			totalAmount= totalAmount+ product.getProduct_price() *item.getQuantity();
			//update product stock	
			//warehouseservice.updateStock(product.getProduct_id(), item.getQuantity());
			productservice.updateStock(product.getProduct_id(), item.getQuantity());
			
		}
		
		orders.setTotalAmount(totalAmount);
		orders.setStatus(OrderStatus.PENDING);
		
		return orderrepo.save(orders);
	}
	
	
	//update order status
	public ProductOrders updateOrderStatus(Long id, String status) {
		ProductOrders updated= orderrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order", "id", id));
		
		try {
			OrderStatus	 orderStatus= OrderStatus.valueOf(status.toUpperCase());
			updated.setStatus(orderStatus);
			return orderrepo.save(updated);
		}
		catch(IllegalArgumentException ie) {
			throw new RuntimeException("Invalid order status:"+ status);
		}
	}

	// get orders by buyer
	public List<ProductOrders> getOrdersByBuyer(Long buyerId){
			System.out.println("getorder BuyerID: "+buyerId);
		Buyer buyer=buyerrepo.findById(buyerId).orElseThrow(() -> new ResourceNotFoundException("Buyer", "ID", buyerId));
		return orderrepo.findByBuyer(buyer);
	}
	public void deleteOrder(Long orderItemId) {
		System.out.println("hello from delete");
		
		ProductOrders orders=orderrepo.findById(orderItemId).orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderItemId));
		
		orderrepo.delete(orders);
	}
	
	public void deleteOrderItem(Long orderItemId) {
		System.out.println("hello from delete");
		OrderItem orders=orderitemrepo.findById(orderItemId).orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderItemId));
		//ProductOrders orders=orderrepo.findById(orderItemId).orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderItemId));
		orderitemrepo.delete(orders);
		//orderrepo.delete(orders);
	}
	
	public OrderItem getOrderItem(Long id){
		return orderitemrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order", "id", id));
		}
		
}
