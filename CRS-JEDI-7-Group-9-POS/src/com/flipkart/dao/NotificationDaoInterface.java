package com.flipkart.dao;

import java.sql.SQLException;


import com.flipkart.constant.ModeOfPayment;
import com.flipkart.constant.NotificationType;

public interface NotificationDaoInterface {

	/**
	 * Send Notification using SQL commands
	 * 
	 * @param type:          type of the notification to be sent
	 * @param studentId:     student to be notified
	 * @param modeOfPayment: mode of payment used, defined in enum
	 * @param amount:	     Amount of Money paid
	 * @param cardNumber:	 Card Number
	 * @param cvv:	     	 Cvv
	 * @return notification id for the record added in the database
	 * @throws SQLException If Sql Error comes up
	 */
	public int sendNotification(NotificationType type, int studentId, ModeOfPayment modeOfPayment, double amount, String cardNumber, String cvv)
			throws SQLException;
}
