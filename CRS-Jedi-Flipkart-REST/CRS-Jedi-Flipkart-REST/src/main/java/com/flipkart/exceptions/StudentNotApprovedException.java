package com.flipkart.exceptions;

public class StudentNotApprovedException extends Exception {
	private int studentId;

	public StudentNotApprovedException(Integer studentId) {
		this.studentId=studentId;
	}
	
	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	
	
	public String getMessage() {
		return "Student "+ studentId +"is not approved";
	}
}
