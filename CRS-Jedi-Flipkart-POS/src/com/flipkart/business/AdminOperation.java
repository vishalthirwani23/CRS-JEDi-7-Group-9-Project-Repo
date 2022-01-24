/**
 * 
 */
package com.flipkart.business;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.ReportCard;
import com.flipkart.bean.Student;
//import com.flipkart.bean.ReportCard;


/**
 * @author User
 *
 */


public class AdminOperation implements AdminInterface {
	

    
    //private void courseId()
	private static volatile AdminOperation instance = null;

	public AdminOperation() {

	}
	/**
	 * Method to make AdminOperation Singleton
	 *
	 * @return instance of AdminOperation
	 */
	public static AdminOperation getInstance() {
		if (instance == null) {
			synchronized (AdminOperation.class) {
				instance = new AdminOperation();
			}
		}
		return instance;
	}

	


	@Override
	public void deleteCourse(String courseCode,List<Course> courseList) {
		// TODO Auto-generated method stub
		//ado removes course
		
	}

	@Override
	public void addCourse(Course course_name,) {
		// TODO Auto-generated method stub
		Course newCourse = new Course();
		newCourse.setCoursename(course_name);
		newCourse.setCourseID(courseID);
		newCourse.setOfferedSemester(semester);
		newCourse.setAvailableSeats(10);
		
		//ado adds course
		
	}

	
	@Override
	public HashMap<String,ArrayList<Integer> > viewCourseStudentList(String courseID, int semester, Boolean viewAll) {
		
//		AdminDaoOperation ado1 = new AdminDaoOperation();
		//ado gives permission to view list
	}

	@Override
	public ReportCard generateReportCard(int studentID)
			
		ReportCard R = new ReportCard();
		//ado gives permission to grades
		return R;
	}

	
	@Override
	public List<Student> getPendingStudentAccountsList() {

		//returns list
		
	}

	@Override
	public void approveStudentAccount(Integer studentID) {
		//ado approves account		
	}
	
	


}
