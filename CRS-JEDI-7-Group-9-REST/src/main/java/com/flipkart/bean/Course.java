/**
 * 
 */
package com.flipkart.bean;


/**
 * @author User
 *
 */
public class Course {
	private int courseCode;
	private String courseName;
	private String instructorId;
	private int seats = 10;
	
	
	public Course() {

	}

	/**
	 * Parameterized constructor
	 *
	 * @param courseCode:   course code
	 * @param courseName:   course name
	 * @param instructorId: instructor user id
	 * @param seats:        seats available
	 */
	public Course(int courseCode, String courseName, String instructorId, int seats) {
		super();
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.setInstructorId(instructorId);
		this.seats = seats;
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
	 * @param courseCode Course code of Course
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
	 * @param courseName Course Name of Course
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/**
	 * Method to get available seats
	 * 
	 * @return Seats available
	 */
	public int getSeats() {
		return seats;
	}

	/**
	 * Method to set available seats
	 * 
	 * @param seats: seats available
	 */
	public void setSeats(int seats) {
		this.seats = seats;
	}

	/**
	 * Method to get Instructor Id of professor teaching the course
	 * 
	 * @return Instructor Id
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Method to set Instructor Id of professor teaching the course
	 * 
	 * @param instructorId: instructor user id
	 */
	public void setInstructorId(String instructorId) {
		this.instructorId = instructorId;
	}

}
