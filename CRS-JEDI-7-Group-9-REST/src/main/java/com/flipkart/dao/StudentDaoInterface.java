
package com.flipkart.dao;

import java.sql.SQLException;
import com.flipkart.bean.Student;
import com.flipkart.bean.ReportCard;
import com.flipkart.exceptions.FeesPendingException;
import com.flipkart.exceptions.StudentNotApprovedException;
import com.flipkart.exceptions.GradeNotAddedException;
import com.flipkart.exceptions.StudentNotRegisteredException;


/**
*
* Interface for Student Operations
*
*/
public interface StudentDaoInterface {

	/**
	 * Method to add student to database
	 *
	 * @param student: student object containing all the fields
	 * @return true if student is added, else false
	 * @throws StudentNotRegisteredException If the student is not registered
	 */
	public int addStudent(Student student) throws StudentNotRegisteredException;

	/**
	 * Method to retrieve Student Id from User Id
	 *
	 * @param userId User Id
	 * @return Student Id
	 */
	public int getStudentId(String userId);

	/**
	 * Method to check if Student is approved
	 *
	 * @param studentId Student Id
	 * @return boolean indicating if student is approved by admin
	 */
	public boolean isApproved(int studentId);

	public ReportCard viewReportCard(int StudentID) throws SQLException, GradeNotAddedException, StudentNotApprovedException, FeesPendingException, StudentNotApprovedException, FeesPendingException;

}

