package com.dharshan.Warehouse_Management.Transportation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/transport")
@CrossOrigin(origins = "http://localhost:4200")
//@PreAuthorize("hasRole('TRANSPORTATION')")
public class TransportController {
	
	@Autowired
	private TransportationService service;
	
	//Get all Shipments
	@GetMapping("/shipments")
	public List<Transportation> getAllTransports(){
		return service.getallTransports();
	}
	
	//Get by Id shipment
	@GetMapping("/shipments/{id}")
	public ResponseEntity<Transportation> getByIdTransport(@PathVariable Long id) {
		System.out.println("INside of GEttransportID");
		Transportation transport=service.getByIdTransport(id);
		return ResponseEntity.ok(transport);
	}
	
	//create Transport
	@PostMapping("/createtransport")
	public ResponseEntity<Transportation> createTransport(@RequestBody Transportation transport){
		System.out.println("INside of CreatetransportID");
		Transportation created=service.createTransport(transport);
		return ResponseEntity.ok(created);
	}
	//update Transport
	@PutMapping("/updatetransport/{id}")
	public ResponseEntity<Transportation> updateTransport(@PathVariable Long id,@RequestBody Transportation transport){
		Transportation updated=service.updateTransport(id, transport);
		return ResponseEntity.ok(updated);
	}
	
	//Delete Transport
	@DeleteMapping("/deletetransport/{id}")
	public ResponseEntity<?> deleteTransport(@PathVariable Long id){
		service.deleteTransport(id);
		return ResponseEntity.ok().build();
	}
	//Update Status Transport
	@PutMapping("/updatestatus/{id}")
	
	public ResponseEntity<?> updateStatusTransport( @PathVariable Long id, @RequestBody String status){
		System.out.println("controller Id transport :"+ id);
		String updatedStatus=status.toUpperCase();
		return ResponseEntity.ok(service.updateStatusTransport(id, updatedStatus));
	}
	
	@GetMapping("/getmethod/{method}")
	public List<Transportation> getByMethod(@PathVariable String method) {
		TransportMethod transportMethod=TransportMethod.valueOf(method.toUpperCase());
		return service.getByMethodTransport(transportMethod);
	}
	
	

}
