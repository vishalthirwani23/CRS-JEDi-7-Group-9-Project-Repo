package com.flipkart.business;



import java.sql.SQLException;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.StudentGrade;
import com.flipkart.dao.RegistrationDaoInterface;
import com.flipkart.dao.RegistrationDaoOperation;
import com.flipkart.exceptions.CourseLimitExceedException;
import com.flipkart.exceptions.CourseNotFoundException;
import com.flipkart.exceptions.SeatNotAvailableException;
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

	public boolean addCourse(int courseCode, int studentId, List<Course> availableCourseList)
			throws CourseNotFoundException, CourseLimitExceedException, SeatNotAvailableException, SQLException {

		if (registrationDaoInterface.numOfRegisteredCourses(studentId) >= 6) {
			throw new CourseLimitExceedException(6);
		} else if (registrationDaoInterface.isRegistered(courseCode, studentId)) {
			return false;
		} else if (!registrationDaoInterface.seatAvailable(courseCode)) {
			throw new SeatNotAvailableException(courseCode);
		} else if (!StudentValidator.isValidCourseCode(courseCode, availableCourseList)) {
			throw new CourseNotFoundException(courseCode);
		}

		return registrationDaoInterface.addCourse(courseCode, studentId);

	}

	public boolean dropCourse(int courseCode, int studentId, List<Course> registeredCourseList)
			throws CourseNotFoundException, SQLException {
		if (!StudentValidator.isRegistered(courseCode, studentId, registeredCourseList)) {
			throw new CourseNotFoundException(courseCode);
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
