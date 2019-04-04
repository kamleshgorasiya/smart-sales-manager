package com.smart.sales.manager.entity.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.Nullable;


@Entity
public class Business {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(length=64, nullable=false)
    @Size(min = 2, max = 64, message="length.twoto64")
	@NotNull(message = "{notempty}")
	private String name;
	
	
    @Size(min = 2, max = 128, message="length.eightto128")
	@Column(length=128, nullable=true)    
	private String title;
    
    @Size(min = 8, max = 16, message="length.eightto16")
	@Column(length=16, nullable=true)    
	private String gstNumber;

	
 
	@Column(length=512)
	@Size(min = 2, max = 512, message="length.eightto512")
	private String description;
	
	@Column(length=64)
	@Size(min = 1, max = 64, message="length.twoto64")	
	@Email(message = "valid.format")
	@Nullable
	private String email;
	
	@Column(length=14)
	@Size(min = 8, max = 14, message="length.eightto14")	
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
	private boolean isOn;

	
	@Column	
	private int minOrderAmount;
	
	@Column	
	private int deliveryDistance;	
	
	
	@Column
	private long ownerId;
	
	@Column(length=64)
	@Size(min = 1, max = 64, message="length.notmorethan32")
	private String ownerName;
	
	
	@Column	
	private String businessTags;
	
	
	@Column
	private long created;
	
	@Column
	private long updated;
	

	
	@Column(length=32, nullable=false)
	@Size(min = 1, max = 32, message="length.notmorethan14")
	@NotNull(message = "{notempty}")	
	private String longitude;
	
	
	@Column(length=32, nullable=false)
	@Size(min = 8, max = 32, message="length.notmorethan14")
	@NotNull(message = "{notempty}")	
	private String latitude;
	
	@Column(length=128, nullable=false)
	@Size(min = 5, max = 128, message="length.eightto128")
	@NotNull(message = "{notempty}")
	private String address;
	
	@Column(length=28)
	@Size(min = 1, max = 28, message="length.oneto28")
	private String city;
	
	@Column(length=28)
	@Size(min = 1, max = 28, message="length.oneto28")
	private String state;
	
	@Column(length=28)
	@Size(min = 1, max = 28, message="length.oneto28")
	private String country;
	
	@Column(length=8)
	@Size(min = 5, max = 8, message="length.fiveto8")
	private String zipcode;

	@OneToMany(mappedBy="business",cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.LAZY)
	@JsonIgnore
	private List<Product> products=new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name="category_id")
	@NotNull(message = "{notempty}")
	private BusinessCategory businessCategory;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the gstNumber
	 */
	public String getGstNumber() {
		return gstNumber;
	}

	/**
	 * @param gstNumber the gstNumber to set
	 */
	public void setGstNumber(String gstNumber) {
		this.gstNumber = gstNumber;
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
		return new Date().getTime();
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
	 * @return the products
	 */
	public List<Product> getProducts() {
		return products;
	}

	/**
	 * @param products the products to set
	 */
	public void setProducts(List<Product> products) {
		this.products = products;
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
	public BusinessCategory getBusinessCategory() {
		return businessCategory;
	}

	/**
	 * @param businessCategory the businessCategory to set
	 */
	public void setBusinessCategory(BusinessCategory businessCategory) {
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
	 * @return the isOn
	 */
	public boolean isOn() {
		return isOn;
	}

	/**
	 * @param isOn the isOn to set
	 */
	public void setIsOn(boolean isOn) {
		this.isOn = isOn;
	}

	

	/**
	 * @param businessTags the businessTags to set
	 */
	public void setBusinessTags(String businessTags) {
		this.businessTags = businessTags;
	}

	

	/**
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
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


	

	/**
	 * @return the ownerId
	 */
	public long getOwnerId() {
		return ownerId;
	}

	/**
	 * @param ownerId the ownerId to set
	 */
	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}

	/**
	 * @return the ownerName
	 */
	public String getOwnerName() {
		return ownerName;
	}

	/**
	 * @param ownerName the ownerName to set
	 */
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}




	
	
}
