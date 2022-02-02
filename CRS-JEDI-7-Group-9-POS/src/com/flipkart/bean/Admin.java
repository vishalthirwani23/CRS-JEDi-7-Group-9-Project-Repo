/**
 * 
 */
package com.flipkart.bean;

import com.flipkart.constant.Role;

import java.util.Date;
/**
 * @author User
 *
 */

public class Admin extends User {
	
	private Date dateOfJoining;
	
	/**
	 * Parameterized Constructor
	 *
	 * @param userId:     email address of the user
	 * @param name:       user full name
	 * @param role:       role among student, professor, admin
	 * @param password:   user password
	 */
	public Admin(String userId, String name, Role role, String password) {
		super(userId, name, role, password);
	}
	
	/**
	 * Method to get Date of joining
	 * 
	 * @return Date of joining
	 */
	public Date getDateOfJoining() {
		return dateOfJoining;
	}
	
	/**
	 * Method to set Date of joining
	 * 
	 * @param dateOfJoining Date of joining
	 */
	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	
	
}
