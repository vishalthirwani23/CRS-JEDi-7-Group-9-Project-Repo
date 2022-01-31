package com.flipkart.application;

import java.sql.SQLException;


import java.util.*;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import com.flipkart.bean.Course;
import com.flipkart.bean.StudentGrade;
import com.flipkart.constant.ModeOfPayment;
import com.flipkart.constant.NotificationType;
import com.flipkart.exceptions.FeesPendingException;
import com.flipkart.exceptions.GradeNotAddedException;
import com.flipkart.exceptions.StudentNotApprovedException;
import com.flipkart.exceptions.CourseLimitExceedException;
import com.flipkart.exceptions.CourseNotFoundException;
import com.flipkart.exceptions.SeatNotAvailableException;
import com.flipkart.business.NotificationInterface;
import com.flipkart.business.NotificationOperation;
import com.flipkart.business.ProfessorInterface;
import com.flipkart.business.ProfessorOperation;
import com.flipkart.business.RegistrationInterface;
import com.flipkart.business.RegistrationOperation;
import com.flipkart.business.StudentOperation;
import com.flipkart.bean.ReportCard;
import com.flipkart.exceptions.CourseNotFoundException;

public class StudentCRSMenu {
	private static Logger logger = Logger.getLogger(StudentCRSMenu.class.getName());

    Scanner sc = new Scanner(System.in);
    RegistrationInterface registrationInterface = RegistrationOperation.getInstance();
    ProfessorInterface professorInterface = ProfessorOperation.getInstance();
    NotificationInterface notificationInterface=NotificationOperation.getInstance();
    private boolean is_registered;

    /**
     * Method to generate Student Menu for course registration, addition, removal and fee payment
     *
     * @param studentId student id
     */
    public void create_menu(int studentId)
    {

        is_registered = getRegistrationStatus(studentId);
        while (CRSApplication.loggedin)
        {
            logger.info("\n\n  Student Access Menu  \n\n "+
            		"1. Register for Courses\n"+
                    "2. Add New Course to Semester\n"+
                    "3. Drop Course from Semester\n"+
                    "4. View Available Courses\n"+
                    "5. View Registered Courses\n"+
                    "6. View Grade Card\n"+
                    "7. Pay Fees for Courses\n"+
                    "8. Logout \n");

     

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
                	logger.info("***** Wrong Choice *****");
            }
        }
    }


    /**
     * Select course for registration
     * @param studentId student id
     * @param studentId
     * */
    private void registerCourses(int studentId)
    {
        if(is_registered)
        {
        	logger.info("Registration is already completed");
            return;
        }

        int count = 0;
        logger.info("\n\nCourse Registration Portal  \n\n");
        while(count < 6)
        {
            try
            {
                List<Course> courseList=viewCourse(studentId);

                if(courseList==null)
                    return;

                logger.info("Enter Course Code : ");
                logger.info("Enter 0 to exit");
                int courseCode = sc.nextInt();

                if (courseCode == 0){
                    break;
                }

                if(registrationInterface.addCourse(courseCode,studentId,courseList))
                {
                    logger.info("Course " + courseCode + " registered sucessfully.");
                    count++;
                }
                else
                {
                    System.err.println("You have already registered for Course : " + courseCode);
                }
            }
            catch(CourseNotFoundException | CourseLimitExceedException | SeatNotAvailableException | SQLException e)
            {
                logger.error(e.getMessage());
            }
        }

        logger.info("\nRegistration Successful  \n");
        is_registered = true;

        try
        {
            registrationInterface.setRegistrationStatus(studentId);
        }
        catch (SQLException e)
        {
        	logger.error(e.getMessage());
        }

    }

    /**
     * Add course for registration
     *
     * @param studentId
     */
    private void addCourse(int studentId)
    {
        if(is_registered)
        {
        	logger.info("\nAdd Course Portal for Student  \n");

            List<Course> availableCourseList=viewCourse(studentId);

            if(availableCourseList==null)
                return;

            try
            {
                logger.info("Enter Course Code : " );
                int courseCode = sc.nextInt();
                if(registrationInterface.addCourse(courseCode, studentId,availableCourseList))
                {
                	logger.info("You have successfully registered for Course : " + courseCode);
                }
                else
                {
                	logger.info("You have already registered for Course : " + courseCode);
                }
            }
            catch(CourseNotFoundException | CourseLimitExceedException | SeatNotAvailableException | SQLException e)
            {
                logger.error(e.getMessage());
            }
        }
        else
        {
        	logger.info("Please complete registration for courses");
        }
    }

    /**
     * Method to check if student is already registered
     *
     * @param studentId student id
     * @param studentId
     * @return Registration Status
     */
    private boolean getRegistrationStatus(int studentId)
    {
        try
        {
            return registrationInterface.getRegistrationStatus(studentId);
        }
        catch (SQLException e)
        {
        	logger.error(e.getMessage());
        }
        return false;
    }

    /**
     * Drop Course
     *
     * @param studentId student id
     * @param studentId
     */
    private void dropCourse(int studentId)
    {
        if(is_registered)
        {
        	logger.info("\nDrop Course Portal for Student\n");
            List<Course> registeredCourseList=viewRegisteredCourse(studentId);

            if(registeredCourseList==null)
                return;

            logger.info("Enter the Course Code : ");
            int courseCode = sc.nextInt();

            try
            {
                registrationInterface.dropCourse(courseCode, studentId,registeredCourseList);
                logger.info("You have successfully dropped Course : " + courseCode);

            }
            catch(CourseNotFoundException e)
            {
                logger.error("You have not registered for course : " + e.getCourseCode());
            }
            catch (SQLException e)
            {

                logger.error(e.getMessage());
            }
          
        }
        else
        {
        	logger.info("Please complete registration for Courses");
        }
    }

    /**
     * View all available Courses
     *
     * @param studentId student id
     * @param studentId
     * @return List of available Courses
     */
    private List<Course> viewCourse(int studentId)

    {
    	logger.info("List of Available Courses");
        List<Course> course_available=null;
        try
        {
            course_available = registrationInterface.viewCourses(studentId);
        }
        catch (SQLException e)
        {

        	logger.error(e.getMessage());
        }


        if(course_available.isEmpty())
        {
        	logger.error("NO COURSE AVAILABLE");
            return null;
        }


        logger.info(String.format("%-20s %-20s %-20s %-20s","COURSE CODE", "COURSE NAME", "INSTRUCTOR", "SEATS"));
        for(Course obj : course_available)
        {
        	logger.info(String.format("%-20s %-20s %-20s %-20s",obj.getCourseCode(), obj.getCourseName(),obj.getInstructorId(), obj.getSeats()));
        }
        
        return course_available;
    }
    
    /**
     * View Registered Courses
     *
     * @param studentId student id
     * @param studentId
     * @return List of Registered Courses
     */
    private List<Course> viewRegisteredCourse(int studentId)
    {
    	logger.info("List of Registered Courses");
        List<Course> course_registered=null;
        try
        {
            course_registered = registrationInterface.viewRegisteredCourses(studentId);
        }
        catch (SQLException e)
        {

        	logger.error(e.getMessage());
        }

        if(course_registered.isEmpty())
        {
        	logger.info("You haven't registered for any course");
            return null;
        }

        logger.info(String.format("%-20s %-20s %-20s","COURSE CODE", "COURSE NAME", "INSTRUCTOR"));

        for(Course obj : course_registered)
        {


        	logger.info(String.format("%-20s %-20s %-20s ",obj.getCourseCode(), obj.getCourseName(),professorInterface.getProfessorById(obj.getInstructorId())));
        }
        
        return course_registered;
    }

    /**
     * View grade card for particular student
     *
     * @param studentId student id
     * @param studentId
     */
    private void viewGradeCard(int studentId) {

    	logger.info("GRADE CARD");
        List<StudentGrade> gradeCard = null;
        try {
            gradeCard = registrationInterface.viewGradeCard(studentId);
        } catch (SQLException e) {

        	logger.error(e.getMessage());
        }

        if (gradeCard.isEmpty()) {
        	logger.info("You haven't registered for any course");
            return;
        }

        logger.info(String.format("%-20s %-20s %-20s %-20s", "COURSE CODE", "COURSE NAME", "GRADE", "SCORE"));

        List<StudentGrade> graded = gradeCard.stream().filter((StudentGrade studentGrade)->{ return studentGrade.getGrade() != null; }).collect(Collectors.toList());
        List<StudentGrade> unGraded = gradeCard.stream().filter((StudentGrade studentGrade)->{ return studentGrade.getGrade() == null; }).collect(Collectors.toList());

        double total_score = 0;
        if (!graded.isEmpty()) {
            logger.info("Graded Courses : ");
            for (StudentGrade studentGrade : graded) {
            	logger.info(String.format("  %-20s %-20s %-20s %-20s", studentGrade.getCourseCode(),
                        studentGrade.getCourseName(), studentGrade.getGrade(), getScore(studentGrade.getGrade())));
                total_score += getScore(studentGrade.getGrade());
            }
        }
        if (!unGraded.isEmpty()) {
        	logger.info("Grade Awaited : ");
           
            unGraded.forEach(studentGrade -> logger.info(String.format("  %-20s %-20s %-20s %-20s", studentGrade.getCourseCode(),
                    studentGrade.getCourseName(), "NA", "NA")));
        }
        if(!graded.isEmpty())
        {
            
        	logger.info(String.format("  %-20s %-20s %-20s %-20s", "",
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

    /**
     * Make Payment for selected courses.
     * Student is provided with an option to pay the fees and select the mode of payment.
     *
     * @param studentId
     */
    private void make_payment(int studentId)
    {

    	logger.info("Payment Portal");
        double fee =0.0;
        try
        {
            fee=registrationInterface.calculateFee(studentId);
        }
        catch (SQLException e)
        {

        	logger.error(e.getMessage());
        }

        if(fee == 0.0)
        {
        	logger.info("You have not  registered for any courses yet");
        }
        else
        {

            logger.info("Your total fee  = " + fee);
            logger.info("Want to continue Fee Payment(y/n)");
            String ch = sc.next();
            if(ch.equals("y"))
            {
                logger.info("Select Mode of Payment:");

                int index = 1;
                for(ModeOfPayment mode : ModeOfPayment.values())
                {
                    logger.info(index + " " + mode);
                    index = index + 1;
                }

                ModeOfPayment mode = ModeOfPayment.getModeofPayment(sc.nextInt());
                String temp = sc.nextLine();
                if(mode == null)
                	logger.info("Invalid Input");
                else
                {
                    logger.info("Please Enter The Card Number:");
                    String cardNumber = sc.nextLine();

                    logger.info("Please Enter your CVV Number");
                    String cvv = sc.nextLine();

                    try
                    {
                        notificationInterface.sendNotification(NotificationType.PAYMENT, studentId, mode, fee, cardNumber, cvv);
                    }
                    catch (Exception e)
                    {

                    	logger.error(e.getMessage());
                    }
                }

            }

        }

    }
}

