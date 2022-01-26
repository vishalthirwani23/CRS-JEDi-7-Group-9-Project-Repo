package com.flipkart.business;
/**
 * @author venkat.karthik
 *
 */

public interface UserInterface {

	boolean updatePassword(String userID, String newPassword);

	public boolean verifyCredentials(String userID, String password) throws Exception;

	public String getRole(String userId);

}
