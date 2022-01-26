package com.flipkart.business;
/**
 * @author venkat.karthik
 *
 */

import java.sql.SQLException;

public interface StudentInterface {


	public int register(String name, String userID, String password, int batch, String branch) throws Exception;

	public int getStudentId(String userId);

	public boolean isApproved(int studentId);
}
