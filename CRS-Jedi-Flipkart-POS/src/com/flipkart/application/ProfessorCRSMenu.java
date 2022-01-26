package com.flipkart.application;

/**
 * @author jayant
 *
 */

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.flipkart.bean.Course;
import com.flipkart.bean.EnrolledStudent;
import com.flipkart.business.ProfessorInterface;
import com.flipkart.business.ProfessorOperation;
import com.flipkart.validator.ProfessorValidator;



public class ProfessorCRSMenu {


    ProfessorInterface professorInterface=ProfessorOperation.getInstance();

    public void createMenu(String profId)
    {
        //Display the options available for the Professor
        Scanner sc=new Scanner(System.in);

        int input;
        while(CRSApplication.loggedin)
        {
           System.out.println("1 Professor Access Menu \n"+"2 View Enrolled Students"+
                    "\n 3 Add grade for Student"+ "\n 4 Logout");

            
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
        	System.out.println("Something went wrong, please try again later!");
        }
    }


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
        	System.out.println("Something went wrong!");
        }
    }


    public void addGrade(String profId)
    {
    	System.out.println("Student Courses Data \n");
        Scanner sc=new Scanner(System.in);

        int studentId;
        String courseCode,grade;
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
            courseCode=sc.next();
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
        catch(Exception e)
        {
        	System.out.println("Grade cannot be added for");

        }
       
    }
}