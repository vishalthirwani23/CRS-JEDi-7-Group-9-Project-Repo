
package com.flipkart.dao;

import java.sql.SQLException;
import com.flipkart.bean.Student;
import com.flipkart.bean.ReportCard;
import com.flipkart.exceptions.FeesPendingException;
import com.flipkart.exceptions.StudentNotApprovedException;
import com.flipkart.exceptions.GradeNotAddedException;

//Interface for Student Operations

public interface StudentDaoInterface {

	//Method to add student to database
	
	public int addStudent(Student student) throws Exception;

	// Method to retrieve Student Id from User Id
	
	public int getStudentId(String userId);

	// Method to check if Student is approved
	
	public boolean isApproved(int studentId);
	
	public ReportCard viewReportCard(int StudentID, int semesterId) throws SQLException, GradeNotAddedException, StudentNotApprovedException, FeesPendingException, StudentNotApprovedException, FeesPendingException;

}

