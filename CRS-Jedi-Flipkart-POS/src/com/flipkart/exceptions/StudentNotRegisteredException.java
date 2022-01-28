package com.flipkart.exceptions;

public class StudentNotRegisteredException extends Exception {
	private int studentId;

	public StudentNotRegisteredException(int studentID) {
		this.studentId = studentID;
	}

	/**
	 * getter function for studentName
	 * 
	 * @return Student Name
	 */
	public int getStudentId() {
		return studentId;
	}

}