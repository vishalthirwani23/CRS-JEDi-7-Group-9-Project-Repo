package com.flipkart.business;

/**
 * @author venkat.karthik
 *
 */

import java.sql.SQLException;
import java.util.UUID;

import com.flipkart.constant.ModeOfPayment;
import com.flipkart.constant.NotificationType;
import com.flipkart.dao.NotificationDaoInterface;
import com.flipkart.dao.NotificationDaoOperation;

public class NotificationOperation implements NotificationInterface {

	private static volatile NotificationOperation instance = null;
	NotificationDaoInterface notificationDaoInterface = NotificationDaoOperation.getInstance();

	private NotificationOperation() {

	}

	public static NotificationOperation getInstance() {
		if (instance == null) {
			synchronized (NotificationOperation.class) {
				instance = new NotificationOperation();
			}
		}
		return instance;
	}

	public int sendNotification(NotificationType type, int studentId, ModeOfPayment modeOfPayment, double amount, String cardNumber, String cvv) {
		int notificationId = 0;
		try {	

			notificationId = notificationDaoInterface.sendNotification(type, studentId, modeOfPayment, amount, cardNumber, cvv);

		} catch (SQLException ex) {
			System.out.println("Error occurred " + ex.getMessage());
		}
		return notificationId;
	}

	public UUID getReferenceId(int notificationId) {
		return null;
	}

}
