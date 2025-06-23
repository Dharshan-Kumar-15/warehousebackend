package com.dharshan.Warehouse_Management.WareHouse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/warehouse")
@CrossOrigin(origins = "https://warehousefrontend-production.up.railway.app")
//@PreAuthorize("hasRole('WAREHOUSE')")
public class WarehouseController {
		@Autowired
		private WarehouseService warehouseservice;
		
		//all items
		@GetMapping("/items")
		public List<WarehouseModel> getWarehouse(){
			return warehouseservice.getWarehouse();
		}
		//single items
		@GetMapping("/items/{id}")
		public ResponseEntity<WarehouseModel> getMethodName(@PathVariable Long id) {
				return ResponseEntity.ok(warehouseservice.getwarehouseById(id));
		}
		//creating warehouse
		@PostMapping("/createwarehouse")
		public ResponseEntity<WarehouseModel> createWarehouseItems(@RequestBody WarehouseModel warehouse){
			return ResponseEntity.ok(warehouseservice.createWarehouseItem(warehouse));
		}
		//updating warehouse
		@PutMapping("/updatewarehouse/{id}")
		public ResponseEntity<WarehouseModel> updateWarehouseItem(@PathVariable Long id, @RequestBody WarehouseModel warehouse) {
			
			
			return ResponseEntity.ok(warehouseservice.updateWarehouse(id, warehouse)) ;
		}
		
		//Delete Warehouse
		@DeleteMapping("/deletewarehouse/{id}")
		public ResponseEntity<?> deleteWarehouse(@PathVariable Long id){
			warehouseservice.deleteWarehouse(id);
			return ResponseEntity.ok().build();
		}
		
		
		
}
