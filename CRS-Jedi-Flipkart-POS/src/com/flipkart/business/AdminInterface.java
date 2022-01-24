package com.flipkart.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.flipkart.bean.Course;
//import com.flipkart.bean.Professor;
//import com.flipkart.bean.ReportCard;
//import com.flipkart.bean.Student;


/**
 * @author rutwi
 *
 */

public class AdminInterface {
	
	/**
	 * @param students
	 * @throws StudentNotApprovedException 
	 * @throws FeesPendingException 
	 */
	public void approveStudentRegistration(int studentId,int semesterId);
	
	/**
	 * @param professor
	 */
	public void addProfessor(Professor professor);
	
	/**
	 * @param professor
	 */
	public void removeProfessor(int professorID);
	
	/**
	 * @param studentID
	 * @return 
	 * @throws StudentNotApprovedException 
	 * @throws FeesPendingException 
	 */
	public ReportCard generateReportCard(int studentID);
	
	/**
	 * @param courseID
	 * @param courseCatalog
	 */
	public void removeCourse(String courseID);
	
	/**
	 * @param courseID
	 * @param courseCatalog
	 */
	public void addCourse(String course_name, String courseID, int semester);

	public HashMap<String, ArrayList<Integer>> viewCourseStudentList(String courseID, int semester, Boolean viewAll);

	
}
