/**
 * 
 */
package com.flipkart.business;

import java.util.Map;

/**
 * @author venkat.karthik
 *
 */
public class ReportCardOperation implements ReportCardInterFace {
	public Double calculateGrade(Map<courseName, Grade> coursesTaken) {
		int points = 0;
		int number_courses = coursesTaken.size();
		for(int grade: coursesTaken.valueSet()) {
			points = points + grade;
		}
		Double overallGrade = (double) (points/number_courses);
		return overallGrade;
	}
}
