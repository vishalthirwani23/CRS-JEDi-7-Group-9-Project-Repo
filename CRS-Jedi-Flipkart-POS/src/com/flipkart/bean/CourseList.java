/**
 * 
 */
package com.flipkart.bean;
import java.util.HashMap;


/**
 * @author User
 *
 */
public class CourseList extends Course{
	
	
	private HashMap<String,Course> list = new HashMap<String,Course>();
	
	public void addCourse(String courseID,String coursename,String instructorID,Integer totalSeats,Integer availableSeats,Integer offeredSemester)
	{
		Course course=new Course();
		course.setAvailableSeats(availableSeats);
		course.setCourseID(courseID);
		course.setCoursename(coursename);
		course.setInstructorID(instructorID);
		course.setOfferedSemester(offeredSemester);
		course.setTotalSeats(totalSeats);
		list.put(courseID, course);
		
	}
	
	public void deleteCourse(String courseID)
	{
		if(list.containsKey(courseID))
		{
			list.remove(courseID);
		}
		else
			System.out.println("Not found course");
	}
	

}
