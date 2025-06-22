package com.dharshan.Warehouse_Management.Orders;

import com.dharshan.Warehouse_Management.admin.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="order_items")
public class OrderItem {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long orderItemId;
	
	@ManyToOne
	@JoinColumn(name="order_id",nullable = false)
	@JsonIgnore
	private ProductOrders order;
	
	@ManyToOne
	@JoinColumn(name="product_id", nullable = false)
	private Product product;
	
	@Column(nullable = false)
	private double quantity;
	
	@Column(nullable = false)
	private double price;

	public OrderItem() {}

	//getter and setter
	
	public OrderItem( ProductOrders order, Product product, double quantity, double price) {
		
		
		this.order = order;
		this.product = product;
		this.quantity = quantity;
		this.price = price;
	}

	public Long getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Long orderItemId) {
		this.orderItemId = orderItemId;
	}

	public ProductOrders getOrder() {
		return order;
	}

	public void setOrder(ProductOrders order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
	
	
	
	
	

}
