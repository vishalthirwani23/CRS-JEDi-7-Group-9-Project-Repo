/**
 * 
 */
package com.flipkart.bean;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * @author tushar
 *
 */
public class ReportCard {
	
	private int studentId;
	private HashMap<Integer,Double> grades = new HashMap<Integer,Double>();
	private ArrayList<Integer> courseIDs = new ArrayList<Integer>();
	private Boolean isVisible;
	private Double spi;
	/**
	 * @return the studentId
	 */
	public int getStudentId() {
		return studentId;
	}
	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	/**
	 * @return the grades
	 */
	public HashMap<Integer, Double> getGrades() {
		return grades;
	}
	/**
	 * @param grades the grades to set
	 */
	public void setGrades(HashMap<Integer, Double> grades) {
		this.grades = grades;
	}
	/**
	 * @return the courseIDs
	 */
	public ArrayList<Integer> getCourseIDs() {
		return courseIDs;
	}
	
	public Double getSpi() {
		return spi;
	}
	
	public Boolean getIsVisible() {
		return isVisible;
	}
	
	public void setIsVisible(Boolean isVisible) {
		this.isVisible = isVisible;
	} 
	

	/**
	 * @param courseIDs the courseIDs to set
	 */
	public void setCourseIDs(ArrayList<Integer> courseIDs) {
		this.courseIDs = courseIDs;
	}
	public void setSpi(Double spi) {
		this.spi = spi;
	}


	
}
