package com.dharshan.Warehouse_Management.Buyer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dharshan.Warehouse_Management.admin.Product;
import com.dharshan.Warehouse_Management.admin.ProductRepo;
import com.dharshan.Warehouse_Management.exception.ResourceNotFoundException;
import com.dharshan.Warehouse_Management.userLogin.User;
import com.dharshan.Warehouse_Management.userLogin.UserRepo;

@Service
public class BuyerService {
	
	@Autowired
	private ProductRepo productrepo;
	
	
	@Autowired
	private BuyerRepo buyerrepo;
	
	@Autowired
	private UserRepo userrepo;
	
	
	
	
	//get all buyers
	public List<Buyer> getAllBuyers() {
		return buyerrepo.findAll();
		}
	/// Get by Id of buyer
	public Buyer getByIdBuyer(Long id) {
		return buyerrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Buyer", "id", id));
	}
	
	//get by user Id of buyer
	public Buyer getByUserId(Long user_id) {
		User user=userrepo.findById(user_id).orElseThrow(()-> new ResourceNotFoundException("User", "user_id", user_id) ) ;
			
		return buyerrepo.findByUserId(user.getUser_id()).orElseThrow( ()-> new ResourceNotFoundException("User", "user_id", user_id));
	}
	
	//createBuyer
	public Buyer createBuyer(Buyer buyer) {
//			//Check if user exists 
		User user=userrepo.findById(buyer.getUser().getUser_id()).orElseThrow(
					() -> new ResourceNotFoundException("User", "id", buyer.getUser().getUser_id()));
		
		buyer.setUser(user);
			System.out.println("last check");
		return buyerrepo.save(buyer);
	}
	
	//Update Buyer
	
	public Buyer updateBuyer(Long id, Buyer buyer) {
		Buyer created=buyerrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Buyer", "id", id));
		created.setName(buyer.getName());
		created.setAddress(buyer.getAddress());
		created.setPhone(buyer.getPhone());
		created.setPaymentMode(buyer.getPaymentMode());
		return buyerrepo.save(created);
	}
	
	//deleteBuyer
	public void deleteBuyer(Long id) {
		Buyer deleted=buyerrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Buyer", "id", id));
		buyerrepo.delete(deleted);
		
	}
}
