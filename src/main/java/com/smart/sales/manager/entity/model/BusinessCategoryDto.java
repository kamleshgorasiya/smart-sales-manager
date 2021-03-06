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

import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



/**
 * @author om
 *
 */
public class BusinessCategoryDto {

	
	@JsonIgnore
	private boolean is_active;
	
	
	
	@JsonIgnore
	private long created;
	
	
	@JsonIgnore
	private long updated;
	
	private long id;
 
	@Column(unique = true,length=64, nullable=false)
	@Size(min = 2, max = 64, message="length.twoto64")
	@NotNull(message = "notempty")
	private String categoryName;	

	private long parentId=0;

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
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * @param categoryName the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * @return the parentId
	 */
	public long getParentId() {
		return parentId;
	}

	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BusinessCategoryDto [is_active=" + is_active + ", created=" + created + ", updated=" + updated + ", id="
				+ id + ", categoryName=" + categoryName + ", parentId=" + parentId + "]";
	}
	
	
	
}
