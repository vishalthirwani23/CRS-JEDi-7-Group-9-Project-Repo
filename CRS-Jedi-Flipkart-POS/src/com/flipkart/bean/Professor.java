/**
 * 
 */
package com.flipkart.bean;

import com.flipkart.constant.Role;

/**
 * @author Dell
 *
 */
public class Professor extends User{
	
	private String course;
	public Professor() {
		// to be used as a data structure.
	}
	public void setcourse(String course)
	{
		this.course=course;
	}
	public String getcourse() {
		return course;
	}

	
	public Professor(String userName, String userId,  String password, String mobileNo, String emailID, String role, String course) {
		//super(userName, userId, password, mobileNo, emailID, role);
		this.setcourse(course);
	
	}
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getDesignation() {
		// TODO Auto-generated method stub
		return null;
	}
	public void setName(String professorName) {
		// TODO Auto-generated method stub
		
	}
	public void setDesignation(String designation) {
		// TODO Auto-generated method stub
		
	}
	public void setDepartment(String department) {
		// TODO Auto-generated method stub
		
	}
	public void setRole(Role stringToName) {
		// TODO Auto-generated method stub
		
	}
}
;