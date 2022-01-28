package com.flipkart.business;
import java.sql.SQLException;
/**
 * @author venkat.karthik
 *
 */
import java.util.List;

import com.flipkart.bean.*;
import com.flipkart.constant.Role;

import com.flipkart.dao.AdminDaoInterface;
import com.flipkart.dao.AdminDaoOperation;
import com.flipkart.exceptions.FeesPendingException;
import com.flipkart.exceptions.GradeNotAddedException;
import com.flipkart.exceptions.StudentNotApprovedException;
import com.flipkart.exceptions.StudentNotRegisteredException;
import com.flipkart.validator.AdminValidator;

public class AdminOperation implements AdminInterface {

	private static volatile AdminOperation instance = null;

	private AdminOperation() {

	}

	public static AdminOperation getInstance() {
		if (instance == null) {
			synchronized (AdminOperation.class) {
				instance = new AdminOperation();
			}
		}
		return instance;
	}

	AdminDaoInterface adminDaoOperation = AdminDaoOperation.getInstance();

	public int register(String name, String userID, String password) throws Exception{
		int adminId = 0;
		try {
			User admin = new Admin(userID, name, Role.ADMIN, password);
			adminId = adminDaoOperation.addAdmin(admin) ;

		} catch (Exception ex) {
			throw ex;
		}
		return adminId;
	}

	public void deleteCourse(int dropCourseCode, List<Course> courseList)
			throws Exception{

		if (!AdminValidator.isValidDropCourse(dropCourseCode, courseList)) {
			System.out.println("courseCode: " + dropCourseCode + " not present in catalog!");
			throw new Exception();
		}

		try {
			adminDaoOperation.deleteCourse(dropCourseCode);
		} catch (Exception e) {
			throw e;
		}

	}

	public void addCourse(Course newCourse, List<Course> courseList) throws Exception {

		if (!AdminValidator.isValidNewCourse(newCourse, courseList)) {
			System.out.println("courseCode: " + newCourse.getCourseCode() + " already present in catalog!");
			throw new Exception();
		}

		try {
			adminDaoOperation.addCourse(newCourse);
		} catch (Exception e) {
			throw e;
		}

	}

	public List<Student> viewPendingAdmissions() {
		return adminDaoOperation.viewPendingAdmissions();
	}

	public void approveStudent(int studentId, List<Student> studentList) throws Exception {

		if (!AdminValidator.isValidUnapprovedStudent(studentId, studentList)) {
			throw new Exception();
		}

		try {
			adminDaoOperation.approveStudent(studentId);
		} catch (Exception e) {
			throw e;
		}

	}

	public void addProfessor(Professor professor) throws Exception {

		try {
			adminDaoOperation.addProfessor(professor);
		} catch (Exception e) {
			throw e;
		}

	}

	public void assignCourse(int courseCode, String professorId)
			throws Exception {

		try {
			adminDaoOperation.assignCourse(courseCode, professorId);
		} catch (Exception e) {
			throw e;
		}

	}

	public List<Course> viewCourses(int catalogId) {

		return adminDaoOperation.viewCourses(catalogId);

	}

	public List<Professor> viewProfessors() {

		return adminDaoOperation.viewProfessors();

	}

	@Override
	public ReportCard generateReportCard(int studentID) throws SQLException, StudentNotRegisteredException,
			GradeNotAddedException, StudentNotApprovedException, FeesPendingException {
		// TODO Auto-generated method stub
		return adminDaoOperation.generateReportCard(studentID);
		
	}

}
