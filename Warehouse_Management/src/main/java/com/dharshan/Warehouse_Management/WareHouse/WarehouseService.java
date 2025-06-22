package com.dharshan.Warehouse_Management.WareHouse;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dharshan.Warehouse_Management.admin.Product;
import com.dharshan.Warehouse_Management.admin.ProductRepo;
import com.dharshan.Warehouse_Management.exception.ResourceNotFoundException;

@Service
public class WarehouseService {
		@Autowired
		private WareHouseRepo warehouserepo;
		@Autowired
		private ProductRepo productrepo;
		
		//all product warehouse list
		public List<WarehouseModel> getWarehouse(){
			return warehouserepo.findAll();
		}
		
		//single product warehouse list
		public WarehouseModel getwarehouseById(Long id) {
			return warehouserepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Warehouse", "id", id));
		}
		// Creating Warehouse
		public WarehouseModel createWarehouseItem(WarehouseModel warehouse) {
			//check if product exists
			Product product= productrepo.findById(warehouse.getProduct().getProduct_id()).orElseThrow(
					()-> new ResourceNotFoundException("Product","id",warehouse.getProduct().getProduct_id()));
				
			
			warehouse.setProduct(product);
			warehouse.setQuantity(product.getProduct_stocks());
								
			return warehouserepo.save(warehouse);
		}
		//Update Warehouse
		public WarehouseModel updateWarehouse(Long id,WarehouseModel warehouse) {
		WarehouseModel updated=warehouserepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Warehouse", "id", id));
		
			//updated.setQuantity(warehouse.getQuantity());
			updated.setLocation(warehouse.getLocation());
			return warehouserepo.save(updated);
		}
		//Delete Warehouse
		public void deleteWarehouse(Long id) {
			WarehouseModel deleted= warehouserepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Warehouse", "id", id));
			warehouserepo.delete(deleted);
		}
		// Update warehouse Stock see the orderService.java
		
		public 	void updateStock(Long productid,double qty) {
			
//			
//			WarehouseModel warehouse=warehouserepo.findById(productid).orElseThrow(()-> new ResourceNotFoundException("Warehouse", "ProductId", productid));
//		
//			//quantity deduction by buyer order
//			double newQuantity=warehouse.getQuantity() - qty;
//			System.out.println("newquantity: "+newQuantity);
//			if(newQuantity <0) {
//				throw new RuntimeException("Insufficient Stock: "+productid);
//			}
//			warehouse.setQuantity(newQuantity);
//			warehouserepo.save(warehouse);
//			
//			//Update Product Stock
			
			//Product product=productrepo.findById(productid).orElseThrow(() -> new ResourceNotFoundException("Product", "Id", productid));
			
			
		}
	
	

}
