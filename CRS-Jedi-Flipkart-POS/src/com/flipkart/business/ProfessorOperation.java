package com.flipkart.business;

import com.flipkart.dao.ProfessorDaoOperation;
import com.flipkart.dao.ProfessorDaoInterface;
import com.flipkart.bean.EnrolledStudent;
/**
 * @author venkat.karthik
 *
 */

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.flipkart.bean.Course;


public class ProfessorOperation implements ProfessorInterface {

	private static volatile ProfessorOperation instance = null;
	ProfessorDaoInterface professorDAOInterface = ProfessorDaoOperation.getInstance();

	private ProfessorOperation() {

	}

	public static ProfessorOperation getInstance() {
		if (instance == null) {
			synchronized (ProfessorOperation.class) {
				instance = new ProfessorOperation();
			}
		}
		return instance;
	}

	public boolean addGrade(int studentId, String courseCode, String grade) throws Exception {
		try {
			professorDAOInterface.addGrade(studentId, courseCode, grade);
		} catch (Exception ex) {
			throw new Exception();
		}
		return true;
	}

	public List<EnrolledStudent> viewEnrolledStudents(String profId) throws SQLException {
		List<EnrolledStudent> enrolledStudents = new ArrayList<EnrolledStudent>();
		try {
			enrolledStudents = professorDAOInterface.getEnrolledStudents(profId);
		} catch (Exception ex) {
			throw ex;
		}
		return enrolledStudents;
	}

	public List<Course> getCourses(String profId) {
		List<Course> coursesOffered = new ArrayList<Course>();
		try {
			coursesOffered = professorDAOInterface.getCoursesByProfessor(profId);
		} catch (Exception ex) {
			throw ex;
		}
		return coursesOffered;
	}

	public String getProfessorById(String profId) {
		return professorDAOInterface.getProfessorById(profId);
	}
}
