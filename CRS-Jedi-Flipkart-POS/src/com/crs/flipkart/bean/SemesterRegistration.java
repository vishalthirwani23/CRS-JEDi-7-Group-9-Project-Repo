package com.crs.flipkart.bean;

public class SemesterRegistration {
	private Integer studentID;
	private Integer semesterID;
	private String dateOfRegistration;
	private Boolean isApproved;

	
	public SemesterRegistration() {

	}
	
	public SemesterRegistration(Integer studentID, Integer semesterID, String dateOfRegistration, Boolean isApproved) {
		this.studentID = studentID;
		this.semesterID = semesterID;
		this.dateOfRegistration = dateOfRegistration;
		this.isApproved = isApproved;
	}

	public Integer getStudentID() {
		return studentID;
	}

	public void setStudentID(Integer studentID) {
		this.studentID = studentID;
	}

	public Integer getSemesterID() {
		return semesterID;
	}

	public void setSemesterID(Integer semesterID) {
		this.semesterID = semesterID;
	}

	public String getDateOfRegistration() {
		return dateOfRegistration;
	}

	public void setDateOfRegistration(String dateOfRegistration) {
		this.dateOfRegistration = dateOfRegistration;
	}

	public Boolean getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
	}
	
}
