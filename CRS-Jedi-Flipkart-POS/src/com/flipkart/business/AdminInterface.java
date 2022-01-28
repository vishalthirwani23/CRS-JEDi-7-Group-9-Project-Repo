package com.flipkart.business;

import java.sql.SQLException;

/**
 * @author venkat.karthik
 *
 */

import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.ReportCard;
import com.flipkart.bean.Student;
import com.flipkart.exceptions.*;
import com.flipkart.exceptions.FeesPendingException;
import com.flipkart.exceptions.GradeNotAddedException;
import com.flipkart.exceptions.StudentNotApprovedException;
import com.flipkart.exceptions.StudentNotRegisteredException;

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
	default public void demonstrate(){
		System.out.println("Admin Operation performed");
	}

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
	public int register(String name, String userID, String password) throws AdminAccountNotCreatedException;
	/**
	 * Method to Delete Course from Course Catalog
	 *
	 * @param courseCode : Course Code
	 * @param courseList : Courses available in the catalog
	 * @throws CourseNotFoundException If course is not found
	 * @throws CourseNotDeletedException If course not deleted
	 */
	public void deleteCourse(int courseCode, List<Course> courseList)
			throws CourseNotFoundException, CourseNotDeletedException;

	/**
	 * Method to add Course to Course Catalog
	 *
	 * @param course     : Course object storing details of a course
	 * @param courseList : Courses available in the catalog
	 * @throws CourseFoundException If course is not found
	 */
	public void addCourse(Course course, List<Course> courseList) throws CourseFoundException;



	/**
	 * Method to approve a Student
	 *
	 * @param studentId : Student ID
	 * @param studentList List Of Students
	 * @throws StudentNotFoundForApprovalException If student is not found
	 */
	public void approveStudent(int studentId, List<Student> studentList) throws StudentNotFoundForApprovalException;

	/**
	 * Method to add Professor to DB
	 *
	 * @param professor : Professor Object storing details of a professor
	 * @throws ProfessorNotAddedException If profesor is not found
	 * @throws UserIdAlreadyInUseException If user id is already in use
	 */
	public void addProfessor(Professor professor) throws ProfessorNotAddedException, UserIdAlreadyInUseException;

	/**
	 * Method to assign Course to a Professor
	 *
	 * @param courseCode : Course Code
	 * @param professorId : ID Of Professor
	 * @throws CourseNotFoundException If course is not found
	 * @throws UserNotFoundException If user is not found
	 */
	public void assignCourse(int courseCode, String professorId)
			throws CourseNotFoundException, UserNotFoundException;

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

	/**
	 * Method to view Students yet to be approved by Admin
	 *
	 * @return List of Students with pending admissions
	 */
	public List<Student> viewPendingAdmissions();
}
	/**
	 * Method to generate report card
	 *
	 * @return The ReportCard objecr
	 */
	public ReportCard generateReportCard(int studentID) throws SQLException, StudentNotRegisteredException, GradeNotAddedException, StudentNotApprovedException, FeesPendingException;

}
