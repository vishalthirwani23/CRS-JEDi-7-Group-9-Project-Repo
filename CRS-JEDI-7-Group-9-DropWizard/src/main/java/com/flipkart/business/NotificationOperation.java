package com.flipkart.business;

import com.flipkart.*;

import java.sql.SQLException;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.flipkart.constant.ModeOfPayment;
import com.flipkart.constant.NotificationType;
import com.flipkart.dao.NotificationDaoInterface;
import com.flipkart.dao.NotificationDaoOperation;

public class NotificationOperation implements NotificationInterface {

	private static volatile NotificationOperation instance = null;
	NotificationDaoInterface notificationDaoInterface = new NotificationDaoOperation();
	private static Logger logger = Logger.getLogger(NotificationOperation.class);

	public NotificationOperation() {

	}

	
	public int sendNotification(NotificationType type, int studentId, ModeOfPayment modeOfPayment, double amount, String cardNumber, String cvv) {
		int notificationId = 0;
		try {	

			notificationId = notificationDaoInterface.sendNotification(type, studentId, modeOfPayment, amount, cardNumber, cvv);

		} catch (SQLException ex) {
			logger.error("Error occurred " + ex.getMessage());
		}
		return notificationId;
	}

	public UUID getReferenceId(int notificationId) {
		return null;
	}

}
