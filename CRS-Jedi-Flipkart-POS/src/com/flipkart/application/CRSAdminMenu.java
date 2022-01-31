package com.flipkart.application;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

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

	private static Logger logger = Logger.getLogger(CRSAdminMenu.class.getName());
    AdminInterface adminOperation = AdminOperation.getInstance();
    Scanner scanner = new Scanner(System.in);
    NotificationInterface notificationInterface = NotificationOperation.getInstance();

    /**
     * Method to Create Admin Menu
     */
    public void createMenu(){

        while (CRSApplication.loggedin) {
		
	   logger.info("\n\nAdministrative Control Menu\n");
           logger.info("1. View course Catalogue");
           logger.info("2. Add New Course to Catalogue");
           logger.info("3. Delete Course from Catalogue");
           logger.info("4. Approve Student Registration");
           logger.info("5. Add New Professor");
           logger.info("6. Assign Courses To Professor");
           logger.info("7. Generate Report Card");
           logger.info("8. Logout\n\n");

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
                    logger.info("***** Wrong Choice *****");
            }
        }
    }

    /**
     * Method to assign Course to a Professor
     */
    private void assignCourseToProfessor() {
        List<Professor> professorList = adminOperation.viewProfessors(); // add viewProfessors method in admin

        logger.info("\n\n List of Professors Available \n\n");
        logger.info("ProfessorId	Name	Designation");

        for (Professor professor : professorList) {
            logger.info(professor.getUserId() + "	" + professor.getName() + "	" + professor.getDesignation());
        }
        

        List<Course> courseList = adminOperation.viewCourses(1); // add viewCourses method in admin

        logger.info("\n\n List of Courses Available \n\n");
        logger.info("Course Code	CourseName");
        for (Course course : courseList) {
            logger.info(course.getCourseCode() + "	" + course.getCourseName());

        }
     
        logger.info("Enter Course Code of the course to be assigned:");
        int courseCode = scanner.nextInt();

        logger.info("Enter Professor's User Id:");
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
        logger.info("\n\n Add Professor Portal \n\n");
        logger.info("Enter Professor Name: ");
        String professorName = scanner.next();
        professor.setName(professorName);

        logger.info("Enter Department:");
        String department = scanner.next();
        professor.setDepartment(department);

        logger.info("Enter Designation:");
        String designation = scanner.next();
        professor.setDesignation(designation);

        logger.info("Enter Email:");
        String userId = scanner.next();
        professor.setUserId(userId);

        logger.info("Enter Password:");
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
        

        logger.info("\n\n PENDING STUDENTS \n\n");
        logger.info("Student ID	 Name	 User ID");
        
        for (Student stud : studentList) {
        	logger.info(stud.getStudentId() + "	" + stud.getName() + "	" + stud.getUserId());
        }
        
        logger.info("\n\n Approve Student Portal \n\n");

        logger.info("Enter Student's ID:");
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
        logger.info("\n\n Delete Course Portal \n\n");
        List<Course> courseList = viewCoursesInCatalogue();
        logger.info("Enter Course Code:");
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
        logger.info("\n\n Add Course to Catalogue Portal \n\n");
        List<Course> courseList = viewCoursesInCatalogue();
        scanner.nextLine();
        logger.info("Enter Course Code:");
        int courseCode = scanner.nextInt();
        scanner.nextLine();

        logger.info("Enter Course Name:");
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
            logger.info("No course in the catalogue!");
            return courseList;
        }

        logger.info("\n\n Course Catalogue \n\n");
        logger.info("COURSE CODE  COURSE NAME    INSTRUCTOR");
        for (Course course : courseList) {
            String instructorId = "No Professor";
            if (course.getInstructorId() != null && !course.getInstructorId().isEmpty())
                instructorId = course.getInstructorId();
            logger.info(course.getCourseCode() + "	" +course.getCourseName() + "	" +instructorId);
        }
        return courseList;
    }


    private void generateReportCard() {
		logger.info("\n\nGENERATE REPORT PORTAL\n\n");
		logger.info("Enter Student Id :");
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
		logger.info("\nReport Card Generated/n");
	}
}


