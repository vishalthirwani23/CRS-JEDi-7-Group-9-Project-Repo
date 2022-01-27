package com.flipkart.exceptions;

public class SeatNotAvailableException extends Exception {

	private String courseCode;

	/**
	 * Constructor
	 * 
	 * @param courseCode: User Id for User
	 */
	public SeatNotAvailableException(String courseCode) {
		this.courseCode = courseCode;
	}

	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() {
		return "Seats are not available in : " + courseCode;
	}

}