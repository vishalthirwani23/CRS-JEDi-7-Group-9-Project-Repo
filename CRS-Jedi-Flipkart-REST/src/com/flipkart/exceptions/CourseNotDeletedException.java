package com.flipkart.exceptions;

public class CourseNotDeletedException extends Exception {
	private int courseCode;

	public CourseNotDeletedException(int courseCode) {
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
	 * Message thrown by exception
	 */
	@Override
	public String getMessage() {
		return "Course with courseCode: " + courseCode + " can not be deleted.";
	}
}