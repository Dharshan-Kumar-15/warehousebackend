package com.dharshan.Warehouse_Management.exception;

public class ResourceNotFoundException extends RuntimeException{
	
		public ResourceNotFoundException(String resource,String field, Object value) {
			super(String.format("%s not found with  %s:  %s",resource,field,value));
		}

}
