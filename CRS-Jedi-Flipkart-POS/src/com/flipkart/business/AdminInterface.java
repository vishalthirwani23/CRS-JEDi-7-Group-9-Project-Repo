package com.flipkart.business;

import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;



/**
 *
 *
 * Interface for Admin Operations
 * 
 */
public interface AdminInterface {

	/**
	 * Method to demonstrate admin operation performed
	 */
//	default public void demonstrate(){
//		System.out.println("Admin Operation performed");
//	}

	/**
	 * Method to add Admin Account
	 *
	 * @param name : Name of the Admin
	 * @param userID : User ID of the Admin
	 * @param password : password
	 * @param gender : Gender of the Admin
	 * @param address  : Address Of the Admin
	 * @param country : Country Of the Admin
	 * @throws AdminAccountNotCreatedException If admin account is not created
	 * @return Admin ID
	 */
	public String register(String name, String userID, String password,String email);
	/**
	 * Method to Delete Course from Course Catalog
	 * 
	 * @param courseCode : Course Code
	 * @param courseList : Courses available in the catalog
	 * @throws CourseNotFoundException If course is not found
	 * @throws CourseNotDeletedException If course not deleted
	 */
	public void deleteCourse(String courseCode);

	/**
	 * Method to add Course to Course Catalog
	 * 
	 * @param course     : Course object storing details of a course
	 * @param courseList : Courses available in the catalog
	 * @throws CourseFoundException If course is not found
	 */
	public void addCourse(Course course);


	/**
	 * Method to approve a Student
	 * 
	 * @param studentId : Student ID
	 * @param studentList List Of Students
	 * @throws StudentNotFoundForApprovalException If student is not found
	 */
	public void approveStudent(Integer studentId) ;

	

	


	/**
	 * View professor in the institute
	 * 
	 * @return List of the professors in the institute
	 */
	public void viewProfessors();
	
	public void addProfessor (String name,String ID,String password,String mobile,String email,String role);
	
	
	
	
	
}