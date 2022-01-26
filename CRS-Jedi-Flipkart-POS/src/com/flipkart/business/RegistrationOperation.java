package com.flipkart.business;

/**
 * @author venkat.karthik
 *
 */

import java.sql.SQLException;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.StudentGrade;
import com.flipkart.dao.RegistrationDaoInterface;
import com.flipkart.dao.RegistrationDaoOperation;
import com.flipkart.validator.StudentValidator;


public class RegistrationOperation implements RegistrationInterface {

	private static volatile RegistrationOperation instance = null;

	private RegistrationOperation() {
	}

	public static RegistrationOperation getInstance() {
		if (instance == null) {
			synchronized (RegistrationOperation.class) {
				instance = new RegistrationOperation();
			}
		}
		return instance;
	}

	RegistrationDaoInterface registrationDaoInterface = RegistrationDaoOperation.getInstance();

	public boolean addCourse(String courseCode, int studentId, List<Course> availableCourseList)
			throws Exception {

		if (registrationDaoInterface.numOfRegisteredCourses(studentId) >= 6) {
			throw new Exception();
		} else if (registrationDaoInterface.isRegistered(courseCode, studentId)) {
			return false;
		} else if (!registrationDaoInterface.seatAvailable(courseCode)) {
			throw new Exception();
		} else if (!StudentValidator.isValidCourseCode(courseCode, availableCourseList)) {
			throw new Exception(courseCode);
		}

		return registrationDaoInterface.addCourse(courseCode, studentId);

	}

	public boolean dropCourse(String courseCode, int studentId, List<Course> registeredCourseList)
			throws Exception {
		if (!StudentValidator.isRegistered(courseCode, studentId, registeredCourseList)) {
			throw new Exception();
		}

		return registrationDaoInterface.dropCourse(courseCode, studentId);

	}

	public double calculateFee(int studentId) throws SQLException {
		return registrationDaoInterface.calculateFee(studentId);
	}

	public List<StudentGrade> viewGradeCard(int studentId) throws SQLException {
		return registrationDaoInterface.viewGradeCard(studentId);
	}

	public List<Course> viewCourses(int studentId) throws SQLException {
		return registrationDaoInterface.viewCourses(studentId);
	}

	public List<Course> viewRegisteredCourses(int studentId) throws SQLException {
		return registrationDaoInterface.viewRegisteredCourses(studentId);
	}

	public boolean getRegistrationStatus(int studentId) throws SQLException {
		return registrationDaoInterface.getRegistrationStatus(studentId);
	}

	public void setRegistrationStatus(int studentId) throws SQLException {
		registrationDaoInterface.setRegistrationStatus(studentId);

	}

}
