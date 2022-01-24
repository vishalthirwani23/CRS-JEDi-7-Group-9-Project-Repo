/**
 * 
 */
package com.flipkart.bean;

/**
 * @author User
 *
 */
public class Notification {
	private String notificationID;
	private String paymentID;
	private String message;

	
	public Notification() {
		notificationID = "null";
		paymentID = "null";
		message = "NothingToDisplay";
	}
	
	
	public Notification(String notificationID, String paymentID, String message) {
		
		this.notificationID = notificationID;
		this.paymentID = paymentID;
		this.message = message;
		
	}
	public String getNotificationID() {
		return notificationID;
	}


	public void setNotificationID(String notificationID) {
		this.notificationID = notificationID;
	}


	public String getPaymentID() {
		return paymentID;
	}


	public void setPaymentID(String paymentID) {
		this.paymentID = paymentID;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}

}
