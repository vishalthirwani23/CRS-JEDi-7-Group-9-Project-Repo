
package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import java.util.HashMap;

import com.flipkart.bean.Student;
import com.flipkart.bean.ReportCard;
import com.flipkart.constant.SQLQueriesConstants;
import com.flipkart.exceptions.StudentNotRegisteredException;
import com.flipkart.business.StudentOperation;
import com.flipkart.utils.DBUtils;

import com.flipkart.exceptions.FeesPendingException;
import com.flipkart.exceptions.StudentNotApprovedException;
import com.flipkart.exceptions.GradeNotAddedException;
import com.flipkart.exceptions.ReportCardNotGeneratedException;


public class StudentDaoOperation implements StudentDaoInterface {

	private static volatile StudentDaoOperation instance = null;
	private static Logger logger = Logger.getLogger(StudentOperation.class);


	private StudentDaoOperation() {

	}

	public static StudentDaoOperation getInstance() {
		if (instance == null) {
			// This is a synchronized block, when multiple threads will access this instance
			synchronized (StudentDaoOperation.class) {
				instance = new StudentDaoOperation();
			}
		}
		return instance;
	}

	@Override
	public int addStudent(Student student) throws StudentNotRegisteredException {

		DBUtils connectionObj = new DBUtils(); Connection connection = connectionObj.getConnection();
		int studentId = 0;
		try {
			// open db connection
			PreparedStatement preparedStatement = connection.prepareStatement(SQLQueriesConstants.ADD_USER_QUERY);
			preparedStatement.setString(1, student.getUserId());
			preparedStatement.setString(2, student.getName());
			preparedStatement.setString(3, student.getPassword());
			preparedStatement.setString(4, student.getRole().toString());

			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected == 1) {
				// add the student record
				// "insert into student (userId,branchName,batch,isApproved) values (?,?,?,?)";
				PreparedStatement preparedStatementStudent;
				preparedStatementStudent = connection.prepareStatement(SQLQueriesConstants.ADD_STUDENT,
						Statement.RETURN_GENERATED_KEYS);
				preparedStatementStudent.setString(1, student.getUserId());
				preparedStatementStudent.setString(2, student.getBranchName());
				preparedStatementStudent.setInt(3, student.getBatch());
				preparedStatementStudent.setBoolean(4, false);
				preparedStatementStudent.executeUpdate();
				ResultSet results = preparedStatementStudent.getGeneratedKeys();
				if (results.next())
					studentId = results.getInt(1);
			}

		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new StudentNotRegisteredException(student.getName());

		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage() + "SQL error");
			}
		}
		return studentId;
	}

	@Override
	public int getStudentId(String userId) {
		DBUtils connectionObj = new DBUtils(); Connection connection = connectionObj.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.GET_STUDENT_ID);
			statement.setString(1, userId);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				return rs.getInt("studentId");
			}

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		return 0;
	}

	@Override
	public boolean isApproved(int studentId) {
		DBUtils connectionObj = new DBUtils(); Connection connection = connectionObj.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.IS_APPROVED);
			statement.setInt(1, studentId);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				return rs.getBoolean("isApproved");
			}

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		return false;
	}
  
	public ReportCard viewReportCard(int StudentID) throws SQLException, GradeNotAddedException , StudentNotApprovedException, FeesPendingException{

		DBUtils connectionObj = new DBUtils(); Connection connection = connectionObj.getConnection();

		ReportCard R = new ReportCard();
		R.setStudentId(StudentID);

		try
		{
			PreparedStatement preparedStatement=connection.prepareStatement(SQLQueriesConstants.GET_REPORT);
			preparedStatement.setInt(1, StudentID);
			ResultSet rs = preparedStatement.executeQuery();
			HashMap<Integer,Double> grades = new HashMap<Integer, Double>();

			while (rs.next()) {

					if(rs.getInt(4) == 0) {
						continue;
					}

					grades.put(rs.getInt(3), (double) rs.getInt(4));
					System.out.println(rs.getInt(4));

			}
			if(grades.isEmpty()) throw new ReportCardNotGeneratedException();
			R.setIsVisible(true);
			R.setGrades(grades);

			Double spi;
			PreparedStatement ps = connection.prepareStatement(SQLQueriesConstants.GET_SPI);
			ps.setInt(1, StudentID);

			rs = ps.executeQuery();
			rs.next();
			spi = rs.getDouble(1);
			System.out.println(spi);
			R.setSpi(spi);

		}

		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}

		return R;
	}

}

