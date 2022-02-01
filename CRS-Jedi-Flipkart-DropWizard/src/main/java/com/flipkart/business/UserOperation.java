package com.flipkart.business;
/**
 * @author venkat.karthik
 *
 */

import com.flipkart.dao.UserDaoInterface;
import com.flipkart.dao.UserDaoOperation;
import com.flipkart.exceptions.UserNotFoundException;
import com.flipkart.*;


public class UserOperation implements UserInterface {

	private static volatile UserOperation instance = null;
	UserDaoInterface userDaoInterface = new UserDaoOperation();

	public UserOperation() {

	}


	

	public boolean updatePassword(String userID, String newPassword) {
		return userDaoInterface.updatePassword(userID, newPassword);
	}


	public boolean verifyCredentials(String userID, String password) throws UserNotFoundException {
		try {
			return userDaoInterface.verifyCredentials(userID, password);
		} finally {

		}
	}

	public String getRole(String userId) {
		return userDaoInterface.getRole(userId);
	}

}
