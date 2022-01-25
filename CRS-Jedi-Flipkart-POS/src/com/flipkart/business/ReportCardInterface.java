/**
 * 
 */
package com.flipkart.business;

import java.util.HashMap;


/**
 * @author venkat.karthik
 *
 */
public interface ReportCardInterface {
	

	public double getSGPA(int studentId, int semesterId) ;
	

	public HashMap<String, Integer> viewCourseGrade(int studentId, int semesterId, int courseId);
	
}
