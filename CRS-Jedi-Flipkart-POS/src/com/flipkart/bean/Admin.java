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

	public void admin(String adminName, String adminID)
	{
		this.setAdminName(adminName);
		this.setAdminID(adminID);
	}

}
