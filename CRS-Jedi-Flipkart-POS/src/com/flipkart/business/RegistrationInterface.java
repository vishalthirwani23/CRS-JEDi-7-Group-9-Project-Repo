package com.flipkart.business;

/**
 * @author venkat.karthik
 *
 */

import java.sql.SQLException;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.StudentGrade;

public interface RegistrationInterface {

	public boolean addCourse(String courseCode, int studentId, List<Course> courseList)
			throws Exception;

	public boolean dropCourse(String courseCode, int studentId, List<Course> registeredCourseList)
			throws Exception;

	public List<Course> viewCourses(int studentId) throws SQLException;

	public List<Course> viewRegisteredCourses(int studentId) throws SQLException;

	public List<StudentGrade> viewGradeCard(int studentId) throws SQLException;

	public double calculateFee(int studentId) throws SQLException;

	public boolean getRegistrationStatus(int studentId) throws SQLException;

	public void setRegistrationStatus(int studentId) throws SQLException;

}
