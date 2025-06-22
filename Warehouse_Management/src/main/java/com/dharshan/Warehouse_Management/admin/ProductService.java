package com.dharshan.Warehouse_Management.admin;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dharshan.Warehouse_Management.WareHouse.WareHouseRepo;
import com.dharshan.Warehouse_Management.WareHouse.WarehouseModel;
import com.dharshan.Warehouse_Management.exception.ResourceNotFoundException;



import java.io.IOException;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;





@Service
public class ProductService {
	
		
		
		@Autowired
		private  ProductRepo repo;
//		@Autowired
//		private WareHouseRepo warehouserepo;
		
		//get all  Products List
		public List<Product> getProduct(){
			return repo.findAll();
		}
		/// get product by id
		public Product getProductById(Long product_id) {
				return repo.findById(product_id).orElseThrow(()-> new ResourceNotFoundException("Product","id",product_id));
		}
		//without image create product
//		public Product saveProduct(Product product) {
//			return repo.save(product);
//		}
//		
//		public Product updateProduct(Product product, Long product_id) {
//			Product updated=repo.findById(product_id).orElseThrow(()-> new ResourceNotFoundException("Product", "id", product_id));			
//			updated.setProduct_name(product.getProduct_name());
//			updated.setProduct_Desc(product.getProduct_Desc());
//			updated.setProduct_price(product.getProduct_price());
//			updated.setProduct_stocks(product.getProduct_stocks());
//			return repo.save(updated);
//		
//		}
		
		
		
			public 	void updateStock(Long productid,double qty) {
			
			
				Product product=repo.findById(productid).orElseThrow(()-> new ResourceNotFoundException("Product", "id", productid));
				
		
			//quantity deduction by buyer order
			double newQuantity=product.getProduct_stocks() - qty;
			System.out.println("newquantity: "+newQuantity);
			if(newQuantity <0) {
				throw new RuntimeException("Insufficient Stock: "+productid);
			}
			product.setProduct_stocks(newQuantity);
			repo.save(product);
			
			
			
//			//Update Warehouse Stock
//			WarehouseModel warehouse=warehouserepo.findByUserId(productid).orElseThrow(()-> new ResourceNotFoundException("Product", "id", productid));
//			warehouse.setQuantity(newQuantity);
//			
			
			
			
		}
		
		
		
		
		
		
//		//create Product List(only one data)    
		public Product saveProduct(Product product,MultipartFile imageFile) {
				if(imageFile !=null && !imageFile.isEmpty() ) {
					try {
						product.setImage_name(imageFile.getOriginalFilename());
						product.setImage_type(imageFile.getContentType());
						product.setImage_data(imageFile.getBytes());
					}
					catch(IOException e) {
						throw new RuntimeException("Failed to upload File: "+imageFile.getOriginalFilename(),e);
					}
				}
			
				return repo.save(product);
		}
		
		
		//Multiple product saves
		public List<Product> saveAllProduct(List<Product> product){
			
			return repo.saveAll(product);
		}
		
		
		
//		//update product by id
		public Product updateProduct(Product product, Long product_id,MultipartFile imageFile) {
			Product updated=repo.findById(product_id).orElseThrow(()-> new ResourceNotFoundException("Product", "id", product_id));			
			updated.setProduct_name(product.getProduct_name());
			updated.setProduct_Desc(product.getProduct_Desc());
			updated.setProduct_price(product.getProduct_price());
			updated.setProduct_stocks(product.getProduct_stocks());
			
			if(imageFile !=null && !imageFile.isEmpty()) {
				try {
					updated.setImage_name(imageFile.getOriginalFilename());
					updated.setImage_type(imageFile.getContentType());
					updated.setImage_data(imageFile.getBytes());			
					}
				catch(IOException e) {
					throw new RuntimeException("Failed to upload File: "+imageFile.getOriginalFilename(),e);
				}
			}
			
			
			return repo.save(updated);
					
		}
		// get Image by product id
		public ResponseEntity<byte[]> getProductImage(Long product_id){
			Product product=repo.findById(product_id).orElseThrow(()-> new ResourceNotFoundException("Product", "id", product_id));
			
			if(product.getImage_data() == null) {
				return ResponseEntity.notFound().build();
			}
			
			HttpHeaders headers=new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType(product.getImage_type()));
			headers.setContentLength(product.getImage_data().length);
			
			return new ResponseEntity<>(product.getImage_data(),headers,HttpStatus.OK);
		}
		
		//Delete Product
		public  void deleteProduct(Long product_id) {
				Product deleted=repo.findById(product_id).orElseThrow(()-> new ResourceNotFoundException("Product", "id", product_id));
				// Delete image if exists
		      				
				repo.delete(deleted);
		}
		//Delete All Product
		public  void deleteAllProduct(List<Long> product_id) throws Exception {
			List<Product> deleteAll= repo.findAllById(product_id);
			if(deleteAll.size() != product_id.size()) {
				throw new Exception("One or more users not Found");
			}
			repo.deleteAll(deleteAll);
		}
		
		
		
	
}
