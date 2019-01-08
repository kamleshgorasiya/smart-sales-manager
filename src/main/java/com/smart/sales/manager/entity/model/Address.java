package com.smart.sales.manager.entity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Date;


@Entity
public class Address {


	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(length=64, nullable=false)
    @Size(min = 2, max = 64, message="length.twoto64")
	@NotNull(message = "notempty")
	private String name;
	
	@Column	
	private boolean isActive;
	
	@Column	
	private boolean isDeleted;
	
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

	@ManyToOne
	@JoinColumn(name="id")
	@NotNull(message="notempty")
	private User user;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
