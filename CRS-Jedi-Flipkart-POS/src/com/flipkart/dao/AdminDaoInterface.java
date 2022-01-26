package com.flipkart.dao;

import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.exception.*;


public interface AdminDaoInterface {


	public int addAdmin(User admin) throws Exception;

	public void deleteCourse(String courseCode) throws Exception;

	public void addCourse(Course course) throws Exception;

	public void approveStudent(int studentId) throws Exception;

	public void addProfessor(Professor professor) throws Exception;

	public void addUser(User user) throws Exception;

	public void assignCourse(String courseCode, String professorId)
			throws Exception;

	public List<Course> viewCourses(int catalogId);

	public List<Professor> viewProfessors();
}
