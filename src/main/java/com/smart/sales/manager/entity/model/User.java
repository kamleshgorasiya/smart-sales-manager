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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
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
    @Size(min = 2, max = 64, message="length.twoto64")
	@NotNull(message = "notempty")
	private String firstName;
	
	
    @Size(min = 2, max = 64, message="length.twoto64")
	@Column(length=50, nullable=false)
    @NotNull(message = "notempty")
	private String lastName;
	
 
	@Column(unique = true,length=64, nullable=false)
	@Size(min = 2, max = 64, message="length.twoto64")
	@NotNull(message = "notempty")
	private String username;
	
	@Column(unique = true,length=64, nullable=false)
	@Size(min = 1, max = 64, message="length.twoto64")
	@NotEmpty(message = "notempty")
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
	
	@Column(length=50, nullable=false)
	@Min(value = 15000, message = "min.salary")
	@Max(value = 100000, message = "max.salary")
	@NotNull(message = "notempty")
	private long salary;
	
	@Column(length=50, nullable=false)
	@Min(value = 18, message = "min.age")
	@Max(value = 100, message = "max.age")
	@NotNull(message = "notempty")
	private int age;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "USER_ROLES", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "ROLE_ID") })
	@JsonIgnore
	private List<Role> roles;

	
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
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
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
