/**
 * 
 */
package com.flipkart.bean;

/**
 * @author User
 *
 */
public class Admin extends User {
	
	private String adminName;
	private String adminID;
	
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	
	public String getAdminID() {
		return adminID;
	}
	public void setAdminID(String adminID) {
		this.adminID = adminID;
	}

	public Admin(String adminName, String adminID, String password)
	{
		this.setAdminName(adminName);
		this.setAdminID(adminID);
		this.setPassword(password);
	}

}
