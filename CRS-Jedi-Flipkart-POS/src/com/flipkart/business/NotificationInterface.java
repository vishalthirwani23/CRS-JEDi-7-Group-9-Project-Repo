package com.flipkart.business;
/**
 * @author venkat.karthik
 *
 */

import java.util.UUID;

import com.flipkart.constant.ModeOfPayment;
import com.flipkart.constant.NotificationType;

public interface NotificationInterface {

	public int sendNotification(NotificationType type, int studentId, ModeOfPayment modeOfPayment, double amount, String cardNumber, String cvv);

	public UUID getReferenceId(int notificationId);
}
