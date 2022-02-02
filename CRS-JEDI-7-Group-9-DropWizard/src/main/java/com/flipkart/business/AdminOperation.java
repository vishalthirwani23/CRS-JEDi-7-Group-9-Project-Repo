package com.flipkart.business;
import java.sql.SQLException;
/**
 * @author venkat.karthik
 *
 */
import java.util.List;

import org.apache.log4j.Logger;


import com.flipkart.bean.*;
import com.flipkart.constant.Role;

import com.flipkart.dao.AdminDaoInterface;
import com.flipkart.dao.AdminDaoOperation;
import com.flipkart.exceptions.FeesPendingException;
import com.flipkart.exceptions.GradeNotAddedException;
import com.flipkart.exceptions.StudentNotApprovedException;
import com.flipkart.exceptions.StudentNotRegisteredException;
import com.flipkart.exceptions.AdminAccountNotCreatedException;
import com.flipkart.exceptions.CourseFoundException;
import com.flipkart.exceptions.CourseNotDeletedException;
import com.flipkart.exceptions.CourseNotFoundException;
import com.flipkart.exceptions.ProfessorNotAddedException;
import com.flipkart.exceptions.StudentNotFoundForApprovalException;
import com.flipkart.exceptions.UserIdAlreadyInUseException;
import com.flipkart.exceptions.UserNotFoundException;
import com.flipkart.validator.AdminValidator;

public class AdminOperation implements AdminInterface {
	private static Logger logger = Logger.getLogger(AdminOperation.class);
	private static volatile AdminOperation instance = null;

	public AdminOperation() {

	}

	
	AdminDaoInterface adminDaoOperation = new AdminDaoOperation();

	public int register(String name, String userID, String password) throws AdminAccountNotCreatedException{
		int adminId = 0;
		try {
			User admin = new Admin(userID, name, Role.ADMIN, password);
			adminId = adminDaoOperation.addAdmin(admin) ;

		} catch (AdminAccountNotCreatedException ex) {
			throw ex;
		}
		return adminId;
	}

	public void deleteCourse(int dropCourseCode, List<Course> courseList)
			throws CourseNotFoundException, CourseNotDeletedException {

		if (!AdminValidator.isValidDropCourse(dropCourseCode, courseList)) {
			logger.error("courseCode: " + dropCourseCode + " not present in catalog!");
			throw new CourseNotFoundException(dropCourseCode);
		}

		try {
			adminDaoOperation.deleteCourse(dropCourseCode);
		} catch (CourseNotFoundException | CourseNotDeletedException e) {
			throw e;
		}

	}

	public void addCourse(Course newCourse, List<Course> courseList) throws CourseFoundException {

		if (!AdminValidator.isValidNewCourse(newCourse, courseList)) {
			System.out.println("courseCode: " + newCourse.getCourseCode() + " already present in catalog!");
			throw new CourseFoundException(newCourse.getCourseCode());
		}

		try {
			adminDaoOperation.addCourse(newCourse);
		} catch (CourseFoundException e) {
			throw e;
		}

	}


	public void approveStudent(int studentId, List<Student> studentList) throws StudentNotFoundForApprovalException {

		if (!AdminValidator.isValidUnapprovedStudent(studentId, studentList)) {
			throw new StudentNotFoundForApprovalException(studentId);
		}

		try {
			adminDaoOperation.approveStudent(studentId);
		} catch (StudentNotFoundForApprovalException e) {
			throw e;
		}

	}

	public void addProfessor(Professor professor) throws ProfessorNotAddedException, UserIdAlreadyInUseException {

		try {
			adminDaoOperation.addProfessor(professor);
		} catch (ProfessorNotAddedException | UserIdAlreadyInUseException  e) {
			throw e;
		}

	}

	public void assignCourse(int courseCode, String professorId)
			throws CourseNotFoundException, UserNotFoundException {

		try {
			adminDaoOperation.assignCourse(courseCode, professorId);
		} catch (CourseNotFoundException | UserNotFoundException e) {
			throw e;
		}

	}

	public List<Course> viewCourses(int catalogId) {

		return adminDaoOperation.viewCourses(catalogId);

	}

	public List<Professor> viewProfessors() {

		return adminDaoOperation.viewProfessors();

	}

	public List<Student> viewPendingAdmissions() {
		return adminDaoOperation.viewPendingAdmissions();
	}

	@Override
	public ReportCard generateReportCard(int studentID) throws SQLException, StudentNotRegisteredException,
			GradeNotAddedException, StudentNotApprovedException, FeesPendingException {
		// TODO Auto-generated method stub
		return adminDaoOperation.generateReportCard(studentID);

	}

}
