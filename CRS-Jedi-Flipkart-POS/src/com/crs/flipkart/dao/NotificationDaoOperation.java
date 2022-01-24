
package com.crs.flipkart.dao;

import com.crs.flipkart.constant.ModeOfPayment;
import com.crs.flipkart.constant.NotificationType;


public class NotificationDaoOperation implements NotificationDaoInterface {
	private static volatile NotificationDaoOperation instance = null;

	private NotificationDaoOperation() {

	}

	public static NotificationDaoOperation getInstance() {
		if (instance == null) {
			// This is a synchronized block, when multiple threads will access this instance
			synchronized (NotificationDaoOperation.class) {
				instance = new NotificationDaoOperation();
			}
		}
		return instance;
	}

	@Override
	public int sendNotification(NotificationType type, int studentId, ModeOfPayment modeOfPayment, double amount, String cardNumber, String cvv)
	{
		int notificationId=0;
		
		switch (type) {
			case REGISTRATION:
				System.out.println("Registration successfull. Administration will verify the details and approve it!");
				notificationId = 1;
				break;
			case REGISTRATION_APPROVAL:
				System.out.println("Student with id " + studentId + " has been approved!");
				notificationId = 2;
				break;
			case PAYMENT:
				System.out.println("Student with id " + studentId + " fee has been paid");
				notificationId = 3;
			}
		
		return notificationId;
	}


	public void addPayment(int studentId, ModeOfPayment modeOfPayment, double amount, String CardNumber, String cvv)
	{	
		System.out.println("Payment details added");
	}

}
