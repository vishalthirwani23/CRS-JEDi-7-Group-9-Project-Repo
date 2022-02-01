package com.flipkart.bean;


import com.flipkart.constant.Role;

/**
 * User Class
 */
public  class User {
	private String userId;
	private String name;
	private Role role;
	private String password;
	
	/**
	 * Parameterized Constructor
	 * 
	 * @param userId:   email address of the user
	 * @param name:     user name
	 * @param role:     user's role
	 * @param password: user's password
	 * @param gender:   user's gender
	 * @param address:  user's address
	 * @param country:  user's country
	 */
	public User(String userId, String name, Role role, String password) {
		this.userId = userId;
		this.name = name;
		this.role = role;
		this.password = password;
		
	}

	
	

	
	/**
	 * Default Constructor
	 */
	public User() {

	}

	/**
	 * Method to get User's Id
	 * 
	 * @return User Id
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Method to set User's Id
	 * 
	 * @param userId: User's ID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * Method to get User's Name
	 * 
	 * @return User Name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Method to set User's Name
	 * 
	 * @param name: Name of the user
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Method to get User's Role
	 * 
	 * @return User Role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * Method to set User's Role
	 * 
	 * @param role: User Role
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * Method to get User's Password
	 * 
	 * @return User Password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Method to set User's Password
	 * 
	 * @param password: password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}