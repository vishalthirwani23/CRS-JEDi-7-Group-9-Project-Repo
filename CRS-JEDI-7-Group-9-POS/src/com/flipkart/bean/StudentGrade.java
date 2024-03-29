/**
 * 
 */
package com.flipkart.bean;

import com.flipkart.constant.Grade;

/**
 * 
 * 
 * Class to store Student Grade information
 * 
 */
public class StudentGrade {

	private int courseCode;
	private String courseName;
	private String grade;

	/**
	 * Parameterized Constructor
	 * 
	 * @param courseCode: course code
	 * @param courseName: course name
	 * @param grade:      grade
	 */
	public StudentGrade(int courseCode, String courseName, String grade) {
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.setGrade(grade);
	}

	/**
	 * Method to get Course Code
	 * 
	 * @return Course Code
	 */
	public int getCourseCode() {
		return courseCode;
	}

	/**
	 * Method to set Course Code
	 * 
	 * @param courseCode: Course Code
	 */
	public void setCourseCode(int courseCode) {
		this.courseCode = courseCode;
	}

	/**
	 * Method to get Course Name
	 * 
	 * @return Course Name
	 */
	public String getCourseName() {
		return courseName;
	}

	/**
	 * Method to set Course Name
	 * 
	 * @param courseName: Course Name
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/**
	 * Method to get grade
	 * 
	 * @return Grade
	 */
	public String getGrade() {
		return grade;
	}

	/**
	 * Method to set grade
	 * 
	 * @param grade: Grade
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}

}