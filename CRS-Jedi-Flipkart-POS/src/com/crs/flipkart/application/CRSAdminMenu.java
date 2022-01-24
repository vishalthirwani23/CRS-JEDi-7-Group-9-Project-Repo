package com.crs.flipkart.application;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
//import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.bean.ReportCard;
import com.crs.flipkart.bean.Student;
//import com.crs.flipkart.constants.constants;
//import com.crs.flipkart.exception.CourseNotDeletedException;
//import com.crs.flipkart.exception.CourseNotFoundException;
//import com.crs.flipkart.exception.FeesPendingException;
//import com.crs.flipkart.exception.GradeNotAddedException;
//import com.crs.flipkart.exception.StudentNotApprovedException;
//import com.crs.flipkart.exception.StudentNotRegisteredException;
import com.crs.flipkart.service.AdminInterface;
import com.crs.flipkart.service.AdminOperation;


//public class CRSAdminMenu {
//
//}


public class CRSAdminMenu {
    private Scanner sc = new Scanner(System.in);
//    AdminOperation ao = new AdminOperation();
    	
	AdminInterface ao = AdminOperation.getInstance();
//	NotificationInterface notificationInterface=NotificationOperation.getInstance();

    public void createAdminMenu(String username) {
        try {

            while(true) {
            	System.out.println("\n\n==~~=~~=~~=~~=~Admin Panel~=~~=~~=~~=~~==");
                System.out.println("Choose an option : ");
                System.out.println("---------------------------------------");
                System.out.println("1 : Edit course details");
                System.out.println("2 : Generate report card");
                System.out.println("3 : Approve student registration");
                System.out.println("4 : Add Professor");
                System.out.println("5 : Remove Professor");
                System.out.println("6 : View Course Wise student list");
                System.out.println("7 : Approve Pending Student Accounts");
                System.out.println("8 : Logout");
                System.out.println("=======================================");

                int menuOption = sc.nextInt();
                sc.nextLine();

                switch(menuOption) {
                    case 1 :
                        editCourseList();
                        break;
                    case 2 :
                        generateReportCard();
                        break;
                    case 3:
                        approveStudentRegistration();
                        break;
                    case 4:
                    	addProfessorDetails();
                        break;
                    case 5:
                    	removeProfessor();
                        break;
                    case 6:
                        viewCourseStudentList();
                        break;
                    case 7:
                    	approvePendingStudentAccounts();
                    case 8:
//                    	System.exit(0);
                        return;
                    default:
                        System.out.println("Invalid input");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void approvePendingStudentAccounts() {
		
    	List<Student> pendingStudents = ao.getPendingStudentAccountsList();
    	
    	System.out.println("List of Students with Pending Account Approval : ");
    	System.out.println();
    	System.out.println("Student ID\t Name\t Department\t Joining Year\t Contact Number");
    	for(Student st: pendingStudents) {
    		System.out.println(st.getStudentID()+"\t"+st.getName()+"\t"+st.getDepartment()+"\t"+st.getJoiningYear()+"\t"+st.getContactNumber());
    	}
    	
    	System.out.println("\n Enter the Student ID for the Student Account you want to approve : ");
		Integer studentID = sc.nextInt();
		sc.nextLine();
		
		ao.approveStudentAccount(studentID);
		
		
	}

	private void viewCourseStudentList() {
    	
    	System.out.println("\n\n==~~=~~=~Course Details~=~~=~~=~~==");
        System.out.println("Choose an option : ");
        System.out.println("---------------------------------------");
        System.out.println("1 : View All Course's Details");
        System.out.println("2 : View for a particular course");
        System.out.println("=======================================");
        int menuOption = sc.nextInt();
        sc.nextLine();

    	String courseID="";
    	Boolean viewAll = true;
        switch(menuOption) {
            case 1 :
                break;
            case 2 :
            	viewAll=false;
            	System.out.println("Enter course ID: ");
            	courseID = sc.nextLine();
                break;
            default:
                System.out.println("Invalid input");
        }

        
//        AdminOperation Ao= new AdminOperation();
        HashMap<String,ArrayList<Integer> > CourseStudentList = ao.viewCourseStudentList (courseID,SemesterID,viewAll);
        System.out.println("+-------------------------------------+");
        CourseStudentList.entrySet().forEach(entry -> {
    	    System.out.println("| Course ID : " + entry.getKey());
    	    System.out.print("| Students Enrolled : \n| " );
    	    for(Integer stID : entry.getValue()) {
    	    	System.out.print(stID.toString()+"\t");
    	    }
    	    System.out.println();
    	    System.out.println("+-------------------------------------+");
//    	    System.out.println();
    	});
        
    }


    private void removeProfessor() {
    	System.out.println("Enter Instructor ID :");
    	Integer professorID = sc.nextInt();
        
    	try {
            ao.removeProfessor(professorID);                            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private void addProfessorDetails() {
    	
    	
        try {

            String username, name, password, department, designation, contact;
            Integer joiningYear;
            
            System.out.println("=======================================");
            System.out.println("Enter professor details");
            System.out.println("---------------------------------------");
            System.out.println("User Name: ");
            username = sc.nextLine();
            System.out.println("Password: ");
            password = sc.nextLine();
            System.out.println("Name: ");
            name = sc.nextLine();
            System.out.println("Department: ");
            department = sc.nextLine();
            System.out.println("Designation: ");
            designation = sc.nextLine();
            System.out.println("Contact Number");
            contact = sc.nextLine();
            System.out.println("Joining Year");
            joiningYear = sc.nextInt();
            System.out.println("=======================================");

            Professor Prof = new Professor();
            Prof.setUserID(username);
            Prof.setName(name);
            Prof.setPassword(password);
            Prof.setDepartment(department);
            Prof.setDesignation(designation);
            Prof.setContactNumber(contact);
            Prof.setJoiningYear(joiningYear);
            
            ao.addProfessor(Prof);
            //done : create professor obj, and add to db

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void approveStudentRegistration() {
        int studentID;
        System.out.println("Enter student ID: ");
        studentID = sc.nextInt();
        
//        AdminOperation Ao= new AdminOperation();

			ao.approveStudentRegistration(studentID,SemesterID);
        
        // to do : approve student reg logic
    }

    private void generateReportCard() {
        int studentID;
        System.out.println("Enter student ID: ");
        studentID = sc.nextInt();
        ReportCard R = new ReportCard();
		R = ao.generateReportCard(studentID);
		
		if(R.getSpi() > 0)
        System.out.println("Student ID : "+studentID+"    SPI : "+ R.getSpi());
        // to do : get student courses and grade, and generate report card
    }

    private void editCourseList() {
        
        
            while(true) {
            	System.out.println("=======================================");
                System.out.println("Options : ");
                System.out.println("---------------------------------------");
                System.out.println("1 : Add course");
                System.out.println("2 : Remove course");
                System.out.println("3 : Exit");
                System.out.println("=======================================");

                int menuOption = sc.nextInt();
                sc.nextLine();

                switch(menuOption) {
                    case 1 :
                        addCourse();
                        break;
                    case 2 :
                        removeCourse();
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Invalid input");
                }
            }

        
    }

    private void removeCourse() {
        String courseID;
        System.out.println("=======================================");
        System.out.println("Enter course ID: ");
        courseID = sc.nextLine();
        
        
			ao.removeCourse(courseID);
		
        // to do : remove course from db
    }

    private void addCourse() {

        
            String course_name, courseID;
            int offeredSemester;
            
            System.out.println("=======================================");
            System.out.println("Enter course details");
            System.out.print("Course Name: ");
            course_name = sc.nextLine();
            System.out.print("Course ID: ");
            courseID = sc.nextLine();
            System.out.print("Semester: ");
            offeredSemester = sc.nextInt();
            
            ao.addCourse(course_name, courseID, offeredSemester);

            // to do : create course object, update course in db
        


    }
}