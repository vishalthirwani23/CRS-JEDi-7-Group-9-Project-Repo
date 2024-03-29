package com.flipkart.exceptions;

public class CourseLimitExceedException extends Exception {

	private int num;

	/**
	 * Constructor
	 * 
	 * @param num number of courses
	 */
	public CourseLimitExceedException(int num) {
		this.num = num;
	}

	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() {
		return "You have already registered for " + num + " courses";
	}

}