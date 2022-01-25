package com.flipkart.application;

import java.util.List;
import java.util.Scanner;


import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

//import com.flipkart.constant.Color;
//import com.flipkart.constant.Gender;
import com.flipkart.constant.NotificationType;
import com.flipkart.constant.Role; 
import com.flipkart.business.AdminInterface;
import com.flipkart.business.AdminOperation;
import com.flipkart.business.NotificationInterface;
import com.flipkart.business.NotificationOperation;
 

/**
 * Class that display Admin Client Menu
 */
public class CRSAdminMenu {


    AdminInterface adminOperation = AdminOperation.getInstance();
    Scanner scanner = new Scanner(System.in);
    NotificationInterface notificationInterface = NotificationOperation.getInstance();

    /**
     * Method to Create Admin Menu
     */
    public void createMenu() {

        while (CRSApplication.loggedin) {
		
	   System.out.println("Administrative Control Menu");
           System.out.println("1. View course Catalogue");
           System.out.println("2. Add New Course to Catalogue");
           System.out.println("3. Delete Course from Catalogue");
           System.out.println("4. Approve Student Registration");
           System.out.println("5. Add New Professor");
           System.out.println("6. Assign Courses To Professor");
           System.out.println("7. Logout");

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
        System.out.println("List of Professors Available");
        System.out.println("ProfessorId Name Designation");
        for (Professor professor : professorList) {
            System.out.println(professor.getUserId()+professor.getName()+professor.getDesignation());
        }
        

        List<Course> courseList = adminOperation.viewCourses(); // add viewCourses method in admin
        System.out.println("List of Courses Available");
        System.out.println("CourseCode CourseName");
        for (Course course : courseList) {
            System.out.println(course.getCourseCode()+course.getCourseName());
        }
     
        System.out.println("Enter Course Code of the course to be assigned:");
        String courseCode = scanner.next();

        System.out.println("Enter Professor's User Id:");
        String userId = scanner.next();
	   
        adminOperation.assignCourse(courseCode, userId); // add assignCourse method in admin
	    
    }

    /**
     * Method to add Professor to DB
     */
    private void addProfessor() {

        Professor professor = new Professor();
        System.out.println("Add Professor Portal");
        System.out.println("Enter Professor Name:");
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

        adminOperation.addProfessor(professor);
 
    }

    /**
     * Method to approve a Student using Student's ID
     */
    private void approveStudent() {

        List<Student> studentList = viewPendingAdmissions();
        if (studentList.size() == 0) {
            return;
        }
        System.out.println(("Approve Student Portal");
        System.out.println("Enter Student's ID:");
        int studentUserIdApproval = scanner.nextInt();

        adminOperation.approveStudent(studentUserIdApproval, studentList);
            //send notification from system
        notificationInterface.sendNotification(NotificationType.REGISTRATION_APPROVAL, studentUserIdApproval, null, 0, null, null);

    }

    private void approveStudent() {

        List<Student> studentList = viewPendingAdmissions();
        if (studentList.size() == 0) {
            return;
        }
        System.out.println("Approve Student Portal");
        System.out.println("Enter Student's ID:");
        int studentUserIdApproval = scanner.nextInt();

        try {
            adminOperation.approveStudent(studentUserIdApproval, studentList);
            //send notification from system
            notificationInterface.sendNotification(NotificationType.REGISTRATION_APPROVAL, studentUserIdApproval, null, 0, null, null);

        } catch (Exception e) {
            
        }
    }
    private void deleteCourse() {
        System.out.println("Delete Course Portal");
        List<Course> courseList = viewCoursesInCatalogue();
        System.out.println("Enter Course Code:");
        String courseCode = scanner.next();
        try {
            adminOperation.deleteCourse(courseCode, courseList);
        } catch (Exception e) {
           
        }
	
    }


    private void addCourseToCatalogue() {
        System.out.println("Add Course to Catalogue Portal");
        List<Course> courseList = viewCoursesInCatalogue();
        scanner.nextLine();
        System.out.println("Enter Course Code:");
        String courseCode = scanner.nextLine();

        System.out.println("Enter Course Name:");
        String courseName = scanner.next();
        Course course = new Course(courseCode, courseName, null, 10);
        try {
        	adminOperation.addCourse(course, courseList);
        	 
        }catch(Exception e)
        {
        	
        }
	
    }


    private List<Course> viewCoursesInCatalogue() {
        List<Course> courseList = adminOperation.viewCourses();
        if (courseList.size() == 0) {
            System.out.println("No course in the catalogue!");
            return courseList;
        }
        System.out.println("Course Catalogue");
        System.out.println("COURSE CODE COURSE NAME INSTRUCTOR");
        for (Course course : courseList) {
            String instructorId = "No Professor";
            if (course.getInstructorId() != null && !course.getInstructorId().isEmpty())
                instructorId = course.getInstructorId();
            System.out.println(course.getCourseCode()+course.getCourseName()+instructorId);
        }
        return courseList;
    }
}
