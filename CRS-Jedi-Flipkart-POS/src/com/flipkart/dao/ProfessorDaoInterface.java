package com.flipkart.dao;

import java.util.*;
import com.flipkart.bean.Course;
import com.flipkart.bean.EnrolledStudent;
import com.flipkart.bean.Student;

public interface ProfessorDaoInterface {

	public List<Course> getCoursesByProfessor(String userId);

	public List<EnrolledStudent> getEnrolledStudents(String profId);

	public Boolean addGrade(int studentId, int courseCode, String grade);

	public String getProfessorById(String profId);
}
