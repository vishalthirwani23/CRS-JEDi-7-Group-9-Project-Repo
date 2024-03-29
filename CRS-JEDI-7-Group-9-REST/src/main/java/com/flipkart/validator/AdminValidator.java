package com.flipkart.validator;

import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;

/**
 *
 *
 * Class for Admin Validator
 *
 */
public class AdminValidator {

	/**
	 * Method to validate if newCourse is not already present in catalog
	 *
	 */
	public static boolean isValidNewCourse(Course newCourse, List<Course> courseList) {
		for (Course course : courseList) {
			if (newCourse.getCourseCode() == course.getCourseCode()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Method to validate if dropCourse is already present in catalog
	 *
	 */
	public static boolean isValidDropCourse(int dropCourseCode, List<Course> courseList) {
		for (Course course : courseList) {
			if (dropCourseCode == course.getCourseCode()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Method to validate if studentId is still unapproved
	 *
	 */
	public static boolean isValidUnapprovedStudent(int studentId, List<Student> studentList) {
		for (Student student : studentList) {
			if (studentId == student.getStudentId()) {
				return true;
			}
		}
		return false;
	}
}