
package com.flipkart.dao;

import java.sql.SQLException;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Notification;
import com.flipkart.bean.StudentGrade;
import com.flipkart.constant.ModeOfPayment;


//Interface for Registration DAO Operation
 
public interface RegistrationDaoInterface {

	// Method to add course in database

	public boolean addCourse(String courseCode, int studentId) throws SQLException;

	//Drop Course selected by student
	
	public boolean dropCourse(String courseCode, int studentId) throws SQLException;

	//Method to get the list of courses available from course catalogue
	
	public List<Course> viewCourses(int studentId) throws SQLException;

	//Method to View list of Registered Courses
	
	public List<Course> viewRegisteredCourses(int studentId) throws SQLException;

	//Method to view grade card of the student
	
	public List<StudentGrade> viewGradeCard(int studentId) throws SQLException;

	//Method to retrieve fees for the selected courses from the database and
	 
	public double calculateFee(int studentId) throws SQLException;

	// Check if seat is available for that particular course
	
	public boolean seatAvailable(String courseCode) throws SQLException;

	// Method to get the list of courses registered by the student Number of
	 
	public int numOfRegisteredCourses(int studentId) throws SQLException;

	//Method checks if the student is registered for that course
	 
	public boolean isRegistered(String courseCode, int studentId) throws SQLException;

	//Method to get student registration status
	 
	public boolean getRegistrationStatus(int studentId) throws SQLException;

	//Method to set student registration status
	