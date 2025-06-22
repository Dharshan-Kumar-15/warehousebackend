package com.dharshan.Warehouse_Management.Transportation;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dharshan.Warehouse_Management.exception.ResourceNotFoundException;

@Service
public class TransportationService {
	
	@Autowired
	private TrackingNumberGeneration trackingnumber;
	
	@Autowired
	private TransportationRepo trans_repo;
	
	//Get all Transports
	public List<Transportation> getallTransports(){
		return trans_repo.findAll();
	}
	
	//Get by id transports
	public Transportation getByIdTransport(Long id) {
		return trans_repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Transportation", "id",id));
	}
	// Create Transport
	
	public Transportation createTransport(Transportation transport) {
		if(transport.getTrackingNumber() == null) {
			transport.setTrackingNumber(trackingnumber.generate());
		}
		transport.setStatus(TransportStatus.PREPARING);
		return trans_repo.save(transport);
	}
	//update Transport
	
	public Transportation updateTransport(Long id,Transportation transport) {
		Transportation updated=trans_repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Transportation", "id",id));
		updated.setMethod(transport.getMethod());
		
		updated.setEstimatedDelivery(transport.getEstimatedDelivery());
		
		return trans_repo.save(updated);		
	}
	//delete Transport
	public void deleteTransport(Long id) {
	 Transportation deleted=trans_repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Transportation", "id",id));
	 trans_repo.delete(deleted);
	}
	//Update Transport Status
	public Transportation updateStatusTransport(Long id, String status) {
		System.out.println("Trnas id:"+id);
		Transportation updateStatus=trans_repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Transportation", "id",id));
		TransportStatus sts=TransportStatus.valueOf(status.toUpperCase());
		updateStatus.setStatus(sts);
		if(sts == TransportStatus.DELIVERED) {
			updateStatus.setActualDelivery(LocalDate.now());
		}
		System.out.println("Last step");
		return trans_repo.save(updateStatus);
	}
	// Get Transport by Method
	public List<Transportation> getByMethodTransport(TransportMethod method){
		
		return trans_repo.findByMethod(method);
	}
	
	
	
	
	
	
}
