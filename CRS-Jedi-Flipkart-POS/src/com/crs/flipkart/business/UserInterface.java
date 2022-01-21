/**
 * 
 */
package com.crs.flipkart.business;

/**
 * @author jayant
 *
 */
public interface UserInterface {
	
	public void updatePassword(String userID, String password);
	public void updateContactNumber(String userID, String number);
	public void updateRole(String userID, String role);
	public boolean loginUser(String userID, String password, String role);
	
}
