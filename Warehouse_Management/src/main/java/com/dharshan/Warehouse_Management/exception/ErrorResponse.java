package com.dharshan.Warehouse_Management.exception;

import java.time.LocalDateTime;
import java.util.Map;

public class ErrorResponse {
	
	private final LocalDateTime timestamp;
	
	private final String message;
	
	private final int status;
	
	private Map<String, Object> details;
	
	public ErrorResponse(LocalDateTime timestamp,String message,int status,Map<String,Object> details) {
		this.timestamp=timestamp;
		this.message=message;
		this.status=status;
		this.details=details;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public int getStatus() {
		return status;
	}

	public Map<String, Object> getDetails() {
		return details;
	}
	

}
