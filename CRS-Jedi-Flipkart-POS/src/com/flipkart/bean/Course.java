/**
 * 
 */
package com.flipkart.bean;

import java.util.List;

/**
 * @author User
 *
 */
public class Course {
	private String courseID;
	private String coursename;
	private String instructorID;
	private Integer totalSeats;
	private Integer availableSeats;
	private Integer offeredSemester;
	
	
	public Course(String courseCode, String courseName2, Object object, int i) {
		// TODO Auto-generated constructor stub
	}
	public String getCourseID() {
		return courseID;
	}
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	public String getInstructorID() {
		return instructorID;
	}
	public void setInstructorID(String instructorID) {
		this.instructorID = instructorID;
	}
	public Integer getTotalSeats() {
		return totalSeats;
	}
	public void setTotalSeats(Integer totalSeats) {
		this.totalSeats = totalSeats;
	}
	public Integer getAvailableSeats() {
		return availableSeats;
	}
	public void setAvailableSeats(Integer availableSeats) {
		this.availableSeats = availableSeats;
	}
	public Integer getOfferedSemester() {
		return offeredSemester;
	}
	public void setOfferedSemester(Integer offeredSemester) {
		this.offeredSemester = offeredSemester;
	}
	public int getCourseCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	public int getCourseName() {
		// TODO Auto-generated method stub
		return 0;
	}
	public String getInstructorId() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
