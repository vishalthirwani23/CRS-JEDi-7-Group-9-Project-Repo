/**
 * 
 */
package com.flipkart.dao;

/**
 * @author venkat.karthik
 *
 */
public interface UserDaoInterface {

	/**
	 * @param userId
	 * @param password
	 * @return true if user credentials are correct
	 */
	public boolean verifyCredentials(String userId, String password);
	
	
	public String getRole(String userId);
	
	
	public boolean updatePassword(String userID, String newPassword);
	
}
