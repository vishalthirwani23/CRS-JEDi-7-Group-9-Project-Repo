package com.flipkart.exceptions;

public class CourseNotFoundException extends Exception {
	private int courseCode;

	public CourseNotFoundException(int courseCode) {
		this.courseCode = courseCode;
	}

	/**
	 * Getter function for course code
	 * 
	 * @return Course Code of Course
	 */
	public int getCourseCode() {
		return courseCode;
	}

	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() {
		return "Course with courseCode: " + courseCode + " not found.";
	}
}
