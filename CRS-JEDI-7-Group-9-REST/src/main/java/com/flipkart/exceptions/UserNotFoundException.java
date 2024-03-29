package com.flipkart.exceptions;

public class UserNotFoundException extends Exception {

	private String userId;

	/***
	 * Getter function for UserId
	 * 
	 * @param userId: User Id of User
	 */
	public UserNotFoundException(String userId) {
		this.userId = userId;
	}

	/**
	 * Message thrown by exception
	 */
	@Override
	public String getMessage() {
		return "User with userId: " + userId + " not found.";
	}

}