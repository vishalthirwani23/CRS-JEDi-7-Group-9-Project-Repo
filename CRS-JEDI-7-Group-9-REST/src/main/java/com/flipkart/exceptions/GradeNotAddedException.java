package com.flipkart.exceptions;

public class GradeNotAddedException extends Exception {

	private int studentId;

	/**
	 * Constructor
	 * 
	 * @param studentId: Student Id of Student
	 */
	public GradeNotAddedException(int studentId) {
		this.studentId = studentId;
	}

	/**
	 * Getter function for studentId
	 * 
	 * @return Student ID
	 */
	public int getStudentId() {
		return studentId;
	}

}