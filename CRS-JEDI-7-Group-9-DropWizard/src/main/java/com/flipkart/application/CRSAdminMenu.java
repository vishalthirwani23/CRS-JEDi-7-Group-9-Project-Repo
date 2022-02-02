package com.flipkart.application;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.*;
//import com.flipkart.constant.Color;
//import com.flipkart.constant.Gender;
import com.flipkart.constant.NotificationType;
import com.flipkart.constant.Role;
import com.flipkart.exceptions.FeesPendingException;
import com.flipkart.exceptions.GradeNotAddedException;
import com.flipkart.exceptions.StudentNotApprovedException;
import com.flipkart.exceptions.StudentNotRegisteredException;
import com.flipkart.constant.Role;
import com.flipkart.exceptions.CourseFoundException;
import com.flipkart.exceptions.CourseNotDeletedException;
import com.flipkart.exceptions.CourseNotFoundException;
import com.flipkart.exceptions.ProfessorNotAddedException;
import com.flipkart.exceptions.StudentNotFoundForApprovalException;
import com.flipkart.exceptions.UserIdAlreadyInUseException;
import com.flipkart.exceptions.UserNotFoundException;
import com.flipkart.business.AdminInterface;
import com.flipkart.business.AdminOperation;
import com.flipkart.business.NotificationInterface;
import com.flipkart.business.NotificationOperation;

public class CRSAdminMenu {

	private static Logger logger = Logger.getLogger(CRSApplication.class);
    AdminInterface adminOperation = new AdminOperation();
    Scanner scanner = new Scanner(System.in);
    NotificationInterface notificationInterface = new NotificationOperation();

    /**
     * Method to Create Admin Menu
     */
    public void createMenu(){

        while (CRSApplication.loggedin) {
       System.out.println("\n_________________________________________\n");

	   System.out.println("\n\nADMINISTRATIVE CONTROL MENU\n");
           System.out.println("1. View course Catalogue");
           System.out.println("2. Add New Course to Catalogue");
           System.out.println("3. Delete Course from Catalogue");
           System.out.println("4. Approve Student Registration");
           System.out.println("5. Add New Professor");
           System.out.println("6. Assign Courses To Professor");
           System.out.println("7. Generate Report Card");
           System.out.println("8. Logout\n\n");
           System.out.println("\n_________________________________________\n");

           System.out.println("Enter your choice");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewCoursesInCatalogue();
                    break;

                case 2:
                    addCourseToCatalogue();
                    break;

                case 3:
                    deleteCourse();
                    break;

                case 4:
                    approveStudent();
                    break;

         
                case 5:
                    addProfessor();
                    break;

                case 6:
                    assignCourseToProfessor();
                    break;

                case 7:
                	generateReportCard();

                case 8:
                    CRSApplication.loggedin = false;
                    return;

                default:
                    System.out.println("***** Wrong Choice *****");
            }
        }
    }

    /**
     * Method to assign Course to a Professor
     */
    private void assignCourseToProfessor() {
        List<Professor> professorList = adminOperation.viewProfessors(); // add viewProfessors method in admin
        System.out.println("\n_________________________________________\n");

        System.out.println("\n\n List of Professors Available \n\n");

        System.out.println(String.format("%-20s %-20s %-20s","ProfessorId",	"Name",	"Designation"));

        for (Professor professor : professorList) {
            System.out.println(String.format("%-20s %-20s %-20s",professor.getUserId(), professor.getName(), professor.getDesignation()));
        }
        
        System.out.println("\n_________________________________________\n");

        List<Course> courseList = adminOperation.viewCourses(1); // add viewCourses method in admin
        System.out.println("\n_________________________________________\n");

        System.out.println("\n\n List of Courses Available \n\n");

        System.out.println(String.format("%-20s %-20s","Course Code", "CourseName"));
        for (Course course : courseList) {
            System.out.println(String.format("%-20s %-20s", course.getCourseCode(), course.getCourseName()));

        }
        System.out.println("\n_________________________________________\n");

        System.out.println("Enter Course Code of the course to be assigned:");
        int courseCode = scanner.nextInt();

        System.out.println("Enter Professor's User Id:");
        String userId = scanner.next();
	   
        try {

            adminOperation.assignCourse(courseCode, userId);

        } catch (CourseNotFoundException | UserNotFoundException e) {

            logger.error(e.getMessage());
        } // add assignCourse method in admin

    }

    /**
     * Method to add Professor to DB
     */
    private void addProfessor() {

        Professor professor = new Professor();
        System.out.println("\n_________________________________________\n");

        System.out.println("\n\n Add Professor Portal \n\n");
        System.out.println("\n_________________________________________\n");

        System.out.println("Enter Professor Name: ");
        String professorName = scanner.next();
        professor.setName(professorName);

        System.out.println("Enter Department:");
        String department = scanner.next();
        professor.setDepartment(department);

        System.out.println("Enter Designation:");
        String designation = scanner.next();
        professor.setDesignation(designation);

        System.out.println("Enter Email:");
        String userId = scanner.next();
        professor.setUserId(userId);

        System.out.println("Enter Password:");
        String password = scanner.next();
        professor.setPassword(password);

 

        professor.setRole(Role.stringToName("Professor"));

        try {
            adminOperation.addProfessor(professor);
        } catch (ProfessorNotAddedException | UserIdAlreadyInUseException e) {
            logger.error(e.getMessage());
        }

    }

    /**
     * Method to approve a Student using Student's ID
     */
    private void approveStudent() {

        List<Student> studentList = adminOperation.viewPendingAdmissions();
        if (studentList.size() == 0) {
            return;
        }
        
        System.out.println("\n_________________________________________\n");

        System.out.println("\n\n PENDING STUDENTS \n\n");
        System.out.println(String.format("%-20s %-20s %-20s", "Student ID", "Name", "User ID"));
        
        for (Student stud : studentList) {
        	System.out.println(String.format("%-20s %-20s %-20s", stud.getStudentId(), stud.getName(), stud.getUserId()));
        }
        System.out.println("\n_________________________________________\n");

        System.out.println("\n Approve Student Portal \n");

        System.out.println("Enter Student's ID:");
        int studentUserIdApproval = scanner.nextInt();

        try {
            adminOperation.approveStudent(studentUserIdApproval, studentList);
            //send notification from system
            notificationInterface.sendNotification(NotificationType.REGISTRATION_APPROVAL, studentUserIdApproval, null, 0, null, null);

        } catch (StudentNotFoundForApprovalException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * Method to delete Course from catalogue
     *
     * @throws CourseNotFoundException
     */
    private void deleteCourse() {
        System.out.println("\n_________________________________________\n");

        System.out.println("\n\n Delete Course Portal \n\n");
        List<Course> courseList = viewCoursesInCatalogue();
        System.out.println("\n_________________________________________\n");

        System.out.println("Enter Course Code:");
        int courseCode = scanner.nextInt();
        try {
            adminOperation.deleteCourse(courseCode, courseList);
        } catch (CourseNotFoundException | CourseNotDeletedException e) {
        	logger.error(e.getMessage());
        }
	
    }

    /**
     * Method to add Course to catalogue
     */
    private void addCourseToCatalogue() {
        System.out.println("\n_________________________________________\n");

        System.out.println("\n\n Add Course to Catalogue Portal \n\n");
        System.out.println("\n_________________________________________\n");

        List<Course> courseList = viewCoursesInCatalogue();
        scanner.nextLine();
        System.out.println("Enter Course Code:");
        int courseCode = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter Course Name:");
        String courseName = scanner.nextLine();
        Course course = new Course(courseCode, courseName, null, 10);
        try {
        	adminOperation.addCourse(course, courseList);
        	 
        }catch (CourseFoundException e) {
        	logger.error(e.getMessage());
        }
	
    }

    /**
     * Method to display courses in catalogue
     *
     * @return List of courses in catalogue
     */
    private List<Course> viewCoursesInCatalogue() {
        List<Course> courseList = adminOperation.viewCourses(1);
        if (courseList.size() == 0) {
            System.out.println("No course in the catalogue!");
            return courseList;
        }
        System.out.println("\n_________________________________________\n");

        System.out.println("\n\n Course Catalogue \n\n");
        System.out.println(String.format("%-20s %-20s %-20s", "COURSE CODE", "COURSE NAME", "INSTRUCTOR"));
        for (Course course : courseList) {
            String instructorId = "No Professor";
            if (course.getInstructorId() != null && !course.getInstructorId().isEmpty())
                instructorId = course.getInstructorId();
            System.out.println(String.format("%-20s %-20s %-20s",course.getCourseCode(), course.getCourseName(), instructorId));
        }
        return courseList;
    }


    private void generateReportCard() {
    	
        System.out.println("\n_________________________________________\n");

		System.out.println("\n\nGENERATE REPORT PORTAL\n\n");
		System.out.println("Enter Student Id :");
		try {
			adminOperation.generateReportCard(scanner.nextInt());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
        	logger.error(e.getMessage());

		} catch (StudentNotRegisteredException e) {
			// TODO Auto-generated catch block
        	logger.error(e.getMessage());
		} catch (GradeNotAddedException e) {
			// TODO Auto-generated catch block
        	logger.error(e.getMessage());
		} catch (StudentNotApprovedException e) {
			// TODO Auto-generated catch block
        	logger.error(e.getMessage());
		} catch (FeesPendingException e) {
			// TODO Auto-generated catch block
        	logger.error(e.getMessage());
		}
		System.out.println("\nReport Card Generated/n");
        System.out.println("\n_________________________________________\n");

	}
}


