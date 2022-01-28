package com.flipkart.business;


import com.flipkart.bean.Student;
import com.flipkart.application.CRSApplication;
import com.flipkart.constant.Role;
import com.flipkart.dao.StudentDaoInterface;
import com.flipkart.dao.StudentDaoOperation;



public class StudentOperation implements StudentInterface {

	private static volatile StudentOperation instance = null;
	private static Logger logger = Logger.getLogger.getLogger(CRSApplication.class);
	StudentDaoInterface studentDaoInterface = StudentDaoOperation.getInstance();

	private StudentOperation() {

	}
	public static StudentOperation getInstance() {
		if (instance == null) {
			synchronized (StudentOperation.class) {
				instance = new StudentOperation();
			}
		}
		return instance;
	}


	public int register(String name, String userId, String password, int batch, String branch) throws StudentNotRegisteredException {
		int studentId;
		try {
			Student newStudent = new Student(userId, name, Role.STUDENT, password, branch, 0, batch, false, false);
			studentId = studentDaoInterface.addStudent(newStudent);

		} catch (StudentNotRegisteredException ex) {
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

}
