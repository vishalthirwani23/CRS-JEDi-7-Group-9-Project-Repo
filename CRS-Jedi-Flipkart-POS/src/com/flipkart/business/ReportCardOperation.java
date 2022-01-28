package com.flipkart.business;
/**
 * @author venkat.karthik
 *
 */

import java.util.HashMap;
import java.util.Map;
import com.flipkart.bean.ReportCard;

public class ReportCardOperation implements ReportCardInterface {
	
	public Float getSPI(ReportCard RC) {
		HashMap<Integer, Double> grades = RC.getGrades();
		float sum = 0.0f;
		int num_courses=0;
		for (Double g : grades.values()) {
		    sum += g;
		    num_courses++;
		}
		sum=sum/num_courses;
		return sum;
	}
}
