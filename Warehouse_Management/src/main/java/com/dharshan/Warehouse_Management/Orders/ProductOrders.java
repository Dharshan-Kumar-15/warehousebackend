package com.dharshan.Warehouse_Management.Orders;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.ManyToAny;

import com.dharshan.Warehouse_Management.Buyer.Buyer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name="orders")
public class ProductOrders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long order_id;
	
	
	@ManyToOne
	@JoinColumn(name="buyer_id", nullable = false)
	private Buyer buyer;
	
	
	@Column(updatable = false)
	private LocalDateTime orderDate;
	
	@Column(nullable=false)
	private double totalAmount;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private OrderStatus status;
	
	
	@OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
	private List<OrderItem> items;
	
	@PrePersist
	protected void onCreate() {
		orderDate=LocalDateTime.now();
	}
	
	//COnstructors
	
	public ProductOrders() {}
	
	public ProductOrders( Buyer buyer, LocalDateTime orderDate, double totalAmount, OrderStatus status,
			List<OrderItem> items) {
		
		
		this.buyer = buyer;
		this.orderDate = orderDate;
		this.totalAmount = totalAmount;
		this.status = status;
		this.items = items;
	}

	//getter and setter
	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	public Buyer getBuyer() {
		return buyer;
	}

	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "ProductOrders [order_id=" + order_id + ", buyer=" + buyer + ", orderDate=" + orderDate
				+ ", totalAmount=" + totalAmount + ", status=" + status + ", items=" + items + ", getOrder_id()="
				+ getOrder_id() + ", getBuyer()=" + getBuyer() + ", getOrderDate()=" + getOrderDate()
				+ ", getTotalAmount()=" + getTotalAmount() + ", getStatus()=" + getStatus() + ", getItems()="
				+ getItems() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

	
	

}
