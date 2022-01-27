package com.flipkart.exceptions;

public class StudentNotRegisteredException extends Exception {
	private String studentName;

	public StudentNotRegisteredException(String studentName) {
		this.studentName = studentName;
	}

	/**
	 * getter function for studentName
	 * 
	 * @return Student Name
	 */
	public String getStudentName() {
		return studentName;
	}

}