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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Date;
import java.util.List;


@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(length=64, nullable=false)
    @Size(min = 2, max = 64, message="length.twoto64")
	@NotNull(message = "notempty")
	private String name;	
	
    @Size(min = 2, max = 128, message="length.eightto128")	
    @Column(length=128, nullable=false)    
	private String title;	
 
	@Column(length=512, nullable=false)
	@Size(min = 2, max = 512, message="length.eightto512")
	@NotNull(message = "notempty")
	private String description;	
	
	@Column	
	private boolean isActive;

	
	@Column
	private boolean isAllowAdvanceBooking;	
	
	@Column	
	private boolean allowedHomeService;
	
	@Column
	@Min(value = 0, message = "min.value.zero")
	@Max(value = 100000, message = "max.value.lakhs")
	private int serviceCharge;
	
	@Column
	@Min(value = 0, message = "min.value.zero")
	@Max(value = 1000, message = "max.value.thousand")
	private int deliveryDistance;	

	@ManyToOne
	@JoinColumn(name="business_id")
	@NotNull(message="notempty")
	private Business business;
	
	@Column	
	private String serviceTags;
	
	@Column	
	@Min(value = 0, message = "min.value.zero")
	@Max(value = 1000, message = "max.value.thousand")
	private int approxTime;
	
	@Column
	private long created;
	
	@Column
	private long updated;
	
	
	@Column
	private boolean isService;


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the approxTime
	 */
	public int getApproxTime() {
		return approxTime;
	}

	/**
	 * @param approxTime the approxTime to set
	 */
	public void setApproxTime(int approxTime) {
		this.approxTime = approxTime;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the isActive
	 */
	public boolean isActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	

	/**
	 * @return the isAllowAdvanceBooking
	 */
	public boolean isAllowAdvanceBooking() {
		return isAllowAdvanceBooking;
	}

	/**
	 * @param isAllowAdvanceBooking the isAllowAdvanceBooking to set
	 */
	public void setAllowAdvanceBooking(boolean isAllowAdvanceBooking) {
		this.isAllowAdvanceBooking = isAllowAdvanceBooking;
	}

	/**
	 * @return the allowedHomeService
	 */
	public boolean isAllowedHomeService() {
		return allowedHomeService;
	}

	/**
	 * @param allowedHomeService the allowedHomeService to set
	 */
	public void setAllowedHomeService(boolean allowedHomeService) {
		this.allowedHomeService = allowedHomeService;
	}

	/**
	 * @return the serviceCharge
	 */
	public int getServiceCharge() {
		return serviceCharge;
	}

	/**
	 * @param serviceCharge the serviceCharge to set
	 */
	public void setServiceCharge(int serviceCharge) {
		this.serviceCharge = serviceCharge;
	}

	/**
	 * @return the deliveryDistance
	 */
	public int getDeliveryDistance() {
		return deliveryDistance;
	}

	/**
	 * @param deliveryDistance the deliveryDistance to set
	 */
	public void setDeliveryDistance(int deliveryDistance) {
		this.deliveryDistance = deliveryDistance;
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
	 * @return the serviceTags
	 */
	public String getServiceTags() {
		return serviceTags;
	}

	/**
	 * @param serviceTags the serviceTags to set
	 */
	public void setServiceTags(String serviceTags) {
		this.serviceTags = serviceTags;
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

	/**
	 * @return the isService
	 */
	public boolean isService() {
		return isService;
	}

	/**
	 * @param isService the isService to set
	 */
	public void setService(boolean isService) {
		this.isService = isService;
	}
	

}
