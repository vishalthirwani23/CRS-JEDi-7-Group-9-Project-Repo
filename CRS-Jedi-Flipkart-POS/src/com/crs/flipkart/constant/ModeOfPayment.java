/**
 * 
 */
package com.crs.flipkart.constant;


public enum ModeOfPayment {

	CREDIT_CARD, DEBIT_CARD;

	/**
	 * Method to get Mode of Payment
	 *
	 * @param value Amount
	 * @return Mode of Payment
	 */
	public static ModeOfPayment getModeofPayment(int value) {
		switch (value) {
			case 1:
				return ModeOfPayment.CREDIT_CARD;
			case 2:
				return ModeOfPayment.DEBIT_CARD;
			default:
				return null;

		}

	}

}
