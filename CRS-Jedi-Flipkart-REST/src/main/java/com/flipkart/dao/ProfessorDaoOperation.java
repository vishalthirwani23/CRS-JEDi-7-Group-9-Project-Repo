package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.flipkart.bean.Course;
import com.flipkart.bean.EnrolledStudent;
import com.flipkart.constant.SQLQueriesConstants;
import com.flipkart.utils.DBUtils;


public class ProfessorDaoOperation implements ProfessorDaoInterface {

	private static volatile ProfessorDaoOperation instance = null;
	private static Logger logger = Logger.getLogger(UserDaoOperation.class);

	public ProfessorDaoOperation() {

	}


	public static ProfessorDaoOperation getInstance() {
		if (instance == null) {
			// This is a synchronized block, when multiple threads will access this instance
			synchronized (ProfessorDaoOperation.class) {
				instance = new ProfessorDaoOperation();
			}
		}
		return instance;
	}


	public List<Course> getCoursesByProfessor(String profId) {
		DBUtils connectionObj = new DBUtils(); Connection connection = connectionObj.getConnection();
		List<Course> courseList = new ArrayList<Course>();
		try {
			PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.GET_COURSES);

			statement.setString(1, profId);

			ResultSet results = statement.executeQuery();
			while (results.next()) {
				courseList.add(new Course(results.getInt("courseCode"), results.getString("courseName"),
						results.getString("professorId"), results.getInt("seats")));
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
			}
		}
		return courseList;

	}


	public List<EnrolledStudent> getEnrolledStudents(String profId) {
		DBUtils connectionObj = new DBUtils(); Connection connection = connectionObj.getConnection();
		List<EnrolledStudent> enrolledStudents = new ArrayList<EnrolledStudent>();
		try {
			PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.GET_ENROLLED_STUDENTS);
			statement.setString(1, profId);

			ResultSet results = statement.executeQuery();
			while (results.next()) {
				// public EnrolledStudent(String courseCode, String courseName, int studentId)
				enrolledStudents.add(new EnrolledStudent(results.getInt("courseCode"),
						results.getString("courseName"), results.getInt("studentId")));
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
			}
		}
		return enrolledStudents;
	}


	public Boolean addGrade(int studentId, int courseCode, String grade) {
		DBUtils connectionObj = new DBUtils(); Connection connection = connectionObj.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.ADD_GRADE);

			statement.setString(1, grade);
			statement.setInt(2, courseCode);
			statement.setInt(3, studentId);

			int row = statement.executeUpdate();

			if (row == 1)
				return true;
			else
				return false;
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
			}
		}
		return false;
	}


	public String getProfessorById(String profId) {
		String prof_Name = null;
		DBUtils connectionObj = new DBUtils(); Connection connection = connectionObj.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.GET_PROF_NAME);

			statement.setString(1, profId);
			ResultSet rs = statement.executeQuery();
			rs.next();

			prof_Name = rs.getString(1);

		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
			}
		}

		return prof_Name;
	}
}
