package com.flipkart.business;

public class StudentOperation implements StudentInterface {

	private static StudentOperation instance ;
	private StudentOperation() {

	}

	/**
	 * Method to make StudentOperation Singleton
	 * 
	 * @return StudentOperation Instance
	 */
	public static StudentOperation getInstance() {
		instance = new StudentOperation();
		return instance;
	}

	/**
	 * Method to register a student, although student can't login until it's
	 * approved by admin
	 * 
	 * @param name Name
	 * @param userId User ID
	 * @param password Password
	 * @param gender Gender
	 * @param batch Batch number
	 * @param branch Branch
	 * @param address Address of the student
	 * @param country Country Of the student
	 * @return Student ID
	 */
	@Override
	public int register(String name, String userId, String password, int batch, String branch,
			String address, String country)  {
		int studentId=0;
		
	
		return studentId;
	}

	/**
	 * Method to get Student ID from User ID
	 * 
	 * @param userId User ID
	 * @return Student ID
	 */
	@Override
	public int getStudentId(String userId) {
		return 1;

	}

	/**
	 * Method to check if student is approved by Admin or not
	 * 
	 * @param studentId Student ID
	 * @return boolean indicating if student is approved
	 */
	@Override
	public boolean isApproved(int studentId) {
		return true;
	}

}
