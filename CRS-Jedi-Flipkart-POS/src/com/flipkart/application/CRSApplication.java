package com.flipkart.application;

import java.util.Scanner;

import com.flipkart.business.*;
import com.flipkart.constant.Gender;
import com.flipkart.constant.NotificationType;
import com.flipkart.constant.Role;


/**
 * This class is used as the main entry point of the application
 * In main menu to login, register are displayed
 */
public class CRSApplication {

    static boolean loggedin = false;
    StudentInterface studentInterface = StudentOperation.getInstance();
    UserInterface userInterface = UserOperation.getInstance();
    NotificationInterface notificationInterface = NotificationOperation.getInstance();
    AdminInterface adminInterface = AdminOperation.getInstance();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CRSApplication crsApplication = new CRSApplication();
        int userInput;
        //create the main menu
        createMainMenu();
        userInput = sc.nextInt();
       

            //until user do not exit the application
            while (userInput != 5) {
                switch (userInput) {
                    case 1:
                        //login
                        crsApplication.loginUser();
                        break;
                    case 2:
                        //student registration
                        crsApplication.registerStudent();
                        break;
                    case 3:
                        crsApplication.updatePassword();
                        break;
                    case 4:
                        //student registration
                        crsApplication.registerAdmin();
                        break;
                    default:
                    	System.out.println("Invalid Input");
                }
                createMainMenu();
                userInput = sc.nextInt();
            }
        
    }

    /**
     * Method to Create Main Menu
     */
    public static void createMainMenu() {
       
        
    	System.out.println("\n\n==~~=~~=~~=~~=~~=~CRS~=~~=~~=~~=~~=~~==");
        System.out.println("Choose an option: ");
        System.out.println("---------------------------------------");
        System.out.println("1 : Login");
        System.out.println("2 : Student Registration");
        System.out.println("3 : Change password");
        System.out.println("4 : Admin Account Creation");
        System.out.println("5 : Exit");
        System.out.println("=======================================");
        
    }


    /**
     * Method for Login functionality
     */
    public void loginUser() {

        Scanner sc = new Scanner(System.in);

        String userId, password;
       
           
            System.out.println("Login Portal");
            System.out.println("=======================================");
            System.out.println("Email:");
            userId = sc.next();
            System.out.println("Password:");
            password = sc.next();
            loggedin = userInterface.verifyCredentials(userId, password);
            //2 cases
            //true->role->student->approved
            if (loggedin) {
                


                System.out.println("Welcome "+userId);
                String role = userInterface.getRole(userId);
                Role userRole = Role.stringToName(role);
                switch (userRole) {
                    case ADMIN:
                    	System.out.println( " Login Successful as Admin");
                        AdminCRSMenu adminMenu = new AdminCRSMenu();
                        adminMenu.createMenu();
                        break;
                    case PROFESSOR:
                    	System.out.println(" Login Successful for Professor");
                        ProfessorCRSMenu professorMenu = new ProfessorCRSMenu();
                        professorMenu.createMenu(userId);

                        break;
                    case STUDENT:

                        int studentId = studentInterface.getStudentId(userId);
                        boolean isApproved = studentInterface.isApproved(studentId);
                        if (isApproved) {
                        	System.out.println(" Login Successful for Student");
                            StudentCRSMenu studentMenu = new StudentCRSMenu();
                            studentMenu.create_menu(studentId);

                        } else {
                        	System.out.println("Failed to login, you have not been approved by the administration!");
                            loggedin = false;
                        }
                        break;
                }


            }
            else {
            	System.out.println("Invalid Credentials!");
            }

        
    }

    /**
     * Method to Register Admin
     */
    public void registerAdmin() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter root password");
        String rt_pwd = sc.nextLine();

        if(!rt_pwd.equals("1234567"))
        {
        	System.out.println("Incorrect Password! Access Denied");
            return;
        }
        String userId, name, password, address, country;
        Gender gender;
        int genderV;
      
            //input all the student details
        	System.out.println("Administrative Account Creation Portal");
            System.out.println("Name:");
            name = sc.nextLine();
            System.out.println("Email:");
            userId = sc.next();
            System.out.println("Password:");
            password = sc.next();
            int admin= adminInterface.register(name, userId, password);
       
            System.out.println("Administrative Account Successfully Created!");
    }
    /**
     * Method to help Student register themselves, pending admin approval
     */
    public void registerStudent() {
        Scanner sc = new Scanner(System.in);

        String userId, name, password, address, country, branchName;
        Gender gender;
        int genderV, batch;
     
            //input all the student details
        	System.out.println("Student Registration Portal");
            System.out.println("Name:");
            name = sc.nextLine();
            System.out.println("Email:");
            userId = sc.next();
            System.out.println("Password:");
            password = sc.next();
            System.out.println("Gender: \t 1: Male \t 2.Female\t 3.Other");
            genderV = sc.nextInt();
            sc.nextLine();
            System.out.println("Branch:");
            branchName = sc.nextLine();
            System.out.println("Batch:");
            batch = sc.nextInt();
            sc.nextLine();
            System.out.println("Address:");
            address = sc.nextLine();
            System.out.println("Country");
            country = sc.next();
            gender = Gender.getName(genderV);
            int newStudentId = studentInterface.register(name, userId, password, gender, batch, branchName, address, country);
            notificationInterface.sendNotification(NotificationType.REGISTRATION, newStudentId, null, 0, null, null);

      
            System.out.println("Student Successfully Registered!");
    }

    /**
     * Method to update password of User
     */
    public void updatePassword() {
        Scanner sc = new Scanner(System.in);
        String userId, newPassword, password;
   
        System.out.println("Update Password Portal");
        System.out.println("Email:");
        userId = sc.next();
        System.out.println("old Password:");
        password = sc.next();
        loggedin = userInterface.verifyCredentials(userId, password);
        if (loggedin) {

            System.out.println("New Password:");
            newPassword = sc.next();
            boolean isUpdated = userInterface.updatePassword(userId, newPassword);
            if (isUpdated)
            	System.out.println("Password updated successfully!");
            else
            	System.out.println("Something went wrong, please try again!");
        }
        else{
        	System.out.println("Incorrect Password");
        }
       
    }
}

