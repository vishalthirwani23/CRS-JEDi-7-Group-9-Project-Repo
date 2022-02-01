package com.flipkart.application;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.constant.NotificationType;
import com.flipkart.constant.Role;
import com.flipkart.exceptions.AdminAccountNotCreatedException;
import com.flipkart.exceptions.UserNotFoundException;
import com.flipkart.business.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CRSApplication {

    static boolean loggedin = false;
	private static Logger logger = Logger.getLogger(CRSApplication.class);

    StudentInterface studentInterface = StudentOperation.getInstance();
    UserInterface userInterface = UserOperation.getInstance();
    NotificationInterface notificationInterface = NotificationOperation.getInstance();
    AdminInterface adminInterface = AdminOperation.getInstance();

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        CRSApplication crsApplication = new CRSApplication();
        int userInput;
        //create the main menu
        createMainMenu();
        userInput = sc.nextInt();
       
        try {
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
        } catch (Exception ex) {
            logger.error("Error occurred " + ex.getMessage());;
        } finally {
            sc.close();
        }

    }

    /**
     * Method to Create Main Menu
     */
    public static void createMainMenu() {
       
        
    	System.out.println("\n==~~=~~=~~=~~=~~=~CRS~=~~=~~=~~=~~=~~==");
        System.out.println("\n_________________________________________\n");
        System.out.println("1 : Login");
        System.out.println("2 : Student Registration");
        System.out.println("3 : Change password");
        System.out.println("4 : Admin Account Creation");
        System.out.println("5 : Exit");
        System.out.println("\n_________________________________________\n");
        
        System.out.println("Choose an option: ");
    }

    /**
     * Method for Login functionality
     */
    public void loginUser() {

        Scanner sc = new Scanner(System.in);

        String userId, password;
       
        try {
            System.out.println("\n_________________________________________\n");
            System.out.println("LOGIN PORTAL");

            System.out.println("=======================================");
            System.out.println("Email:");
            userId = sc.next();
            System.out.println("Password:");
            password = sc.next();
            loggedin = userInterface.verifyCredentials(userId, password);
            //2 cases
            //true->role->student->approved
            if (loggedin) {
                
            	DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

                LocalDateTime myDateObj = LocalDateTime.now();

                String formattedDate = myDateObj.format(myFormatObj);
                System.out.println(formattedDate);
                System.out.println("\n_________________________________________\n");

                System.out.println("Welcome "+userId);
                String role = userInterface.getRole(userId);
                Role userRole = Role.stringToName(role);
                switch (userRole) {
                    case ADMIN:
                    	System.out.println( "Login Successful as Admin");
                    	CRSAdminMenu adminMenu = new CRSAdminMenu();
                        adminMenu.createMenu();
                        break;
                    case PROFESSOR:
                    	System.out.println("Login Successful for Professor");
                        ProfessorCRSMenu professorMenu = new ProfessorCRSMenu();
                        professorMenu.createMenu(userId);

                        break;
                    case STUDENT:

                        int studentId = studentInterface.getStudentId(userId);
                        boolean isApproved = studentInterface.isApproved(studentId);
                        if (isApproved) {
                        	System.out.println("Login Successful for Student");
                            StudentCRSMenu studentMenu = new StudentCRSMenu();
                            studentMenu.create_menu(studentId);

                        } else {
                        	System.out.println("Failed to login, you have not been approved by the administration!");
                            loggedin = false;
                        }
                        System.out.println("\n_________________________________________\n");

                        break;
                }


            }
            else {
            	System.out.println("Invalid Credentials!");
            }
            
         }	catch (UserNotFoundException ex) {
               logger.error(ex.getMessage());
         }
        
    }

    /**
     * Method to Register Admin
     */
    public void registerAdmin()  {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter root password");
        String rt_pwd = sc.nextLine();

        if(!rt_pwd.equals("1234567"))
        {
        	System.out.println("Incorrect Password! Access Denied");
            return;
        }
        String userId, name, password;
   
        try {
            //input all the student details
            System.out.println("\n_________________________________________\n");

        	System.out.println("Administrative Account Creation Portal");
            System.out.println("Name:");
            name = sc.nextLine();
            System.out.println("Email:");
            userId = sc.next();
            System.out.println("Password:");
            password = sc.next();
            int admin= adminInterface.register(name, userId, password);
        } catch (AdminAccountNotCreatedException ex) {
        	logger.error("Error occured! " + ex.getMessage());
            return;
        }
        	System.out.println("\n_________________________________________\n");

            System.out.println("Administrative Account Successfully Created!");
            System.out.println("\n_________________________________________\n");

    }
   
    /**
     * Method to help Student register themselves, pending admin approval
     */
    public void registerStudent() {
        Scanner sc = new Scanner(System.in);

        String userId, name, password, branch;
        int batch;
      
        try {
            //input all the student details
            System.out.println("\n_________________________________________\n");

            System.out.println("Student Registration Portal");
            System.out.println("Name:");
            name = sc.nextLine();
            System.out.println("Email:");
            userId = sc.next();
            System.out.println("Password:");
            password = sc.next();
            System.out.println("Batch:");
            batch = sc.nextInt();
            System.out.println("Branch:");
            branch = sc.next();

            int newStudentId = studentInterface.register(name, userId, password, batch, branch);
            notificationInterface.sendNotification(NotificationType.REGISTRATION, newStudentId, null, 0, null, null);

        } catch (Exception ex) {
        	logger.error(ex.getMessage());
            return;
        }
        System.out.println("\n_________________________________________\n");

        System.out.println("Student Successfully Registered!");
        System.out.println("\n_________________________________________\n");

    }

    /**
     * Method to update password of User
     */
    public void updatePassword() {
        Scanner sc = new Scanner(System.in);
        String userId, newPassword, password;
        try {
            System.out.println("\n_________________________________________\n");

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
	           System.out.println("\n_________________________________________\n");

        } catch (Exception ex) {
            logger.error("Error Occurred " + ex.getMessage());
            return;
        }
       
    }
}

