package com.flipkart.application;

/**
 * @author jayant
 *
 */

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.bean.Course;
import com.flipkart.bean.EnrolledStudent;
import com.flipkart.business.ProfessorInterface;
import com.flipkart.business.ProfessorOperation;
import com.flipkart.exceptions.GradeNotAddedException;
import com.flipkart.validator.ProfessorValidator;


public class ProfessorCRSMenu {

	private static Logger logger = Logger.getLogger(CRSApplication.class);

    ProfessorInterface professorInterface=ProfessorOperation.getInstance();

    /**
     * Method to create Professor menu
     * @param profId: professor id obtained after logging into the system
     * It displays all the options for the professor, and provides navigation
     */
    public void createMenu(String profId)
    {
        //Display the options available for the Professor
        Scanner sc=new Scanner(System.in);

        int input;
        while(CRSApplication.loggedin)
        {
        	
           System.out.println("\n\nProfessor Access Menu \n\n" +  
           "1. View Enrolled Students \n" + 
           "2. Add grade for Student \n" + 
           "3. Logout");

            
            //input user
            input=sc.nextInt();
            switch(input)
            {
                case 1:
                    //view all the courses taught by the professor
                    getCourses(profId);
                    break;
                case 2:
                    //view all the enrolled students for the course
                    viewEnrolledStudents(profId);
                    break;

                case 3:
                    //add grade for a student
                    addGrade(profId);
                    break;
                case 4:
                    //logout from the system
                    CRSApplication.loggedin=false;
                    return;
                default:
                	System.out.println("***** Wrong Choice *****");
            }
        }


    }

    /**
     * Method to view enrolled Students in courses
     * @param profId: professor id obtained after logging into the system
     */
    public void viewEnrolledStudents(String profId)
    {
    	System.out.println("List of Enrolled Students");
        List<Course> coursesEnrolled=professorInterface.getCourses(profId);
        System.out.println(String.format("%20s %20s  %20s","COURSE CODE","COURSE NAME","STUDENT ID" ));
        try
        {
            List<EnrolledStudent> enrolledStudents=new ArrayList<EnrolledStudent>();
            enrolledStudents=professorInterface.viewEnrolledStudents(profId);
            for(EnrolledStudent obj: enrolledStudents)
            {
            	System.out.println(String.format("%20s %20s %20s",obj.getCourseCode(), obj.getCourseName(),obj.getStudentId()));
            }
            
        }
        catch(Exception e)
        {
        	logger.error(e.getMessage()+"Something went wrong, please try again later!");
        }
    }

    /**
     * Method to get list of all Courses Professor has to teach
     * @param profId: professor id obtained after logging into the system
     */
    public void getCourses(String profId)
    {
    	System.out.println("List of All Courses taught by Professor");
        try
        {
            List<Course> coursesEnrolled=professorInterface.getCourses(profId);
            System.out.println(String.format("%20s %20s %20s","COURSE CODE","COURSE NAME","No. of Students  enrolled" ));
            for(Course obj: coursesEnrolled)
            {
            	System.out.println(String.format("%20s %20s %20s",obj.getCourseCode(), obj.getCourseName(),10- obj.getSeats()));
            }
         
        }
        catch(Exception e)
        {
        	logger.error("Something went wrong!"+e.getMessage());
        }
    }

    /**
     * Method to help Professor grade a student
     * @param profId: professor id obtained after logging into the system
     */
    public void addGrade(String profId)
    {
    	System.out.println("Student Courses Data \n");
        Scanner sc=new Scanner(System.in);

        int studentId;
        int courseCode;
        String grade;
        try
        {
            List<EnrolledStudent> enrolledStudents=new ArrayList<EnrolledStudent>();
            enrolledStudents=professorInterface.viewEnrolledStudents(profId);
            System.out.println(String.format("%20s %20s %20s","COURSE CODE","COURSE NAME","Student ID" ));
            for(EnrolledStudent obj: enrolledStudents)
            {
            	System.out.println(String.format("%20s %20s %20s",obj.getCourseCode(), obj.getCourseName(),obj.getStudentId()));
            }
            List<Course> coursesEnrolled=new ArrayList<Course>();
            coursesEnrolled	=professorInterface.getCourses(profId);
            System.out.println("Add Grade");
            System.out.println("Enter student id");
            studentId=sc.nextInt();
            System.out.println("Enter course code");
            courseCode=sc.nextInt();
            System.out.println("Enter grade");
            grade=sc.next();
            if(ProfessorValidator.isValidStudent(enrolledStudents, studentId) && ProfessorValidator.isValidCourse(coursesEnrolled, courseCode))
            {
                professorInterface.addGrade(studentId, courseCode, grade);
                System.out.println("Grade added successfully for "+studentId);
            }
            else
            {
            	System.out.println("Invalid data entered, try again!");
            }
        }
        catch(GradeNotAddedException ex)
        {
            logger.error("Grade cannot be added for"+ex.getStudentId());

        }
        catch(SQLException ex)
        {
            logger.error("Grade not added, SQL exception occurred "+ex.getMessage());
        }
       
    }
}