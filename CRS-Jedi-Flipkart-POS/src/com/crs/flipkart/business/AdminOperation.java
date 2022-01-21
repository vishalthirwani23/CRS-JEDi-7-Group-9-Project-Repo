/**
 * 
 */
package com.crs.flipkart.business;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.flipkart.bean.Course;
//import com.flipkart.bean.ReportCard;


/**
 * @author User
 *
 */


public class AdminOperation implements AdminInterface {
	
//	AdminDaoOperation ado = new AdminDaoOperation();
	private static volatile AdminOperation instance = null;
	
	private AdminOperation()
	{
		
	}
	
	/**
	 * Method to make AdminOperation Singleton
	 */
	public static AdminOperation getInstance()
	{
		if(instance == null)
		{
			synchronized(AdminOperation.class){
				instance = new AdminOperation();
			}
		}
		return instance;
	}
	


	@Override
	public void addProfessor(Professor professor) {
		
		//ado adds professor
		
	}

	@Override
	public void removeProfessor(int professorID) {
		//ado rmoves professor
		
	}


	@Override
	public void removeCourse(String courseID) {
		// TODO Auto-generated method stub
		//ado removes course
		
	}

	@Override
	public void addCourse(String course_name, String courseID, int semester) {
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
