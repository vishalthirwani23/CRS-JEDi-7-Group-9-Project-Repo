package com.flipkart.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;


import com.flipkart.exceptions.*;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.ReportCard;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.business.ReportCardOperation;
import com.flipkart.constant.Role;
import com.flipkart.constant.SQLQueriesConstants;
import com.flipkart.constant.*;
import com.flipkart.utils.DBUtils;


public class AdminDaoOperation implements AdminDaoInterface{

	private static volatile AdminDaoOperation instance = null;
	private static Logger logger = Logger.getLogger(AdminDaoOperation.class);
	private PreparedStatement statement = null;

	public AdminDaoOperation(){}

	
	
	Connection connection = DBUtils.getConnection();
	
	public int addAdmin(User admin) throws AdminAccountNotCreatedException {
		
		Connection connection = DBUtils.getConnection();
		if(connection == null)
			System.out.println("DB Connection Error");
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
			logger.error(ex.getMessage());
			throw new AdminAccountNotCreatedException();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage() + "SQL error");
			}
		}
		return adminId;
	}

	public void deleteCourse(int courseCode) throws CourseNotFoundException, CourseNotDeletedException{
		
		statement = null;
		try {
			String sql = SQLQueriesConstants.DELETE_COURSE_QUERY;
			statement = connection.prepareStatement(sql);
			
			statement.setInt(1,courseCode);
			int row = statement.executeUpdate();

			logger.info(row + " entries deleted.");
			if(row == 0) {
				logger.error(courseCode + " not in catalog!");
				throw new CourseNotFoundException(courseCode);
			}
			logger.info("Course with courseCode: " + courseCode + " deleted.");

		}catch(SQLException se) {
			System.out.println(se.getMessage());
			throw new CourseNotDeletedException(courseCode);
		}
		
	}

	public void addCourse(Course course) throws CourseFoundException{
		
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
				logger.error("Course with courseCode: " + course.getCourseCode() + "not added to catalog.");
				throw new CourseFoundException(course.getCourseCode());
			}
			System.out.println("Course with courseCode: " + course.getCourseCode() + " is added to catalog.");
			
		}catch(SQLException se) {
			logger.error(se.getMessage());
			throw new CourseFoundException(course.getCourseCode());
			
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
				user.setStudentId(resultSet.getInt(5));

				userList.add(user);
				
			}
			logger.info(userList.size() + " students have pending-approval.");
			
		}catch(SQLException se) {
			logger.error(se.getMessage());
		}
		
		return userList;
		
	}

	public void approveStudent(int studentId) throws  StudentNotFoundForApprovalException {
		
		statement = null;
		try {
			String sql = SQLQueriesConstants.APPROVE_STUDENT_QUERY;
			statement = connection.prepareStatement(sql);
			
			statement.setInt(1,studentId);
			int row = statement.executeUpdate();
			
			System.out.println(row + " student approved.");
			if(row == 0) {
				//logger.error("Student with studentId: " + studentId + " not found.");
				throw new StudentNotFoundForApprovalException(studentId);
			}
			
			//logger.info("Student with studentId: " + studentId + " approved by admin.");
			
		}catch(SQLException se) {
			logger.error(se.getMessage());
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

			logger.info(row + " user added.");
			if(row == 0) {
				logger.error("User with userId: " + user.getUserId() + " not added.");
				throw new UserNotAddedException(user.getUserId());
			}
			logger.info("User with userId: " + user.getUserId() + " added.");
			
		}catch(SQLException se) {
			logger.error(se.getMessage());
			throw new UserIdAlreadyInUseException(user.getUserId());
		}
		
	}

	public void addProfessor(Professor professor) throws UserIdAlreadyInUseException, ProfessorNotAddedException {
		
		try {
			
			this.addUser(professor);
			
		}catch (UserNotAddedException e) {

			logger.info(e.getMessage());
			throw new ProfessorNotAddedException(professor.getUserId());
			
		}catch (UserIdAlreadyInUseException e) {
			logger.error(e.getMessage());
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

			logger.info(row + " professor added.");
			if(row == 0) {
				logger.error("Professor with professorId: " + professor.getUserId() + " not added.");
				throw new ProfessorNotAddedException(professor.getUserId());
			}
			System.out.println("Professor with professorId: " + professor.getUserId() + " added.");
			
		}catch(SQLException se) {
			logger.error(se.getMessage());
			throw new UserIdAlreadyInUseException(professor.getUserId());
			
		} 
		
	}

	public void assignCourse(int courseCode, String professorId) throws CourseNotFoundException, UserNotFoundException{
		
		statement = null;
		try {
			String sql = SQLQueriesConstants.ASSIGN_COURSE_QUERY;
			statement = connection.prepareStatement(sql);
			
			statement.setString(1,professorId);
			statement.setInt(2,courseCode);
			int row = statement.executeUpdate();

			logger.info(row + " course assigned.");
			if(row == 0) {
				logger.error(courseCode + " not found");
				throw new CourseNotFoundException(courseCode);
			}
			logger.info("Course with courseCode: " + courseCode + " is assigned to professor with professorId: " + professorId + ".");

		
		}catch(SQLException se) {
			logger.error(se.getMessage());
			throw new UserNotFoundException(professorId);
			
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
			logger.info(courseList.size() + " courses in catalogId: " + catalogId + ".");
			
		}catch(SQLException se) {
			logger.error(se.getMessage());
			
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
			logger.info(professorList.size() + " professors in the institute.");
			
		}catch(SQLException se) {
			logger.error(se.getMessage());
			
		}
		return professorList;
	}

	public ReportCard generateReportCard(int studentID) throws StudentNotRegisteredException, GradeNotAddedException, StudentNotApprovedException, FeesPendingException {

		Connection connection = DBUtils.getConnection();
		ReportCard R = new ReportCard();

		try {


				StudentDaoOperation sdo = StudentDaoOperation.getInstance();
				R = sdo.viewReportCard(studentID);
				ReportCardOperation report = new ReportCardOperation();
				PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.GET_SPI);
				statement.setInt(1, studentID);
				ResultSet rs = statement.executeQuery();
				rs.next();
				double spi = rs.getDouble(1);


				PreparedStatement statement1 = connection.prepareStatement(SQLQueriesConstants.GENERATE_REPORT_CARD);
				statement1.setDouble(1, spi);
				statement1.setInt(2, studentID);

				statement1.executeUpdate();


		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
		return R;
	}
}
