package com.dharshan.Warehouse_Management.WareHouse;

import java.time.LocalDateTime;

import com.dharshan.Warehouse_Management.admin.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name="warehouse_db")
public class WarehouseModel {
	
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		@OneToOne
		@JoinColumn(name="product_id",nullable=false)
		private Product product;
		@Column
		private double quantity;
		@Column
		private String location;
		@Column
		private LocalDateTime last_updated;
		
		@PrePersist
		@PreUpdate
		protected void onUpdate() {
			last_updated=LocalDateTime.now();
		}
		//Constructor
		public WarehouseModel() {}
		public WarehouseModel( Product product, double quantity, String location, LocalDateTime last_updated) {
						
			this.product = product;
			this.quantity = quantity;
			this.location = location;
			this.last_updated = last_updated;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
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

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		public LocalDateTime getLast_updated() {
			return last_updated;
		}

		public void setLast_updated(LocalDateTime last_updated) {
			this.last_updated = last_updated;
		}
		
		
		
		

}
