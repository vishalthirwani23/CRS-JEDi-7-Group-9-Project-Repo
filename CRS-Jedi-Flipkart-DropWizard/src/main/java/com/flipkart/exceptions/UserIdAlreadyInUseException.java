package com.flipkart.exceptions;

public class UserIdAlreadyInUseException extends Exception{
	private String userId;
	
	/**
	 * Constructor
	 * 
	 * @param userId: User Id of User
	 */
	public UserIdAlreadyInUseException(String userId) {
		this.userId = userId;
	}
	
	/**
	 * Getter for userId
	 * 
	 * @return User Id for User
	 */
	public String getUserId() {
		return userId;
	}

	public void setProfessorId(String userId) {
		this.userId = userId;
	}
	
	@Override
	public String getMessage() {
		return "userId: " + userId + " is already in use.";
	}

}