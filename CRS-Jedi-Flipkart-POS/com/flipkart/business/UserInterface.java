/**
 * 
 */
package com.flipkart.business;

/**
 * @author jayant
 *
 */
public interface UserInterface {
	
	boolean updatePassword(String userID, String newPassword);

	/**
	 * Method to verify User credentials
	 * 
	 * @param userID User ID
	 * @param password Password of the user
	 * @return boolean indicating if user exists in the database
	 * @throws UserNotFoundException If Student is not registered
	 */
	public boolean verifyCredentials(String userID, String password);

	/**
	 * Method to get role of a specific User
	 * 
	 * @param userId User ID
	 * @return Role of the User
	 */
	public String getRole(String userId);
	
}
