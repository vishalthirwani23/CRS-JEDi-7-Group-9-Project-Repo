/**
 * 
 */
package com.flipkart.dao;

import java.util.HashMap;

import com.crs.flipkart.bean.User;

/**
 * @author venkat.karthik
 *
 */
public class UserDaoOperation extends UserDB implements UserDaoInterface {

	private static volatile UserDaoOperation instance = null;

	private UserDaoOperation() {

	}

	public static UserDaoOperation getInstance() {
		if (instance == null) {
			// This is a synchronized block, when multiple threads will access this instance
			synchronized (UserDaoOperation.class) {
				instance = new UserDaoOperation();
			}
		}
		return instance;
	}
	
	public boolean updatePassword(String userId, String newPassword) {
		HashMap<Integer, User> userDB = new HashMap<Integer, User>();
		User user = getUser(userId);
		if(user == null){
			System.out.println("User not found!!");
			return false;
		}
		else {
			user.setPassword(newPassword);
			System.out.println("Password updated!!!");
			return true;
		}
		
	}
	
	public boolean verifyCredentials(String userId, String password) {
		HashMap<Integer, User> userDB = new HashMap<Integer, User>();
		User user = getUser(userId);
		if(user == null) {
			System.out.println("User not found!!");
			return false;
		}
		else {
			if(user.getPassword().equals(password)) {
				System.out.println("Credentials Verified!!!");
				return true;
			}
			else {
				System.out.println("Username/Password is incorrect");
				return false;
			}
		}
	}

	public boolean updatePassword(String userID) {
		System.out.println("Enter password!!!");
		return false;
	}
	
	public String getRole(String userId) {
		User user = getUser(userId);
		if(user == null) {
			System.out.println("User not found!!");
			return null;
		}
		else {
			return user.getRole();
		}
	}
	
}
