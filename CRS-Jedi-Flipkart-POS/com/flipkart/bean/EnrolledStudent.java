package com.flipkart.bean;

/**
 * Class for storing details of course Student has taken
 */
public class EnrolledStudent {
	private String courseCode;
	private String courseName;
	private int studentId;

	/**
	 * Method to get Course Code
	 */
	public String getCourseCode() {
		return courseCode;
	}

	/**
	 * Method to set Course Code
	 */
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	/**
	 * Method to get Course Name
	 */
	public String getCourseName() {
		return courseName;
	}

	/**
	 * Method to set Course Name
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/**
	 * Method to get Student Id of student enrolled in the course

	 */
	public int getStudentId() {
		return studentId;
	}

	/**
	 * Method to set Student Id of student enrolled in the course
	 */
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	/**
	 * Parameterized constructor
	 */
	public EnrolledStudent(String courseCode, String courseName, int studentId) {
		super();
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.studentId = studentId;
	}
}