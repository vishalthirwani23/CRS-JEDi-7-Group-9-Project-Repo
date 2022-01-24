/**
 * 
 */
package com.crs.flipkart.bean;


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
		super(userName, userId, password, mobileNo, emailID, role);
		this.setcourse(course);
	
	}
}
;