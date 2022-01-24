package com.flipkart.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.bean.Student;



/**
 * Interface for Admin Operations
 *
 */

public interface AdminInterface {


	/**
	 * Method to add Admin Account
	 *
	 * @param name : Name of the Admin
	 * @param userID : User ID of the Admin
	 * @param password : password
	 * @return Admin ID
	 */
	public int register(String name, String userID, String password ) ;
	/**
	 * Method to Delete Course from Course Catalog
	 * 
	 * @param courseCode : Course Code
	 * @param courseList : Courses available in the catalog
	 */
	public void deleteCourse(String courseCode, List<Course> courseList);

	/**
	 * Method to add Course to Course Catalog
	 * 
	 * @param course     : Course object storing details of a course
	 * @param courseList : Courses available in the catalog
	 */
	public void addCourse(Course course, List<Course> courseList) ;


	/**
	 * Method to approve a Student
	 * 
	 * @param studentId : Student ID
	 * @param studentList List Of Students
	 */
	public void approveStudent(int studentId, List<Student> studentList) ;

	/**
	 * Method to add Professor to DB
	 * 
	 * @param professor : Professor Object storing details of a professor
	 * @throws ProfessorNotAddedException If professor is not found
	 * @throws UserIdAlreadyInUseException If user id is already in use
	 */
	public void addProfessor(Professor professor) ;

	/**
	 * Method to assign Course to a Professor
	 * 
	 * @param courseCode : Course Code
	 * @param professorId : ID Of Professor
	 * @throws CourseNotFoundException If course is not found
	 * @throws UserNotFoundException If user is not found
	 */
	public void assignCourse(String courseCode, String professorId);

	/**
	 * Method to get list of courses in catalog
	 * 
	 * @param catalogId: Id Of Catalog
	 * @return List of courses in catalog
	 */
	public List<Course> viewCourses(int catalogId);

	/**
	 * View professor in the institute
	 * 
	 * @return List of the professors in the institute
	 */
	public List<Professor> viewProfessors();
}
