/**
 * 
 */
package com.flipkart.business;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Admin;
import com.flipkart.bean.ReportCard;
import com.flipkart.bean.Student;
import com.flipkart.bean.UserDB;
import com.flipkart.bean.*;
import com.flipkart.dao.AdminDaoInterface;
import com.flipkart.dao.AdminDaoOperation;
//import com.flipkart.bean.ReportCard;


/**
 * @author User
 *
 */


public class AdminOperation extends UserDB implements AdminInterface {
	

    
    //private void courseId()
	private static volatile AdminOperation instance = null;

	private AdminOperation() {

	}
	/**
	 * Method to make AdminOperation Singleton
	 *
	 * @return instance of AdminOperation
	 */
	public static AdminOperation getInstance() {
//		
	   instance = new AdminOperation();
		return instance;
	}
	
	AdminDaoInterface adminDaoOperation = AdminDaoOperation.getInstance();
	
	@Override
    public String register(String name, String userID,String password,String email) {
		
		super.putUser(name, userID, password, "9012", email, "Admin");
		return userID;
		
	}
	
	@Override
	public void addCourse(Course course)
	{
		System.out.println(course.getCourseID()+" Added");
	}
	
	@Override
    public void deleteCourse(String courseCode)
    {
    	System.out.println(courseCode+" Deleted");
    }
	
	@Override
	public void approveStudent(Integer studentId) 
	{
		if(super.db.containsKey(studentId)==true)
		{
			System.out.println("Approved");
		}
		else
		{
			System.out.println("Student not found");
		}
	}
	
	public void viewProfessors()
	{
		for (HashMap.Entry<String,User> entry : super.db.entrySet())
		{
			if(entry.getValue().getRole()=="Professor")
				System.out.println("Professor name "+entry.getValue().getUserName()+" Emai is "+entry.getValue().getEmailID());
		}
	}
	
	public void addProfessor(String name,String ID,String password,String mobile,String email,String role)
	{
		super.putUser(name, ID, password, mobile, email, "Professor");
	}
	
	
    
    
    
    
	
	
	

	
	


	
	


}
