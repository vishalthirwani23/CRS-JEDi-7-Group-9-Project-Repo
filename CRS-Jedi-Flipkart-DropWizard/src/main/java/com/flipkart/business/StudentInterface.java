package com.flipkart.business;


import java.sql.SQLException;
import com.flipkart.bean.ReportCard;
import com.flipkart.exceptions.FeesPendingException;
import com.flipkart.exceptions.GradeNotAddedException;
import com.flipkart.exceptions.StudentNotApprovedException;


import com.flipkart.exceptions.StudentNotRegisteredException;

/**
 *
 *
 * Interface for Student Operations
 *
 */
public interface StudentInterface {

	/**
	 * Method to register a student, although student can't login until it's
	 * approved by admin
	 *
	 * @param name Name Of the Student
	 * @param userID User ID
	 * @param password Password
	 * @param gender Gender
	 * @param batch Batch number
	 * @param branch Branch name
	 * @param address Address of the student
	 * @param country Country
	 * @return Student ID
	 * @throws StudentNotRegisteredException If Student is not registered
	 */
	public int register(String name, String userID, String password, int batch, String branch) throws StudentNotRegisteredException;

	/**
	 * Method to get Student ID from User ID
	 *
	 * @param userId User ID
	 * @return Student ID
	 */
	public int getStudentId(String userId);

	/**
	 * Method to check if student is approved by Admin or not
	 *
	 * @param studentId Student ID
	 * @return boolean indicating if student is approved
	 */
	public boolean isApproved(int studentId);

	/**
	 * Method to view report card by using student ID
	 *
	 * @param studentId Student ID
	 * @return ReportCard
	 */
	public ReportCard viewReportCard(int StudentID) throws SQLException, GradeNotAddedException, StudentNotApprovedException, FeesPendingException;

}
