package com.gzyct.m.api.busi.util;

public class CardNumberUtil {

	/**
	 * 把卡号转换成16位
	 * 
	 * @param cardNumber
	 * @return
	 */
	public static String toSixteenLength(String cardNumber) {
		if (cardNumber == null) {
			return "";
		} else if (cardNumber.length() == 8) {
			return "51000001" + cardNumber;
		} else if (cardNumber.length() == 9) {
			return "5100000" + cardNumber;
		} else if (cardNumber.length() == 10) {
			return "510000" + cardNumber;
		}
		return cardNumber;
	}

	/**
	 * 把16位卡号转换成10位
	 * 
	 * @param cardNumber
	 * @return
	 */
	public static String toTenLength(String cardNumber) {
		if (cardNumber == null) {
			return "";
		} else if (cardNumber.length() == 16) {
			cardNumber = cardNumber.substring(6);
		}
		return cardNumber;
	}
}
