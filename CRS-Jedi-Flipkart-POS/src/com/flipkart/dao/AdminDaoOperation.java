package com.flipkart.dao;

import java.util.ArrayList;
import java.util.List;


import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.constant.Role;
import com.mysql.jdbc.PreparedStatement;


public class AdminDaoOperation implements AdminDaoInterface{

	private static volatile AdminDaoOperation instance = null;
	private PreparedStatement statement = null;

	private AdminDaoOperation(){}

	public static AdminDaoOperation getInstance()
	{
		if(instance == null)
		{
			synchronized(AdminDaoOperation.class){
				instance = new AdminDaoOperation();
			}
		}
		return instance;
	}
	
	@Override
	public int addAdmin(User admin) {
		
		int adminId = 0;
		
		return adminId;
	}

	@Override
	public void deleteCourse(String courseCode) {
		
		System.out.println("Course deleted");
		
	}

	@Override
	public void addCourse(Course course) {
		
		
		System.out.println("New course added");
	}

	

	@Override
	public void approveStudent(int studentId) {
		
		
		System.out.println("student approved");
	}

	@Override
	public void addUser(User user) {
		
		System.out.println("user added");
		
	}

	@Override
	public void addProfessor(Professor professor) {
		
		System.out.println("professor added");
		
	}
	
	@Override
	public void assignCourse(String courseCode, String professorId) {
		
		System.out.println("course assigned");
		
	}
	
	public List<Course> viewCourses(int catalogId) {
		
		
		List<Course> courseList = new ArrayList<>();
		
		
		return courseList; 
		
	}
	
	@Override
	public List<Professor> viewProfessors() {
		
		
		List<Professor> professorList = new ArrayList<>();
		
		return professorList;
	}
}