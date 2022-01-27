package com.flipkart.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.flipkart.exceptions.*;


import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.constant.Role;
import com.flipkart.constant.SQLQueriesConstants;
import com.flipkart.utils.DBUtils;


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
	
	Connection connection = DBUtils.getConnection();

	public int addAdmin(User admin) throws Exception {
		Connection connection = DBUtils.getConnection();
		int adminId = 0;
		try {
			// open db connection
			PreparedStatement preparedStatement = connection.prepareStatement(SQLQueriesConstants.ADD_USER_QUERY,
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, admin.getUserId());
			preparedStatement.setString(2, admin.getName());
			preparedStatement.setString(3, admin.getPassword());
			preparedStatement.setString(4, admin.getRole().toString());

			int rowsAffected = preparedStatement.executeUpdate();
			ResultSet results = preparedStatement.getGeneratedKeys();
			if (results.next())
				adminId = results.getInt(1);
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			//throw new AdminAccountNotCreatedException();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage() + "SQL error");
				e.printStackTrace();
			}
		}
		return adminId;
	}

	public void deleteCourse(int courseCode) throws Exception{
		
		statement = null;
		try {
			String sql = SQLQueriesConstants.DELETE_COURSE_QUERY;
			statement = connection.prepareStatement(sql);
			
			statement.setInt(1,courseCode);
			int row = statement.executeUpdate();

			System.out.println(row + " entries deleted.");
			if(row == 0) {
				System.out.println(courseCode + " not in catalog!");
				throw new Exception();
			}
			System.out.println("Course with courseCode: " + courseCode + " deleted.");

		}catch(SQLException se) {
			System.out.println(se.getMessage());
			throw new Exception();
		}
		
	}

	public void addCourse(Course course) throws Exception{
		
		statement = null;
		try {
			
			String sql = SQLQueriesConstants.ADD_COURSE_QUERY;
			statement = connection.prepareStatement(sql);
			
			statement.setInt(1, course.getCourseCode());
			statement.setString(2, course.getCourseName());
			
			statement.setInt(3, 1);
			int row = statement.executeUpdate();

			System.out.println(row + " course added");
			if(row == 0) {
				System.out.println("Course with courseCode: " + course.getCourseCode() + "not added to catalog.");
				throw new Exception();
			}
			System.out.println("Course with courseCode: " + course.getCourseCode() + " is added to catalog.");
			
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			throw new Exception();
			
		}
		
	}

	public List<Student> viewPendingAdmissions() {
		
		statement = null;
		List<Student> userList = new ArrayList<Student>();
		try {
			
			String sql = SQLQueriesConstants.VIEW_PENDING_ADMISSION_QUERY;
			statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			while(resultSet.next()) {
				
				Student user = new Student();
				user.setUserId(resultSet.getString(1));
				user.setName(resultSet.getString(2));
				user.setPassword(resultSet.getString(3));
				user.setRole(Role.stringToName(resultSet.getString(4)));
				user.setStudentId(resultSet.getInt(4));
				userList.add(user);
				
			}
			System.out.println(userList.size() + " students have pending-approval.");
			
		}catch(SQLException se) {
			System.out.println(se.getMessage());
		}
		
		return userList;
		
	}

	public void approveStudent(int studentId) throws Exception {
		
		statement = null;
		try {
			String sql = SQLQueriesConstants.APPROVE_STUDENT_QUERY;
			statement = connection.prepareStatement(sql);
			
			statement.setInt(1,studentId);
			int row = statement.executeUpdate();
			
			System.out.println(row + " student approved.");
			if(row == 0) {
				System.out.println("Student with studentId: " + studentId + " not found.");
				throw new Exception();
			}
			
			//logger.info("Student with studentId: " + studentId + " approved by admin.");
			
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			
		}
		
	}

	public void addUser(User user) throws UserNotAddedException, UserIdAlreadyInUseException{
		
		statement = null;
		try {
			
			String sql = SQLQueriesConstants.ADD_USER_QUERY;
			statement = connection.prepareStatement(sql);
			
			statement.setString(1, user.getUserId());
			statement.setString(2, user.getName());
			statement.setString(3, user.getPassword());
			statement.setString(4, user.getRole().toString());
			
			int row = statement.executeUpdate();

			System.out.println(row + " user added.");
			if(row == 0) {
				System.out.println("User with userId: " + user.getUserId() + " not added.");
				throw new UserNotAddedException(user.getUserId());
			}
			System.out.println("User with userId: " + user.getUserId() + " added.");
			
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			throw new UserIdAlreadyInUseException(user.getUserId());
		}
		
	}

	public void addProfessor(Professor professor) throws UserIdAlreadyInUseException, ProfessorNotAddedException {
		
		try {
			
			this.addUser(professor);
			
		}catch (UserNotAddedException e) {

			System.out.println(e.getMessage());
			throw new ProfessorNotAddedException(professor.getUserId());
			
		}catch (UserIdAlreadyInUseException e) {
			System.out.println(e.getMessage());
			throw e;
			
		}
		
		
		statement = null;
		try {
			
			String sql = SQLQueriesConstants.ADD_PROFESSOR_QUERY;
			statement = connection.prepareStatement(sql);
			
			statement.setString(1, professor.getUserId());
			statement.setString(2, professor.getDepartment());
			statement.setString(3, professor.getDesignation());
			int row = statement.executeUpdate();

			System.out.println(row + " professor added.");
			if(row == 0) {
				System.out.println("Professor with professorId: " + professor.getUserId() + " not added.");
				throw new ProfessorNotAddedException(professor.getUserId());
			}
			System.out.println("Professor with professorId: " + professor.getUserId() + " added.");
			
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			throw new UserIdAlreadyInUseException(professor.getUserId());
			
		} 
		
	}

	public void assignCourse(int courseCode, String professorId) throws Exception{
		
		statement = null;
		try {
			String sql = SQLQueriesConstants.ASSIGN_COURSE_QUERY;
			statement = connection.prepareStatement(sql);
			
			statement.setString(1,professorId);
			statement.setInt(2,courseCode);
			int row = statement.executeUpdate();

			System.out.println(row + " course assigned.");
			if(row == 0) {
				System.out.println(courseCode + " not found");
				throw new Exception();
			}
			System.out.println("Course with courseCode: " + courseCode + " is assigned to professor with professorId: " + professorId + ".");

		
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			throw new Exception();
			
		}
		
	}
	
	public List<Course> viewCourses(int catalogId) {
		
		statement = null;
		List<Course> courseList = new ArrayList<>();
		try {
			
			String sql = SQLQueriesConstants.VIEW_COURSE_QUERY;
			statement = connection.prepareStatement(sql);
			statement.setInt(1, catalogId);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				
				Course course = new Course();
				course.setCourseCode(resultSet.getInt(1));
				course.setCourseName(resultSet.getString(2));
				course.setInstructorId(resultSet.getString(3));
				courseList.add(course);
				
			}
			System.out.println(courseList.size() + " courses in catalogId: " + catalogId + ".");
			
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			
		}
		
		return courseList; 
		
	}
	
	public List<Professor> viewProfessors() {
		
		statement = null;
		List<Professor> professorList = new ArrayList<>();
		try {
			
			String sql = SQLQueriesConstants.VIEW_PROFESSOR_QUERY;
			statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				
				Professor professor = new Professor();
				professor.setUserId(resultSet.getString(1));
				professor.setName(resultSet.getString(2));
				
				professor.setDepartment(resultSet.getString(3));
				professor.setDesignation(resultSet.getString(4));
				
				professor.setRole(Role.PROFESSOR);
				professor.setPassword("*********");
				professorList.add(professor);
				
			}
			System.out.println(professorList.size() + " professors in the institute.");
			
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			
		}
		return professorList;
	}
}
