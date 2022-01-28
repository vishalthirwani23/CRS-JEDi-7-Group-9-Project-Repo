/**
 * 
 */
package com.flipkart.business;

/**
 * @author venkat.karthik
 *
 */
import com.flipkart.bean.Course;
import com.flipkart.bean.EnrolledStudent;

import java.sql.SQLException;
import java.util.List;

public interface ProfessorInterface {

	public boolean addGrade(int studentId, int courseCode, String grade) throws Exception;

	public List<EnrolledStudent> viewEnrolledStudents(String profId) throws SQLException;

	public List<Course> getCourses(String profId);

	public String getProfessorById(String profId);
}

