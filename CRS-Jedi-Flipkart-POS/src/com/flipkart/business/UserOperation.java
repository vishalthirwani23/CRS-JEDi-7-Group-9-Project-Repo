package com.flipkart.business;

public class UserOperation implements UserInterface{
	public void updatePassword(String userID, String password) {

		System.out.println("Updating Password");
	}
	
	public void updateContactNumber(String userID, String number) {

		System.out.println("Updating Contact Number");
	}

	public void updateRole(String userID, String role) {
		System.out.println("Updating Role");
	}

	public String getRole(String userID) {

		return "";
	}


	public boolean loginUser(String userID, String password, String role) {
		
		return true;
	}
}
