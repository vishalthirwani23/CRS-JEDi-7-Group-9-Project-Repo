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
	private int semester;
	private HashMap<String,Double> grades = new HashMap<String,Double>();
	private ArrayList<Integer> courseIDs = new ArrayList<Integer>();
	
	
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
	 * @return the semester
	 */
	public int getSemester() {
		return semester;
	}
	/**
	 * @param semester the semester to set
	 */
	public void setSemester(int semester) {
		this.semester = semester;
	}
	/**
	 * @return the grades
	 */
	public HashMap<String, Double> getGrades() {
		return grades;
	}
	/**
	 * @param grades the grades to set
	 */
	public void setGrades(HashMap<String, Double> grades) {
		this.grades = grades;
	}
	/**
	 * @return the courseIDs
	 */
	public ArrayList<Integer> getCourseIDs() {
		return courseIDs;
	}
	/**
	 * @param courseIDs the courseIDs to set
	 */
	public void setCourseIDs(ArrayList<Integer> courseIDs) {
		this.courseIDs = courseIDs;
	}

	
}
