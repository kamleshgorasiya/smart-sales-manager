package com.smart.sales.manager.request.model;

import javax.persistence.Column;
import javax.validation.constraints.Size;

public class UserDto {

   
    private long id;
    
    @Size(min = 2, max = 64, message="length.twoto64")
 	private String fullName;
   
    @Size(min = 2, max = 64, message="length.twoto64")
    private String username;
    
    @Size(min = 2, max = 64, message="length.twoto64")
    private String password;
    
    @Size(min = 2, max = 64, message="length.twoto64")
    private String email;
    
    @Size(min = 8, max = 14, message="length.eightto14")
    private String mobile;

    private boolean is_active;
    
    private String role="USER";
    
	private long dob;
    
    
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * @return the is_active
	 */
	public boolean isIs_active() {
		return is_active;
	}
	/**
	 * @param is_active the is_active to set
	 */
	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}
	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}
	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}
	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	/**
	 * @return the dob
	 */
	public long getDob() {
		return dob;
	}
	/**
	 * @param dob the dob to set
	 */
	public void setDob(long dob) {
		this.dob = dob;
	}
	    
	    
 
   
    
}
