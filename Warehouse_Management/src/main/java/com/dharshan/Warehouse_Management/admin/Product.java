package com.dharshan.Warehouse_Management.admin;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name="products")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long product_id;
	
	private String product_name;
	
	private String product_Desc;
	
	private double product_price;
	
	private double product_stocks;
	
	@Column(name="image_name")	
	private String image_name;
	@Column(name="image_type")
	private String image_type;
	@Lob
	@Column(name="image_data",columnDefinition = "LONGBLOB")
	private byte[] image_data;

	//Constructor
	public Product() {}
	public Product(String product_name, String product_Desc, double product_price, double product_stocks) {
			
		
			this.product_name = product_name;
			this.product_Desc = product_Desc;
			this.product_price = product_price;
			this.product_stocks = product_stocks;
		}
	
	public Product( String product_name, String product_Desc, double product_price,
			double product_stocks, String image_name, String image_type, byte[] image_data) {
		
		
		this.product_name = product_name;
		this.product_Desc = product_Desc;
		this.product_price = product_price;
		this.product_stocks = product_stocks;
		this.image_name = image_name;
		this.image_type = image_type;
		this.image_data = image_data;
	}
	// getter and setter
	public Long getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_Desc() {
		return product_Desc;
	}
	public void setProduct_Desc(String product_Desc) {
		this.product_Desc = product_Desc;
	}
	public double getProduct_price() {
		return product_price;
	}
	public void setProduct_price(double product_price) {
		this.product_price = product_price;
	}
	public double getProduct_stocks() {
		return product_stocks;
	}
	public void setProduct_stocks(double product_stocks) {
		this.product_stocks = product_stocks;
	}
//	//IMAGE GETTER AND SETTER
	public String getImage_name() {
		return image_name;
	}
	public void setImage_name(String image_name) {
		this.image_name = image_name;
	}
	public String getImage_type() {
		return image_type;
	}
	public void setImage_type(String image_type) {
		this.image_type = image_type;
	}
	public byte[] getImage_data() {
		return image_data;
	}
	public void setImage_data(byte[] image_data) {
		this.image_data = image_data;
	}
	
	
		
}
