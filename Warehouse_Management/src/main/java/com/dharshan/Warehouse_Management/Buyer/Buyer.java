package com.dharshan.Warehouse_Management.Buyer;

import com.dharshan.Warehouse_Management.userLogin.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity	
@Table(name="buyers")
public class Buyer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long buyer_id;
	
	@OneToOne
	@JoinColumn(name="user_id",nullable = false)
	private User user;
	 
	
	@Column(nullable = false,length = 100)
	private String name;
	
	@Column(nullable = false, columnDefinition = "TEXT")
	private String address;
	
	@Column(nullable = false,length = 20)
	private String phone;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private PaymentMode paymentMode;
	
	//Constructors
	public Buyer() {}
	
	public Buyer( String name, String address, String phone, PaymentMode paymentMode) {
		
			
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.paymentMode = paymentMode;
	}
	//Getter and setter
	

	public Long getBuyer_id() {
		return buyer_id;
	}

	public void setBuyer_id(Long buyer_id) {
		this.buyer_id = buyer_id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public PaymentMode getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(PaymentMode paymentMode) {
		this.paymentMode = paymentMode;
	}
	
	
	
	
	
	

}
