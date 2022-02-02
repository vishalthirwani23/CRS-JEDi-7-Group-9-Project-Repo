package com.flipkart.exceptions;

public class StudentNotRegisteredException extends Exception {
	private int studentId;
	private String studentName;
	/**
	 * Constructor
	 * 
	 * @param studentName: stundent's Name
	 */
	public StudentNotRegisteredException(String studentName) {
		this.studentName = studentName;
	}

	public StudentNotRegisteredException(int studentID) {
		this.studentId = studentID;
	}

	/**
	 * getter function for studentName
	 * 
	 * @return Student Name
	 */
	public String getStudentName() {
		return studentName;
	}

	public int getStudentId() {
		return studentId;
	}

}