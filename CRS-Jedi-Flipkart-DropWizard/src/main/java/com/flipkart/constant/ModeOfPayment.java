/**
 * 
 */
package com.flipkart.constant;

/**
 * @author User
 *
 */
public enum ModeOfPayment {
	NET_BANKING, CARD, CASH; 

	/**
	 * Method to get Mode of Payment
	 *
	 * @param value Amount
	 * @return Mode of Payment
	 */
	public static ModeOfPayment getModeofPayment(int value) {
		switch (value) {
			case 1:
				return ModeOfPayment.NET_BANKING;
			case 2:
				return ModeOfPayment.CARD;
			case 3:
				return ModeOfPayment.CASH;
			default:
				return null;

		}

	}

}
