package com.flipkart.validator;

import java.util.List;
import com.flipkart.bean.Course;
import com.flipkart.bean.EnrolledStudent;

/**
 * Class for Professor Validator
 */
public class ProfessorValidator {

	/**
	 * Method to check if Student exist in the database
	 */
	public static boolean isValidStudent(List<EnrolledStudent> students, int studentId) {
		boolean result = false;
		// check if student exist in the students list
		for (int i = 0; i < students.size(); i++) {
			// role.equalsIgnoreCase("ADMIN")
			if (students.get(i).getStudentId() == studentId)
				result = true;

		}
		return result;
	}

	/**
	 * Method to check if course exist in the database
	 *
	 */
	public static boolean isValidCourse(List<Course> assignedCourses, int courseCode) {
		// check if course is valid
		boolean result = false;
		for (int i = 0; i < assignedCourses.size(); i++) {
			if (assignedCourses.get(i).getCourseCode() == courseCode)
				result = true;
		}
		return result;
	}

}