package com.flipkart.validator;

import java.util.List;

import com.flipkart.bean.Course;
//import com.flipkart.exception.CourseLimitExceedException;
//import com.flipkart.exception.CourseNotFoundException;
//import com.flipkart.exception.SeatNotAvailableException;

/**
 * Class for Student Validator
 */
public class StudentValidator {

	/**
	 * Method to validate if student is already registered for this particular
	 * course (courseCode) or not
	 */
	public static boolean isRegistered(int courseCode, int studentId, List<Course> registeredCourseList)
//			throws CourseNotFoundException 
	{
		for (Course course : registeredCourseList) {
			if (courseCode == course.getCourseCode()) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Method to validate if courseCode is valid or not
	 *
	 */
	public static boolean isValidCourseCode(int courseCode, List<Course> availableCourseList) {
		for (Course course : availableCourseList) {
			if (courseCode == course.getCourseCode()) {
				return true;
			}
		}

		return false;

	}

}