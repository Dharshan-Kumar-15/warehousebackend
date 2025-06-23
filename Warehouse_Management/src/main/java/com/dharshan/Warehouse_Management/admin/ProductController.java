package com.dharshan.Warehouse_Management.admin;



import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "https://warehousefrontend-production.up.railway.app")
//@PreAuthorize("hasRole('ADMIN')")
public class ProductController {
	
		@Autowired
		public ProductService service;
	
		
		//get all products
		@GetMapping("/getproduct")
		public List<Product> getProducts(){
			System.out.println("Inside get product");
			return service.getProduct();
		}
		// Get By Product ID 
		@GetMapping("/getproduct/{product_id}")
		public ResponseEntity<Product> getProductById(@PathVariable Long product_id){
				return ResponseEntity.ok(service.getProductById(product_id));
		}
		
//		@PostMapping(value="/createproduct")  
//		public ResponseEntity<Product> createProduct(@RequestBody Product product){
//			Product products=service.saveProduct(product);
//			return ResponseEntity.ok(products);
//		}
//		
//		
//		@PutMapping(value="/updateproduct/{product_id}")
//		public ResponseEntity<Product> updateProduct(@PathVariable Long product_id,@RequestBody Product product){
//			Product Update= service.updateProduct(product, product_id);
//			return ResponseEntity.ok(Update);
//		}
//		
		
		
						
		//Single Data Creation with image
		@PostMapping(value="/createproduct",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)  // Images
		public ResponseEntity<Product> createProduct(@RequestPart("product")String productJson,
				@RequestPart("imageFile") MultipartFile imageFile,ObjectMapper mapper) throws JsonMappingException, JsonProcessingException  {
			Product product=mapper.readValue(productJson, Product.class);
			Product newProduct=service.saveProduct(product,imageFile);
					       		
			return  ResponseEntity.ok(newProduct);
			
		}
		
			
		// Update Product
		@PutMapping(value="/updateproduct/{product_id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
		public ResponseEntity<Product> updateProduct(@PathVariable Long product_id,
													@RequestPart("product") String productJson,
													@RequestPart(value="imageFile" ,required = false) MultipartFile imageFile,
													ObjectMapper mapper) throws JsonMappingException, JsonProcessingException{
			Product product=mapper.readValue(productJson, Product.class);
													
				Product Update= service.updateProduct(product, product_id,imageFile);
				
				return ResponseEntity.ok(Update);
		}
			
		
		//delete by Id
		@DeleteMapping("/deleteproduct/{product_id}")
		public ResponseEntity<?> deleteProduct(@PathVariable Long product_id){
			 service.deleteProduct(product_id);
			 return ResponseEntity.ok().build();
		}
		
		
		
		
		//Multiple Data Creation
				@PostMapping("/multipleproduct" )
				public ResponseEntity<List<Product>> multipleProduct(@RequestBody List<Product> product){
					List<Product> newProduct=service.saveAllProduct(product);
					return ResponseEntity.ok(newProduct);
				}
		
		//Delete ALL Product
		
		@DeleteMapping("/deleteall")
		public ResponseEntity<String> deleteAllProduct(@RequestBody List<Long> product_id){
			try {
			
			service.deleteAllProduct(product_id);
			return ResponseEntity.ok().body("Deleted All Products");
			}
			catch(Exception e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
			}
		}
		
		// Get image by product id
		/*
				@GetMapping("/getimage/{product_id}")
				public ResponseEntity<byte[]> getProductImage(@PathVariable Long product_id){
						
					return service.getProductImage(product_id);
				}
		
		@PostMapping("/test-upload")
		public String testUpload(@RequestPart MultipartFile file) {
		    return "Received file: " + file.getOriginalFilename();
		}
		*/
	
}
