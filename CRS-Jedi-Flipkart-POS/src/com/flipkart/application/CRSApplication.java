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
	private static Logger logger = Logger.getLogger(CRSApplication.class.getName());

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
                    	logger.info("Invalid Input");
                    	
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
       
        
    	logger.info("\n\n==~~=~~=~~=~~=~~=~CRS~=~~=~~=~~=~~=~~==");
        logger.info("Choose an option: ");
        logger.info("---------------------------------------");
        logger.info("1 : Login");
        logger.info("2 : Student Registration");
        logger.info("3 : Change password");
        logger.info("4 : Admin Account Creation");
        logger.info("5 : Exit");
        logger.info("=======================================");
        
    }

    /**
     * Method for Login functionality
     */
    public void loginUser() {

        Scanner sc = new Scanner(System.in);

        String userId, password;
       
        try {
            logger.info("Login Portal");
            logger.info("=======================================");
            logger.info("Email:");
            userId = sc.next();
            logger.info("Password:");
            password = sc.next();
            loggedin = userInterface.verifyCredentials(userId, password);
            //2 cases
            //true->role->student->approved
            if (loggedin) {
                
            	DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

                LocalDateTime myDateObj = LocalDateTime.now();

                String formattedDate = myDateObj.format(myFormatObj);
                logger.info(formattedDate);

                logger.info("Welcome "+userId);
                String role = userInterface.getRole(userId);
                Role userRole = Role.stringToName(role);
                switch (userRole) {
                    case ADMIN:
                    	logger.info( " Login Successful as Admin");
                    	CRSAdminMenu adminMenu = new CRSAdminMenu();
                        adminMenu.createMenu();
                        break;
                    case PROFESSOR:
                    	logger.info(" Login Successful for Professor");
                        ProfessorCRSMenu professorMenu = new ProfessorCRSMenu();
                        professorMenu.createMenu(userId);

                        break;
                    case STUDENT:

                        int studentId = studentInterface.getStudentId(userId);
                        boolean isApproved = studentInterface.isApproved(studentId);
                        if (isApproved) {
                        	logger.info(" Login Successful for Student");
                            StudentCRSMenu studentMenu = new StudentCRSMenu();
                            studentMenu.create_menu(studentId);

                        } else {
                        	logger.info("Failed to login, you have not been approved by the administration!");
                            loggedin = false;
                        }
                        break;
                }


            }
            else {
            	logger.info("Invalid Credentials!");
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
        logger.info("Enter root password");
        String rt_pwd = sc.nextLine();

        if(!rt_pwd.equals("1234567"))
        {
        	logger.info("Incorrect Password! Access Denied");
            return;
        }
        String userId, name, password;
   
        try {
            //input all the student details
        	logger.info("Administrative Account Creation Portal");
            logger.info("Name:");
            name = sc.nextLine();
            logger.info("Email:");
            userId = sc.next();
            logger.info("Password:");
            password = sc.next();
            int admin= adminInterface.register(name, userId, password);
        } catch (AdminAccountNotCreatedException ex) {
        	logger.error("Error occured! " + ex.getMessage());
            return;
        }
       
            logger.info("Administrative Account Successfully Created!");
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
            logger.info("Student Registration Portal");
            logger.info("Name:");
            name = sc.nextLine();
            logger.info("Email:");
            userId = sc.next();
            logger.info("Password:");
            password = sc.next();
            logger.info("Batch:");
            batch = sc.nextInt();
            logger.info("Branch:");
            branch = sc.next();

            int newStudentId = studentInterface.register(name, userId, password, batch, branch);
            notificationInterface.sendNotification(NotificationType.REGISTRATION, newStudentId, null, 0, null, null);

        } catch (Exception ex) {
        	logger.error(ex.getMessage());
            return;
        }
        logger.info("Student Successfully Registered!");
    }

    /**
     * Method to update password of User
     */
    public void updatePassword() {
        Scanner sc = new Scanner(System.in);
        String userId, newPassword, password;
        try {
	        logger.info("Update Password Portal");
	        logger.info("Email:");
	        userId = sc.next();
	        logger.info("old Password:");
	        password = sc.next();
	        loggedin = userInterface.verifyCredentials(userId, password);
	        if (loggedin) {
	
	            logger.info("New Password:");
	            newPassword = sc.next();
	            boolean isUpdated = userInterface.updatePassword(userId, newPassword);
	            if (isUpdated)
	            	logger.info("Password updated successfully!");
	            else
	            	logger.info("Something went wrong, please try again!");
	        }
	        else{
	        	logger.info("Incorrect Password");
	        }
        } catch (Exception ex) {
            logger.error("Error Occurred " + ex.getMessage());
            return;
        }
       
    }
}

