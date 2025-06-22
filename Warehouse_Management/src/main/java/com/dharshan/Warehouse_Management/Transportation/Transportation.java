package com.dharshan.Warehouse_Management.Transportation;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.dharshan.Warehouse_Management.Orders.ProductOrders;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name="transportation")
public class Transportation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transId;
	
	@OneToOne
	@JoinColumn(name="order_id", nullable=false)
	private ProductOrders order;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false,length=10)
	private TransportMethod method;
	
	@Column(unique=true,length=100)
	private String trackingNumber;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false,length=20)
	private TransportStatus status;
	
	@Column
	private LocalDate estimatedDelivery;
	
	@Column
	private LocalDate actualDelivery;
	
	@Column(updatable =false)
	private LocalDateTime createdAt;
	
	@Column
	private LocalDateTime updatedAt;
	
	@PrePersist
	protected void onCreate() {
		createdAt=LocalDateTime.now();
		updatedAt=LocalDateTime.now();
		
	}
	@PostPersist
	protected void onUpdated() {
		updatedAt=LocalDateTime.now();
	}
	
	//Constructors
	public Transportation() {}
	
	
	public Transportation( ProductOrders order, TransportMethod method, String trackingNumber,
			TransportStatus status, LocalDate estimatedDelivery, LocalDate actualDelivery, LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		
		
		this.order = order;
		this.method = method;
		this.trackingNumber = trackingNumber;
		this.status = status;
		this.estimatedDelivery = estimatedDelivery;
		this.actualDelivery = actualDelivery;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	//getter and setter
	public Long getTransId() {
		return transId;
	}
	public void setTransId(Long transId) {
		this.transId = transId;
	}
	public ProductOrders getOrder() {
		return order;
	}
	public void setOrder(ProductOrders order) {
		this.order = order;
	}
	public TransportMethod getMethod() {
		return method;
	}
	public void setMethod(TransportMethod method) {
		this.method = method;
	}
	public String getTrackingNumber() {
		return trackingNumber;
	}
	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}
	public TransportStatus getStatus() {
		return status;
	}
	public void setStatus(TransportStatus status) {
		this.status = status;
	}
	public LocalDate getEstimatedDelivery() {
		return estimatedDelivery;
	}
	public void setEstimatedDelivery(LocalDate estimatedDelivery) {
		this.estimatedDelivery = estimatedDelivery;
	}
	public LocalDate getActualDelivery() {
		return actualDelivery;
	}
	public void setActualDelivery(LocalDate actualDelivery) {
		this.actualDelivery = actualDelivery;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	

}
