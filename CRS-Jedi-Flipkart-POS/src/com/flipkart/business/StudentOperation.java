package com.flipkart.business;
/**
 * @author venkat.karthik
 *
 */

import com.flipkart.bean.Student;

import java.sql.SQLException;

import com.flipkart.application.CRSApplication;
import com.flipkart.constant.Role;
import com.flipkart.dao.StudentDaoInterface;
import com.flipkart.dao.StudentDaoOperation;
import com.flipkart.exceptions.FeesPendingException;
import com.flipkart.exceptions.GradeNotAddedException;
import com.flipkart.exceptions.StudentNotApprovedException;
import com.flipkart.bean.ReportCard;



public class StudentOperation implements StudentInterface {

	private static volatile StudentOperation instance = null;
	StudentDaoInterface studentDaoInterface = StudentDaoOperation.getInstance();

	public StudentOperation() {

	}
	public static StudentOperation getInstance() {
		if (instance == null) {
			synchronized (StudentOperation.class) {
				instance = new StudentOperation();
			}
		}
		return instance;
	}


	public int register(String name, String userId, String password, int batch, String branch) throws Exception {
		int studentId;
		try {
			Student newStudent = new Student(userId, name, Role.STUDENT, password, branch, 0, batch, false, false);
			studentId = studentDaoInterface.addStudent(newStudent);

		} catch (Exception ex) {
			throw ex;
		}
		return studentId;
	}

	public int getStudentId(String userId) {
		return studentDaoInterface.getStudentId(userId);

	}

	public boolean isApproved(int studentId) {
		return studentDaoInterface.isApproved(studentId);
	}
	public ReportCard viewReportCard(int StudentID) throws SQLException, GradeNotAddedException, StudentNotApprovedException, FeesPendingException  {

		StudentDaoOperation SDO= StudentDaoOperation.getInstance();
		return SDO.viewReportCard(StudentID);
		
	}
	
}
