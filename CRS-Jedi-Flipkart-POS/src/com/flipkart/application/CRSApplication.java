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