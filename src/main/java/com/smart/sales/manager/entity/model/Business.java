package com.smart.sales.manager.entity.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Business {


	
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
	
	@Column(unique = true,length=64, nullable=false)
	@Size(min = 1, max = 64, message="length.twoto64")
	@NotEmpty(message = "notempty")
	@Email(message = "valid.format")
	private String email;
	
	@Column(unique = true,length=14, nullable=false)
	@Size(min = 8, max = 14, message="length.eightto14")
	@NotNull(message = "notempty")	
	private String mobile;
	
	@Column	
	private boolean isActive;
	
	@Column	
	private boolean isDeleted;
	
	@Column
	private boolean isBookAppointment;
	
	@Column
	private boolean isAdvanceAppointmentBooking;
	
	@Column	
	private boolean allowedOnlineOrder;
	
	@Column	
	private boolean allowedDelivery;
	
	@Column	
	private int minOrderAmount;
	
	@Column	
	private int deliveryDistance;	
	
	@Column	
	private String businessCategory;
	
	@Column(length=64, nullable=false)
    @Size(min = 2, max = 64, message="length.twoto64")
	@NotNull(message = "notempty")
	private String owner;
	
	@Column	
	private String businessTags;
	
	@Column
	@JsonIgnore
	private long created;
	
	@Column
	@JsonIgnore
	private long updated;
	
	
	@Column(length=14, nullable=false)
	@Size(min = 1, max = 14, message="length.notmorethan14")
	@NotNull(message = "notempty")	
	private String longi="0.124564";
	
	
	@Column(length=14, nullable=false)
	@Size(min = 8, max = 14, message="length.notmorethan14")
	@NotNull(message = "notempty")	
	private String lat="0.124564";
	
	@Column(length=128, nullable=false)
	@Size(min = 5, max = 128, message="length.eightto128")
	@NotEmpty(message = "notempty")
	private String address;
	
	@Column(length=28, nullable=false)
	@Size(min = 1, max = 28, message="length.oneto28")
	@NotEmpty(message = "notempty")
	private String city;
	
	@Column(length=28, nullable=false)
	@Size(min = 1, max = 28, message="length.oneto28")
	@NotEmpty(message = "notempty")
	private String state;
	
	@Column(length=28, nullable=false)
	@Size(min = 1, max = 28, message="length.oneto28")
	@NotEmpty(message = "notempty")
	private String country;
	
	@Column(length=8, nullable=false)
	@Size(min = 5, max = 8, message="length.fiveto8")
	@NotEmpty(message = "notempty")
	private String zipcode;

	@OneToMany(mappedBy="business",cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Bookable> services=new ArrayList<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
	 * @return the isBookAppointment
	 */
	public boolean isBookAppointment() {
		return isBookAppointment;
	}

	/**
	 * @param isBookAppointment the isBookAppointment to set
	 */
	public void setBookAppointment(boolean isBookAppointment) {
		this.isBookAppointment = isBookAppointment;
	}

	/**
	 * @return the isAdvanceAppointmentBooking
	 */
	public boolean isAdvanceAppointmentBooking() {
		return isAdvanceAppointmentBooking;
	}

	/**
	 * @param isAdvanceAppointmentBooking the isAdvanceAppointmentBooking to set
	 */
	public void setAdvanceAppointmentBooking(boolean isAdvanceAppointmentBooking) {
		this.isAdvanceAppointmentBooking = isAdvanceAppointmentBooking;
	}

	/**
	 * @return the services
	 */
	public List<Bookable> getServices() {
		return services;
	}

	/**
	 * @param services the services to set
	 */
	public void setServices(List<Bookable> services) {
		this.services.clear();
	    if (services != null) {
	        this.services.addAll(services);
	    }
		
	}

	/**
	 * @return the allowedOnlineOrder
	 */
	public boolean isAllowedOnlineOrder() {
		return allowedOnlineOrder;
	}

	/**
	 * @param allowedOnlineOrder the allowedOnlineOrder to set
	 */
	public void setAllowedOnlineOrder(boolean allowedOnlineOrder) {
		this.allowedOnlineOrder = allowedOnlineOrder;
	}

	/**
	 * @return the allowedDelivery
	 */
	public boolean isAllowedDelivery() {
		return allowedDelivery;
	}

	/**
	 * @param allowedDelivery the allowedDelivery to set
	 */
	public void setAllowedDelivery(boolean allowedDelivery) {
		this.allowedDelivery = allowedDelivery;
	}

	/**
	 * @return the minOrderAmount
	 */
	public int getMinOrderAmount() {
		return minOrderAmount;
	}

	/**
	 * @param minOrderAmount the minOrderAmount to set
	 */
	public void setMinOrderAmount(int minOrderAmount) {
		this.minOrderAmount = minOrderAmount;
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
	 * @return the businessCategory
	 */
	public String getBusinessCategory() {
		return businessCategory;
	}

	/**
	 * @param businessCategory the businessCategory to set
	 */
	public void setBusinessCategory(String businessCategory) {
		this.businessCategory = businessCategory;
	}

	/**
	

	/**
	 * @return the businessTags
	 */
	public String getBusinessTags() {
		return businessTags;
	}

	/**
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}

	/**
	 * @param businessTags the businessTags to set
	 */
	public void setBusinessTags(String businessTags) {
		this.businessTags = businessTags;
	}

	/**
	 * @return the longi
	 */
	public String getLongi() {
		return longi;
	}

	/**
	 * @param longi the longi to set
	 */
	public void setLongi(String longi) {
		this.longi = longi;
	}

	/**
	 * @return the lat
	 */
	public String getLat() {
		return lat;
	}

	/**
	 * @param lat the lat to set
	 */
	public void setLat(String lat) {
		this.lat = lat;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the zipcode
	 */
	public String getZipcode() {
		return zipcode;
	}

	/**
	 * @param zipcode the zipcode to set
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
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
	
	
	
}
