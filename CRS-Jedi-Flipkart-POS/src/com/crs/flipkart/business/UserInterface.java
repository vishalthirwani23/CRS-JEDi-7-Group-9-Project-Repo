/**
 * 
 */
package com.flipkart.business;

import com.flipkart.exception.*;

/**
 * @author admin
 *
 */
public interface UserInterface {
	
	

	public void modifyCourses(int numberOfStudents) throws UserNotFoundException;
	
	public void approveStudent(int studentID, String, studentName) throws UserNotFoundException;
	
	public void registerProfessor(int professorID, String professorName, ArrayList<String> courses) throws UserNotFoundException;
	
	public void generateReportCard() throws UserNotFoundException;
	

}