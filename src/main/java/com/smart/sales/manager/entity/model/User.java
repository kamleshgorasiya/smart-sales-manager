package com.smart.sales.manager.entity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
public class User {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	
	@Column(length=50, nullable=false)
    @Size(min = 2, max = 128, message="length.eightto128")
	@NotNull(message = "notempty")
	private String fullName;
	
	
	/*
	 * @Size(min = 2, max = 64, message="length.twoto64")
	 * 
	 * @Column(length=50, nullable=false)
	 * 
	 * private String lastName;
	 */
	
 
	@Column(unique = true,length=64, nullable=false)
	@Size(min = 2, max = 64, message="length.twoto64")
	@NotNull(message = "notempty")
	private String username;
	
	@Column(unique = true,length=64, nullable=false)
	@Size(min = 1, max = 64, message="length.twoto64")
	@NotNull(message = "notempty")
	@Email(message = "valid.format")
	private String email;
	
	@Column(unique = true,length=14, nullable=false)
	@Size(min = 8, max = 14, message="length.eightto14")
	@NotNull(message = "notempty")	
	private String mobile;
	
	
	@JsonIgnore
    @Size(min = 1, max = 255, message="length.eightto64")
	@Column(length=50, nullable=false)
	@NotNull(message = "notempty")
	private String password;
	
	@Column(length=20)
	/*
	 * @Min(value = 15000, message = "min.salary")
	 * 
	 * @Max(value = 100000, message = "max.salary")
	 */
	private long salary;
	
	@Column(length=20)
	private long dob;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "USER_ROLES", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "ROLE_ID") })
	@JsonIgnore
	private List<Role> roles;
	
	@Column(length=20)
	private long business_id;
	 
	@Column
	private boolean isEmployee;

	@OneToMany(mappedBy="user",cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Address> addresses=new ArrayList<>();
	

	


	/**
	 * @return the addresses
	 */
	public List<Address> getAddresses() {
		return addresses;
	}

	/**
	 * @param addresses the addresses to set
	 */
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	@Column
	@JsonIgnore
	private boolean is_active;
	
	@Column	
	private boolean isDeleted;
	
	
	@Column
	@JsonIgnore
	private boolean is_mobile_verified;
	
	@Column
	@JsonIgnore
	private boolean is_email_verified;
	
	@Column
	@JsonIgnore
	private long created;
	
	@Column
	@JsonIgnore
	private long updated;
	
	
	
	
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
	

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
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
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
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
	 * @return the salary
	 */
	public long getSalary() {
		return salary;
	}

	/**
	 * @param salary the salary to set
	 */
	public void setSalary(long salary) {
		this.salary = salary;
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

	/**
	 * @return the business_id
	 */
	public long getBusiness_id() {
		return business_id;
	}

	/**
	 * @param business_id the business_id to set
	 */
	public void setBusiness_id(long business_id) {
		this.business_id = business_id;
	}

	/**
	 * @return the isEmployee
	 */
	public boolean isEmployee() {
		return isEmployee;
	}

	/**
	 * @param isEmployee the isEmployee to set
	 */
	public void setEmployee(boolean isEmployee) {
		this.isEmployee = isEmployee;
	}

	/**
	 * @return the roles
	 */
	public List<Role> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<Role> roles) {
		this.roles = roles;
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
	 * @return the isDeleted
	 */
	public boolean isDeleted() {
		return isDeleted;
	}

	/**
	 * @param isDeleted the isDeleted to set
	 */
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	/**
	 * @return the is_mobile_verified
	 */
	public boolean isIs_mobile_verified() {
		return is_mobile_verified;
	}

	/**
	 * @param is_mobile_verified the is_mobile_verified to set
	 */
	public void setIs_mobile_verified(boolean is_mobile_verified) {
		this.is_mobile_verified = is_mobile_verified;
	}

	/**
	 * @return the is_email_verified
	 */
	public boolean isIs_email_verified() {
		return is_email_verified;
	}

	/**
	 * @param is_email_verified the is_email_verified to set
	 */
	public void setIs_email_verified(boolean is_email_verified) {
		this.is_email_verified = is_email_verified;
	}

	/**
	 * @return the created
	 */
	public long getCreated() {
		return created;
	}

	/**
	 * @param created the created to set
	 */
	public void setCreated(long created) {
		this.created = created;
	}

	/**
	 * @return the updated
	 */
	public long getUpdated() {
		return updated;
	}

	/**
	 * @param updated the updated to set
	 */
	public void setUpdated(long updated) {
		this.updated = updated;
	}

	@PrePersist
	protected void onCreate() {
		created=new Date().getTime();
	    //created = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		updated = new Date().getTime();
	}

	
	
}
