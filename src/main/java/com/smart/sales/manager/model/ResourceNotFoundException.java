package com.smart.sales.manager.model;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception {
	
	public ResourceNotFoundException(String exception) {
	    super(exception);
	  }

}
