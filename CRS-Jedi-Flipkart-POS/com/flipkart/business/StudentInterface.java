/**
 * 
 */
package com.flipkart.business;

/**
 * @author jayant
 *
 */
public interface StudentInterface {
	public int register(String name, String userID, String password, int batch, String branch,
			String address, String country);

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
}
