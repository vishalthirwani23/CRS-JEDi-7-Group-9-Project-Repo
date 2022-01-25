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
	
	public Admin(String userId, String name, Role role, String password) {
	super(userId, name, role, password);
}
	public Date getDateOfJoining() {
		return dateOfJoining;
	}
	
	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	
	
}
