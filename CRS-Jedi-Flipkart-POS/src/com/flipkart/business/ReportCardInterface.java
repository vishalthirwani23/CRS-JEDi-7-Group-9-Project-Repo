/**
 * 
 */
package com.flipkart.business;

import java.util.HashMap;


/**
 * @author Asus
 *
 */
public interface ReportCardInterface {
	
	/**
	 * Method to get student's SGPA
	 * @param studentId
	 * @param semesterId
	 * @return sgpa for that semester and student
	 */
	public double getSGPA(int studentId, int semesterId) ;
	
	
	
//	/**
//	 * Method to get student's grades for a course
//	 * @param studentId
//	 * @param semesterId
//	 * @param courseId
//	 * @return student's grades for that course 
//	 */
	public HashMap<String, Integer> viewCourseGrade(int studentId, int semesterId, int courseId);
	
}
