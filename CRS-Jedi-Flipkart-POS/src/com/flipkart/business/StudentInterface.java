package com.flipkart.business;
/**
 * @author venkat.karthik
 *
 */

import java.sql.SQLException;
import com.flipkart.bean.ReportCard;
import com.flipkart.exceptions.FeesPendingException;
import com.flipkart.exceptions.GradeNotAddedException;
import com.flipkart.exceptions.StudentNotApprovedException;


public interface StudentInterface {


	public int register(String name, String userID, String password, int batch, String branch) throws Exception;

	public int getStudentId(String userId);

	public boolean isApproved(int studentId);
	public ReportCard viewReportCard(int StudentID) throws SQLException, GradeNotAddedException, StudentNotApprovedException, FeesPendingException;
	
}
