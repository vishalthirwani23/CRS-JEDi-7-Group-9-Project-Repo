/**
 * 
 */
package com.flipkart.bean;

import com.flipkart.constant.Role;

/**
 * @author Dell
 *
 */
public class Student extends User{
  // defines student
	
	String branchName;
	int batch;
	int studentId;
	boolean isApproved;
	boolean isRegistered;
	
	
	
	
	public Student(String userId, String name, Role role, String password, String branchName, int studentId, int batch,
			boolean isApproved, boolean isRegistered) {
		super(userId, name, role, password);
		this.branchName = branchName;
		this.batch = batch;
		this.studentId = studentId;
		this.isApproved = isApproved;
		this.isRegistered = isRegistered;
	}
	
	public Student() {}
	
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public int getBatch() {
		return batch;
	}
	public void setBatch(int batch) {
		this.batch = batch;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public boolean isApproved() {
		return isApproved;
	}
	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}
	public boolean isRegistered() {
		return isRegistered;
	}
	public void setRegistered(boolean isRegistered) {
		this.isRegistered = isRegistered;
	}
	
	
}
