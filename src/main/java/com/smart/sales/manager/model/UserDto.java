package com.smart.sales.manager.model;

import java.util.List;

public class UserDto {

   	private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private String mobile;
    private int age;
    private long salary;
    private List<Long> role_ids;
	    
	    
 
    /**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
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
 

	

	

	/**
	 * @return the role_ids
	 */
	public List<Long> getRole_ids() {
		return role_ids;
	}

	/**
	 * @param role_ids the role_ids to set
	 */
	public void setRole_ids(List<Long> role_ids) {
		this.role_ids = role_ids;
	}

	public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

   
    
}
