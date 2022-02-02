/**
 * 
 */
package com.flipkart.bean;

import java.util.List;

/**
 * @author jayant
 *
 */
public class POJO {
	public List<Integer> courseIds;
    public int studentId;
    public POJO()
    {	
    	
    }
    public POJO(List<Integer> courseIds,int studentId)
    {
   
    	this.courseIds = courseIds;
    	this.studentId = studentId;
    }
}
