/**
 * 
 */
package com.flipkart.bean;

import java.util.List;

/**
 * @author jayant
 *
 */
// used for creating JSON payloads for registerCourse API
public class CourseList {
	public List<Integer> courseIds;
    public int studentId;
    public CourseList()
    {	
    	
    }
    public CourseList(List<Integer> courseIds,int studentId)
    {
   
    	this.courseIds = courseIds;
    	this.studentId = studentId;
    }
}
