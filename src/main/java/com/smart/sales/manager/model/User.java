package com.smart.sales.manager.model;

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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.List;


@Entity
public class User {

	@Column(length=50, nullable=false)
    @Size(min = 1, max = 50)
	private String firstName;
	
	
    @Size(min = 1, max = 50)
	@Column(length=50, nullable=false)
	private String lastName;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
 
	@Column(unique = true,length=50, nullable=false)
	@Size(min = 1, message="", max = 50)
	private String username;
	
	
	@JsonIgnore
    @Size(min = 1, max = 255)
	@Column(length=50, nullable=false)
	private String password;
	
	@Column(length=50, nullable=false)
	@Min(value = 15000, message = "The salary must not be less than 15000")
	@Max(value = 100000, message = "The salary must be greator than 100000")
	private long salary;
	
	@Column(length=50, nullable=false)
	@Min(value = 15, message = "The age must not be less than 15 Years")
	@Max(value = 100, message = "The age must not greator than 100 Years")
	private int age;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "USER_ROLES", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "ROLE_ID") })
	private List<Role> roles;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getSalary() {
		return salary;
	}

	public void setSalary(long salary) {
		this.salary = salary;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}
