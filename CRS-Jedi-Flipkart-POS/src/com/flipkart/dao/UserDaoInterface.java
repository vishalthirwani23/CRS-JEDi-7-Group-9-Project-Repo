package com.flipkart.dao;


//Interface for User dao Operations
 
public interface UserDaoInterface {

	//Method to verify credentials of Users from DataBase
	
	public boolean verifyCredentials(String userId, String password) throws Exception;

	//Method to update password of user in DataBase
	 
	public boolean updatePassword(String userID);

	//Method to get Role of User from DataBase
	
	public String getRole(String userId);

	//Method to update password of user in DataBase
	
	public boolean updatePassword(String userID, String newPassword);
}
