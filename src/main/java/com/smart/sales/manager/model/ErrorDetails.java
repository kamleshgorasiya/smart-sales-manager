package com.smart.sales.manager.model;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ErrorDetails {
	private Date timestamp;
	private String message;
	private String details;
	private int status;

	
	public ErrorDetails(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
		
	}
	public ErrorDetails(Date date, String message, String description, int status) {
		super();
		this.timestamp = date;
		this.message = message;
		this.details = description;
		this.status=status;
	}
	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}

	
	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @param details the details to set
	 */
	public void setDetails(String details) {
		this.details = details;
	}

	
}