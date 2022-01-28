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
import com.flipkart.exceptions.FeesPendingException;
import com.flipkart.exceptions.GradeNotAddedException;
import com.flipkart.exceptions.StudentNotApprovedException;
import com.flipkart.exceptions.StudentNotRegisteredException;


public interface AdminInterface {

	default public void demonstrate(){
		System.out.println("Admin Operation performed");
	}

	public int register(String name, String userID, String password) throws Exception;

	public void deleteCourse(int courseCode, List<Course> courseList)
			throws Exception;

	public void addCourse(Course course, List<Course> courseList) throws Exception;

	public List<Student> viewPendingAdmissions();

	public void approveStudent(int studentId, List<Student> studentList) throws Exception;

	public void addProfessor(Professor professor) throws Exception;

	public void assignCourse(int courseCode, String professorId)
			throws Exception;

	public List<Course> viewCourses(int catalogId);

	public List<Professor> viewProfessors();
	
	public ReportCard generateReportCard(int studentID) throws SQLException, StudentNotRegisteredException, GradeNotAddedException, StudentNotApprovedException, FeesPendingException;

}
