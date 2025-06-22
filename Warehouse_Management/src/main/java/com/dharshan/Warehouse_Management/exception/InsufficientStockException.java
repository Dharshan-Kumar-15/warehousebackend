package com.dharshan.Warehouse_Management.exception;

public class InsufficientStockException extends RuntimeException {
	
	 private final int availableStock;
	  
	 public InsufficientStockException(String productName, int availableStock) {
		 super(String.format("Only %d items available for %s ", availableStock, productName));
		 this.availableStock=availableStock;
	 }
	 
	 public int getAvailableStock() {
		 return availableStock;
	 }

}
