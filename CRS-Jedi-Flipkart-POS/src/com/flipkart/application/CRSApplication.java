/*
package com.flipkart.application;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
//import com.flipkart.service.StudentOperation;
//import com.flipkart.service.UserOperation;

//public class CRSApplication {
//
//}



public class CRSApplication {
    private Scanner sc = new Scanner(System.in);
    
//    StudentOperation so = StudentOperation.getInstance();
    

    public static void main(String[] args) {
    	    	
        CRSApplication newUser = new CRSApplication();
        newUser.createMenu();
    }

    private void createMenu() {
//        try {
            while(true) {
            	System.out.println("\n\n==~~=~~=~~=~~=~~=~CRS~=~~=~~=~~=~~=~~==");
                System.out.println("Choose an option: ");
                System.out.println("---------------------------------------");
                System.out.println("1 : Register a new user");
                System.out.println("2 : Login");
                System.out.println("3 : Exit menu");
                System.out.println("=======================================");

                int menuOption = sc.nextInt();
                sc.nextLine();

                switch(menuOption) {
                    case 1:
                        registerUser();
                        break;

                    case 2:
                        loginUser();
                        break;

                    case 3:
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid input");
                }
            }
//        } 
//        catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    private void loginUser() {
        String username, password, role;

        try {
        	System.out.println("=======================================");
            System.out.print("Username: ");
            username = sc.nextLine();
            System.out.print("Password: ");
            password = sc.nextLine();
            System.out.print("Enter Role (student/professor/admin): ");
            role = sc.nextLine();
            
            UserOperation uo = new UserOperation();
            
            if(uo.loginUser(username, password, role))
            {
                switch (role) {
//                    case "student":
//                        System.out.println("=======================================");
//                        System.out.println("Logged In Succesully as a Student");
//                        CRSStudentMenu sc = new CRSStudentMenu();
//                        sc.createStudentMenu(username);
//                        break;

//                    case "professor":
//                        System.out.println("=======================================");
//                        System.out.println("Logged In Succesully as a Professor");
//                        CRSProfessorMenu pc = new CRSProfessorMenu();
//                        pc.createProfessorMenu(username);
//                        break;

                    case "admin":
                        System.out.println("=======================================");
                        System.out.println("Logged In Succesully as a Admin");
                        CRSAdminMenu ac = new CRSAdminMenu();
                        ac.createAdminMenu(username);
                        break;

                    default:
                        System.out.println("Invalid Role");
                        System.out.println("=======================================");
                }
            		
            }
            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void registerUser() {

        try {
            while(true) {
            	System.out.println("=======================================");
                System.out.println("Register: ");
                System.out.println("---------------------------------------");
                System.out.println("1 : Student");
                System.out.println("2 : Exit registration");
                System.out.println("=======================================");

                int userType = sc.nextInt();
                sc.nextLine();

                switch(userType) {
                    case 1:
                        registerStudent();
                        break;
                    case 2:
                        return;
                    default:
                        System.out.println("Invalid input");
                } 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void registerStudent() {

        String username, password, name, department, contact, joiningYear;
//        StudentOperation so = new StudentOperation();

//        try {
        	System.out.println("=======================================");
            System.out.println("Enter your details");
            System.out.println("---------------------------------------");
            System.out.print("Username: ");
            username = sc.nextLine();
            System.out.print("Password: ");
            password = sc.nextLine();
            System.out.print("Name: ");
            name = sc.nextLine();
            System.out.print("Department: ");
            department = sc.nextLine();
            System.out.print("Year of joining: ");
            joiningYear = sc.nextLine();
            System.out.print("Contact Number: ");
            contact = sc.nextLine();
            System.out.println("=======================================");
            
            so.addStudent(username, name, password, department, contact, Integer.parseInt(joiningYear));
            
            System.out.println("User Added Successfully");
            System.out.println("=======================================");

            // to do : create student bean

//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
*/
package com.flipkart.application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.flipkart.business.*;
import com.flipkart.constant.Gender;
import com.flipkart.constant.NotificationType;
import com.flipkart.constant.Role;
import com.flipkart.exception.AdminAccountNotCreatedException;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.constant.Color;
import com.flipkart.utils.Secrets;
//import com.flipkart.utils.StringUtils;

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
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

                LocalDateTime myDateObj = LocalDateTime.now();

                String formattedDate = myDateObj.format(myFormatObj);


                System.out.println("Welcome "+userId);
                String role = userInterface.getRole(userId);
                Role userRole = Role.stringToName(role);
                switch (userRole) {
                    case ADMIN:
                        StringUtils.printSuccessMessage(formattedDate + " Login Successful as Admin", 100);
                        AdminCRSMenu adminMenu = new AdminCRSMenu();
                        adminMenu.createMenu();
                        break;
                    case PROFESSOR:
                        StringUtils.printSuccessMessage(formattedDate + " Login Successful for Professor");
                        ProfessorCRSMenu professorMenu = new ProfessorCRSMenu();
                        professorMenu.createMenu(userId);

                        break;
                    case STUDENT:

                        int studentId = studentInterface.getStudentId(userId);
                        boolean isApproved = studentInterface.isApproved(studentId);
                        if (isApproved) {
                            StringUtils.printSuccessMessage(formattedDate + " Login Successful for Student");
                            StudentCRSMenu studentMenu = new StudentCRSMenu();
                            studentMenu.create_menu(studentId);

                        } else {
                            StringUtils.printErrorMessage("Failed to login, you have not been approved by the administration!");
                            loggedin = false;
                        }
                        break;
                }


            } else {
                StringUtils.printErrorMessage("Invalid Credentials!");
            }

        
    }

    /**
     * Method to Register Admin
     */
    public void registerAdmin() {
        Scanner sc = new Scanner(System.in);
        StringUtils.printHeading("Enter root password");
        String rt_pwd = sc.nextLine();

        if(!rt_pwd.equals(Secrets.ROOT_PASSWORD))
        {
            StringUtils.printErrorMessage("Incorrect Password! Access Denied");
            return;
        }
        String userId, name, password, address, country;
        Gender gender;
        int genderV;
        try {
            //input all the student details
            StringUtils.printHeading("Administrative Account Creation Portal");
            System.out.println("Name:");
            name = sc.nextLine();
            System.out.println("Email:");
            userId = sc.next();
            System.out.println("Password:");
            password = sc.next();
            System.out.println("Gender: \t 1: Male \t 2.Female\t 3.Other");
            genderV = sc.nextInt();
            sc.nextLine();
            System.out.println("Address:");
            address = sc.nextLine();
            System.out.println("Country");
            country = sc.next();
            gender = Gender.getName(genderV);
            int admin= adminInterface.register(name, userId, password, gender, address, country);
        } catch (AdminAccountNotCreatedException ex) {
            StringUtils.printErrorMessage("Something went wrong! not registered. Please try again\n" + ex.getMessage());
            return;
        }
        StringUtils.printSuccessMessage("Administrative Account Successfully Created!");
    }
    /**
     * Method to help Student register themselves, pending admin approval
     */
    public void registerStudent() {
        Scanner sc = new Scanner(System.in);

        String userId, name, password, address, country, branchName;
        Gender gender;
        int genderV, batch;
        try {
            //input all the student details
            StringUtils.printHeading("Student Registration Portal");
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

        } catch (Exception ex) {
            StringUtils.printErrorMessage("Something went wrong! not registered. Please try again" + ex.getMessage());
            return;
        }
        StringUtils.printSuccessMessage("Student Successfully Registered!");
    }

    /**
     * Method to update password of User
     */
    public void updatePassword() {
        Scanner sc = new Scanner(System.in);
        String userId, newPassword, password;
        try {
            StringUtils.printHeading("Update Password Portal");
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
                    StringUtils.printSuccessMessage("Password updated successfully!");
                else
                    StringUtils.printErrorMessage("Something went wrong, please try again!");
            }
            else{
                StringUtils.printErrorMessage("Incorrect Password");
            }
        } catch (Exception ex) {
            StringUtils.printErrorMessage("Error Occurred " + ex.getMessage());
            return;
        }
    }
}

