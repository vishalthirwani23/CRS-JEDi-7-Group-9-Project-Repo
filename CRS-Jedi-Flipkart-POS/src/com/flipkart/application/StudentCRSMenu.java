package com.flipkart.application;

import java.sql.SQLException;
import java.util.*;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.flipkart.bean.Course;
import com.flipkart.bean.StudentGrade;
import com.flipkart.constant.ModeOfPayment;
import com.flipkart.constant.NotificationType;
import com.flipkart.business.NotificationInterface;
import com.flipkart.business.NotificationOperation;
import com.flipkart.business.ProfessorInterface;
import com.flipkart.business.ProfessorOperation;
import com.flipkart.business.RegistrationInterface;
import com.flipkart.business.RegistrationOperation;

public class StudentCRSMenu {
    Scanner sc = new Scanner(System.in);
    RegistrationInterface registrationInterface = RegistrationOperation.getInstance();
    ProfessorInterface professorInterface = ProfessorOperation.getInstance();
    NotificationInterface notificationInterface=NotificationOperation.getInstance();
    private boolean is_registered;

    public void create_menu(int studentId) throws Exception
    {

        is_registered = getRegistrationStatus(studentId);
        while (CRSApplication.loggedin)
        {
            System.out.println("\n\n  Student Access Menu  \n\n "+
            		"1. Register for Courses\n"+
                    "2. Add New Course to Semester\n"+
                    "3. Drop Course from Semester\n"+
                    "4. View Available Courses\n"+
                    "5. View Registered Courses\n"+
                    "6. View Semester Grade Card\n"+
                    "7. Pay Fees for Courses\n"+
                    "8. Logout\n");

     

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    registerCourses(studentId);
                    break;

                case 2:
                    addCourse(studentId);
                    break;

                case 3:

                    dropCourse(studentId);
                    break;

                case 4:
                    viewCourse(studentId);
                    break;

                case 5:
                    viewRegisteredCourse(studentId);
                    break;

                case 6:
                    viewGradeCard(studentId);
                    break;

                case 7:
                    make_payment(studentId);
                    break;

                case 8:
                    CRSApplication.loggedin = false;
                    return;

                default:
                	System.out.println("***** Wrong Choice *****");
            }
        }
    }



    private void registerCourses(int studentId) throws Exception
    {
        if(is_registered)
        {
        	System.out.println("Registration is already completed");
            return;
        }

        int count = 0;
        System.out.println("\n\nCourse Registration Portal  \n\n");
        while(count < 6)
        {
            try
            {
                List<Course> courseList=viewCourse(studentId);

                if(courseList==null)
                    return;

                System.out.println("Enter Course Code : ");
                System.out.println("Enter 0 to exit");
                int courseCode = sc.nextInt();

                if (courseCode == 0){
                    break;
                }

                if(registrationInterface.addCourse(courseCode,studentId,courseList))
                {
                    System.out.println("Course " + courseCode + " registered sucessfully.");
                    count++;
                }
                else
                {
                    System.err.println("You have already registered for Course : " + courseCode);
                }
            }
            catch(SQLException e)
            {
            	System.out.println(e.getMessage());
            }
        }

        System.out.println("\nRegistration Successful  \n");
        is_registered = true;

        try
        {
            registrationInterface.setRegistrationStatus(studentId);
        }
        catch (SQLException e)
        {
        	
        }

    }

    private void addCourse(int studentId) throws Exception
    {
        if(is_registered)
        {
        	System.out.println("\nAdd Course Portal for Student  \n");

            List<Course> availableCourseList=viewCourse(studentId);

            if(availableCourseList==null)
                return;

            try
            {
                System.out.println("Enter Course Code : " );
                int courseCode = sc.nextInt();
                if(registrationInterface.addCourse(courseCode, studentId,availableCourseList))
                {
                	System.out.println("You have successfully registered for Course : " + courseCode);
                }
                else
                {
                	System.out.println("You have already registered for Course : " + courseCode);
                }
            }
            catch( SQLException e)
            {
            	System.out.println(e.getMessage());
            }
        }
        else
        {
        	System.out.println("Please complete registration for courses");
        }
    }

   
    private boolean getRegistrationStatus(int studentId)
    {
        try
        {
            return registrationInterface.getRegistrationStatus(studentId);
        }
        catch (SQLException e)
        {
        	System.out.println(e.getMessage());
        }
        return false;
    }


    private void dropCourse(int studentId)
    {
        if(is_registered)
        {
        	System.out.println("\nDrop Course Portal for Student\n");
            List<Course> registeredCourseList=viewRegisteredCourse(studentId);

            if(registeredCourseList==null)
                return;

            System.out.println("Enter the Course Code : ");
            int courseCode = sc.nextInt();

            try
            {
                registrationInterface.dropCourse(courseCode, studentId,registeredCourseList);
                System.out.println("You have successfully dropped Course : " + courseCode);

            }
            catch(Exception e)
            {
            	System.out.println("You have not registered for course : ");
            }
          
        }
        else
        {
        	System.out.println("Please complete registration for Courses");
        }
    }

    
    private List<Course> viewCourse(int studentId)

    {
    	System.out.println("List of Available Courses");
        List<Course> course_available=null;
        try
        {
            course_available = registrationInterface.viewCourses(studentId);
        }
        catch (SQLException e)
        {

        	System.out.println(e.getMessage());
        }


        if(course_available.isEmpty())
        {
        	System.out.println("NO COURSE AVAILABLE");
            return null;
        }


        System.out.println(String.format("%-20s %-20s %-20s %-20s","COURSE CODE", "COURSE NAME", "INSTRUCTOR", "SEATS"));
        for(Course obj : course_available)
        {
        	System.out.println(String.format("%-20s %-20s %-20s %-20s",obj.getCourseCode(), obj.getCourseName(),obj.getInstructorId(), obj.getSeats()));
        }
        
        return course_available;
    }
    

    private List<Course> viewRegisteredCourse(int studentId)
    {
    	System.out.println("List of Registered Courses");
        List<Course> course_registered=null;
        try
        {
            course_registered = registrationInterface.viewRegisteredCourses(studentId);
        }
        catch (SQLException e)
        {

        	System.out.println(e.getMessage());
        }

        if(course_registered.isEmpty())
        {
        	System.out.println("You haven't registered for any course");
            return null;
        }

        System.out.println(String.format("%-20s %-20s %-20s","COURSE CODE", "COURSE NAME", "INSTRUCTOR"));

        for(Course obj : course_registered)
        {


        	System.out.println(String.format("%-20s %-20s %-20s ",obj.getCourseCode(), obj.getCourseName(),professorInterface.getProfessorById(obj.getInstructorId())));
        }
        
        return course_registered;
    }


    private void viewGradeCard(int studentId) {

    	System.out.println("GRADE CARD");
        List<StudentGrade> gradeCard = null;
        try {
            gradeCard = registrationInterface.viewGradeCard(studentId);
        } catch (SQLException e) {

        	System.out.println(e.getMessage());
        }

        if (gradeCard.isEmpty()) {
        	System.out.println("You haven't registered for any course");
            return;
        }

        System.out.println(String.format("%-20s %-20s %-20s %-20s", "COURSE CODE", "COURSE NAME", "GRADE", "SCORE"));

        List<StudentGrade> graded = gradeCard.stream().filter((StudentGrade studentGrade)->{ return studentGrade.getGrade() != null; }).collect(Collectors.toList());
        List<StudentGrade> unGraded = gradeCard.stream().filter((StudentGrade studentGrade)->{ return studentGrade.getGrade() == null; }).collect(Collectors.toList());

        double total_score = 0;
        if (!graded.isEmpty()) {
            System.out.println("Graded Courses : ");
            for (StudentGrade studentGrade : graded) {
            	System.out.println(String.format("  %-20s %-20s %-20s %-20s", studentGrade.getCourseCode(),
                        studentGrade.getCourseName(), studentGrade.getGrade(), getScore(studentGrade.getGrade())));
                total_score += getScore(studentGrade.getGrade());
            }
        }
        if (!unGraded.isEmpty()) {
        	System.out.println("Grade Awaited : ");
           
            unGraded.forEach(studentGrade -> System.out.println(String.format("  %-20s %-20s %-20s %-20s", studentGrade.getCourseCode(),
                    studentGrade.getCourseName(), "NA", "NA")));
        }
        if(!graded.isEmpty())
        {
            
        	System.out.println(String.format("  %-20s %-20s %-20s %-20s", "",
                    "", "CGPA", total_score/(double)graded.size()));
        }
       
    }

    private static Map<String, Integer> gradeStrToScore;

    static {
        gradeStrToScore = new HashMap<>();
        gradeStrToScore.put("A", 10);
        gradeStrToScore.put("B", 9);
        gradeStrToScore.put("C", 8);
        gradeStrToScore.put("D", 7);
        gradeStrToScore.put("E", 6);
        gradeStrToScore.put("F", 5);
        gradeStrToScore.put("NA", 0);
        gradeStrToScore.put("EX", 0);
    }

    public int getScore(String grade) {
        if (gradeStrToScore.containsKey(grade))
            return gradeStrToScore.get(grade);
        return 0;
    }

    private void make_payment(int studentId)
    {

    	System.out.println("Payment Portal");
        double fee =0.0;
        try
        {
            fee=registrationInterface.calculateFee(studentId);
        }
        catch (SQLException e)
        {

        	System.out.println(e.getMessage());
        }

        if(fee == 0.0)
        {
        	System.out.println("You have not  registered for any courses yet");
        }
        else
        {

            System.out.println("Your total fee  = " + fee);
            System.out.println("Want to continue Fee Payment(y/n)");
            String ch = sc.next();
            if(ch.equals("y"))
            {
                System.out.println("Select Mode of Payment:");

                int index = 1;
                for(ModeOfPayment mode : ModeOfPayment.values())
                {
                    System.out.println(index + " " + mode);
                    index = index + 1;
                }

                ModeOfPayment mode = ModeOfPayment.getModeofPayment(sc.nextInt());
                String temp = sc.nextLine();
                if(mode == null)
                	System.out.println("Invalid Input");
                else
                {
                    System.out.println("Please Enter The Card Number:");
                    String cardNumber = sc.nextLine();

                    System.out.println("Please Enter your CVV Number");
                    String cvv = sc.nextLine();

                    try
                    {
                        notificationInterface.sendNotification(NotificationType.PAYMENT, studentId, mode, fee, cardNumber, cvv);
                    }
                    catch (Exception e)
                    {

                    	System.out.println(e.getMessage());
                    }
                }

            }

        }

    }
}

