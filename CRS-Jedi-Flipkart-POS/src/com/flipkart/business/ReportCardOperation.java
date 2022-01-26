package com.flipkart.business;
/**
 * @author venkat.karthik
 *
 */

import java.util.Map;


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
