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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
public class Booking {

	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 

	
	@ManyToOne
	@JoinColumn(name="employee_id")
	@NotNull(message="notempty")
	@JsonIgnore
	private User employee;

	
	@ManyToOne
	@JoinColumn(name="user_id")
	@NotNull(message="notempty")
	@JsonIgnore
	private User user;
	
	
	@ManyToOne
	@JoinColumn(name="product_id")
	@NotNull(message="notempty")
	@JsonIgnore
	private Product product;

	@Column(length=50, nullable=false)
	@NotNull(message = "notempty")
	private long bookingTime;

	@ManyToOne
	@JoinColumn(name="business_id")
	@NotNull(message="notempty")
	@JsonIgnore
	private Business business;
	
	
	@Column	
	private boolean isDeleted;
	
	@Column	
	private boolean isConfirmedByEmployee;
	
	
	@Column	
	private boolean isConfirmedByUser;
	
	
	@Column
	private String bookingStatus;
	
	
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
	 * @return the employee
	 */
	public User getEmployee() {
		return employee;
	}

	/**
	 * @param employee the employee to set
	 */
	public void setEmployee(User employee) {
		this.employee = employee;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * @return the bookingTime
	 */
	public long getBookingTime() {
		return bookingTime;
	}

	/**
	 * @param bookingTime the bookingTime to set
	 */
	public void setBookingTime(long bookingTime) {
		this.bookingTime = bookingTime;
	}

	/**
	 * @return the business
	 */
	public Business getBusiness() {
		return business;
	}

	/**
	 * @param business the business to set
	 */
	public void setBusiness(Business business) {
		this.business = business;
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
	 * @return the isConfirmedByEmployee
	 */
	public boolean isConfirmedByEmployee() {
		return isConfirmedByEmployee;
	}

	/**
	 * @param isConfirmedByEmployee the isConfirmedByEmployee to set
	 */
	public void setConfirmedByEmployee(boolean isConfirmedByEmployee) {
		this.isConfirmedByEmployee = isConfirmedByEmployee;
	}

	/**
	 * @return the isConfirmedByUser
	 */
	public boolean isConfirmedByUser() {
		return isConfirmedByUser;
	}

	/**
	 * @param isConfirmedByUser the isConfirmedByUser to set
	 */
	public void setConfirmedByUser(boolean isConfirmedByUser) {
		this.isConfirmedByUser = isConfirmedByUser;
	}

	/**
	 * @return the bookingStatus
	 */
	public String getBookingStatus() {
		return bookingStatus;
	}

	/**
	 * @param bookingStatus the bookingStatus to set
	 */
	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
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
