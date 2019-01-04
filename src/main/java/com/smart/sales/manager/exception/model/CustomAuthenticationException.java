package com.smart.sales.manager.exception.model;

import org.springframework.security.core.AuthenticationException;

public class CustomAuthenticationException  extends AuthenticationException{
	public CustomAuthenticationException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}
	
}

