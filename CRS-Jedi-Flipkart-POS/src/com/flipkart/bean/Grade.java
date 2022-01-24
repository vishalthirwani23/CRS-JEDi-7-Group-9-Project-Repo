/**
 * 
 */
package com.flipkart.bean;

/**
 * @author venkat.karthik
 *
 */
public class Grade {
	private int numericGrade;
	
	public Grade() {
		this.numericGrade = 0;
	}
	
	public Grade(int grade) {
		super();
		this.numericGrade = grade;
	}
	
	public int getNumericGrade() {
		return this.numericGrade;
	}
	
	public void setNumericGrade(int numericGrade) {
		this.numericGrade = numericGrade;
	}
}
