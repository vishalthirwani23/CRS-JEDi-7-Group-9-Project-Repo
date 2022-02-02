package com.flipkart.exceptions;

public class CourseAlreadyRegisteredException extends Exception {

	private String courseCode;

	/**
	 * Constructor
	 */
	public CourseAlreadyRegisteredException(String courseCode) {
		this.courseCode = courseCode;
	}

	/**
	 * Getter method
	 */
	public String getCourseCode() {
		return courseCode;
	}

	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() {
		return "You have already registered for " + courseCode;
	}

}