package cloudPayAdmin.util;

import org.apache.commons.lang.StringUtils;

public class CardNumUtil {
	
	/**
	 * 把羊城通卡号补全为16位的工具方法
	 * @param cardNum
	 * @return
	 */
	public static String completeCardNum(String cardNum) {
		if(cardNum.length() == 16) {
			return cardNum;
		} else if (cardNum.length() == 8) {
			return "51000001" + cardNum;
		} else if (cardNum.length() == 10) {
			return "510000" + cardNum;
		} else {
			return cardNum;
		}
	}
	
	/**
	 * 16位卡号截取成为10位
	 * @param cardNum
	 * @return
	 */
	public static String subCardNum(String cardNum) {
		if(StringUtils.startsWith(cardNum, "510000")) {
			return StringUtils.substring(cardNum,6);
		} else {
			return cardNum;
		}
	}
}
