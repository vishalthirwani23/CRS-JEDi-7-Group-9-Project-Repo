package com.flipkart.exceptions;

public class CourseFoundException extends Exception {
	private int courseCode;

	/***
	 * Constructor
	 * @param courseCode Course Code of Course
	 */
	public CourseFoundException(int courseCode) {
		this.courseCode = courseCode;
	}

	/**
	 * Getter method
	 * 
	 * @return course code
	 */
	public int getCourseCode() {
		return courseCode;
	}

	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() {
		return "Course with courseCode: " + courseCode + " already present in catalog.";
	}
}