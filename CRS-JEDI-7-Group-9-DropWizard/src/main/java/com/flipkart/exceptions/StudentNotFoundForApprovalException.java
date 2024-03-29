package com.flipkart.exceptions;

public class StudentNotFoundForApprovalException extends Exception {
	private int studentId;

	public StudentNotFoundForApprovalException(int studentId) {
		this.studentId = studentId;
	}

	/**
	 * Getter function for studentId
	 * 
	 * @return student ID
	 */
	public int getStudentId() {
		return this.studentId;
	}

	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() {
		return "studentId: " + studentId + " not found for approval!";
	}
}