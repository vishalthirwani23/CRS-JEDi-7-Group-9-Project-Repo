package com.flipkart.exceptions;

public class UserNotAddedException extends Exception {
	private String userId;

	public UserNotAddedException(String userId) {
		this.userId = userId;
	}

	/**
	 * Getter function for UserId
	 * 
	 * @return User Id for User
	 */
	public String getUserId() {
		return this.userId;
	}

	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() {
		return "UserId: " + userId + " is already in use!";
	}
}