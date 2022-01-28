package com.flipkart.business;
/**
 * @author venkat.karthik
 *
 */

import com.flipkart.dao.UserDaoInterface;
import com.flipkart.dao.UserDaoOperation;


public class UserOperation implements UserInterface {

	private static volatile UserOperation instance = null;
	UserDaoInterface userDaoInterface = UserDaoOperation.getInstance();

	private UserOperation() {

	}


	public static UserOperation getInstance() {
		if (instance == null) {
			synchronized (UserOperation.class) {
				instance = new UserOperation();
			}
		}
		return instance;
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
