package cloudPayAdmin.util;


public class IdGenerator {
	public static String OrderID_Prefix_PAY = "31"; // 羊城通宝支付订单号
	public static String ORDERID_PREFIX_USER_CARD = "32"; // 用户卡片号
	public static String ORDERID_PREFIX_TRANSACTION = "33"; // 交易单号
	public static String ORDERID_PREFIX_SYSTEM_CARD = "34"; // 批量制卡卡号
	public static String ORDERID_PREFIX_MERCHANT = "35"; // 商户号码
	
	// public static String OrderID_Prefix_YCTPAY_REF = "32"; //羊城通宝退款单号
	// public static String OrderID_Prefix_CARD = "33"; //卡转账支付单号
	// public static String OrderID_Prefix_CARD_REF = "34"; //卡转账退款单号
	// public static String OrderID_Prefix_ACCT = "35"; //羊城通宝账户充值支付单号
	// public static String OrderID_Prefix_ACCT_REF = "36"; //羊城通宝账户充值退款单号
	// public static String OrderID_Prefix_PROD = "37"; //购买商品支付单号
	// public static String OrderID_Prefix_PROD_REF = "38"; //购买商品退款单号
	// public static String OrderID_Prefix_LITE_POS = "39"; //轻量化终端扣款单号
	//
	// public static String OrderID_Prefix_ALIPAY_DEPOSIT = "41"; //交押金订单号
	// public static String OrderID_Prefix_ALIPAY_DEPOSIT_REF = "42"; //退押金订单号
	// public static String OrderID_Prefix_ALIPAY_EXTERNAL_SIGN_NO = "43";
	// //支付宝外部签约协议号
	// public static String OrderID_Prefix_ALIPAY_OVERDUE = "44"; //欠费的号
	// public static String OrderID_Prefix_STUDENT_CARD_APPLY_PAY = "45";
	// //学生卡申办支付
	// public static String OrderID_Prefix_STUDENT_CARD_APPLY_PAY_REFUND = "46";
	// //学生卡申办退款

	// 生成orderId
	static public String generateOrderId(String orderPrefix, String channelCode, String subType) {
		// 渠道代码
		String channel = "00" + channelCode;
		channel = channel.substring(channel.length() - 2, channel.length());
		// System.out.println("channel = " + channel);

		// 渠道子代码
		String subTypeStr = "0000" + subType;
		subTypeStr = subTypeStr.substring(subTypeStr.length() - 4, subTypeStr.length());
		// System.out.println("subTypeStr = " + subTypeStr);

		// 当前时间14位 yyyyMMddHHmmss
		String currTime = TimeUtil.getCurrTime("yyyyMMddHHmmss");
		// System.out.println("currTime = " + currTime);

		// 随机数10位
		String strRandom = ParamHelper.buildRandom(5) + "" + ParamHelper.buildRandom(5);

		// System.out.println("strRandom = " + strRandom);
		String out_trade_no = orderPrefix + channel + subTypeStr + currTime + strRandom;

		// System.out.println("out_trade_no = " + out_trade_no);
		// System.out.println("out_trade_no length = " + out_trade_no.length());

		return out_trade_no;
	}

	// 生成refundId
	static public String generateRefundId(String orderPrefix, String channelCode, String subType) {

		// 渠道代码
		String channel = "00" + channelCode;
		channel = channel.substring(channel.length() - 2, channel.length());
		// System.out.println("channel = " + channel);

		// 渠道子代码
		String subTypeStr = "0000" + subType;
		subTypeStr = subTypeStr.substring(subTypeStr.length() - 4, subTypeStr.length());
		// System.out.println("subTypeStr = " + subTypeStr);

		// 当前时间14位 yyyyMMddHHmmss
		String currTime = TimeUtil.getCurrTime("yyyyMMddHHmmss");
		// System.out.println("currTime = " + currTime);

		// 随机数10位
		String strRandom = ParamHelper.buildRandom(5) + "" + ParamHelper.buildRandom(5);
		String out_trade_no = orderPrefix + channel + subTypeStr + currTime + strRandom;

		// System.out.println("out_trade_no = " + out_trade_no);
		// System.out.println("out_trade_no length = " + out_trade_no.length());

		return out_trade_no;
	}

	// 生成批量制卡卡号
	static public String generateSystemCardId(String orderPrefix, String channelCode, String subType) {

		// 渠道代码
		String channel = "00" + channelCode;
		channel = channel.substring(channel.length() - 2, channel.length());
		// System.out.println("channel = " + channel);

		// 渠道子代码
		String subTypeStr = "0000" + subType;
		subTypeStr = subTypeStr.substring(subTypeStr.length() - 4, subTypeStr.length());
		// System.out.println("subTypeStr = " + subTypeStr);

		// 当前时间14位 yyyyMMddHHmmss
		String currTime = TimeUtil.getCurrTime("yyyyMMddHHmmss");
		// System.out.println("currTime = " + currTime);

		// 随机数10位
		String strRandom = ParamHelper.buildRandom(5) + "" + ParamHelper.buildRandom(5);
		String out_trade_no = orderPrefix + channel + subTypeStr + currTime + strRandom;

		// System.out.println("out_trade_no = " + out_trade_no);
		// System.out.println("out_trade_no length = " + out_trade_no.length());

		return out_trade_no;
	}
	
	// 生成批量制卡卡号
		static public String generateMerchantId(String orderPrefix, String channelCode, String subType) {

			// 渠道代码
			String channel = "00" + channelCode;
			channel = channel.substring(channel.length() - 2, channel.length());
			// System.out.println("channel = " + channel);

			// 渠道子代码
			String subTypeStr = "0000" + subType;
			subTypeStr = subTypeStr.substring(subTypeStr.length() - 4, subTypeStr.length());
			// System.out.println("subTypeStr = " + subTypeStr);

			// 当前时间14位 yyyyMMddHHmmss
			String currTime = TimeUtil.getCurrTime("yyyyMMddHHmmss");
			// System.out.println("currTime = " + currTime);

			// 随机数10位
			String strRandom = ParamHelper.buildRandom(5) + "" + ParamHelper.buildRandom(5);
			String out_trade_no = orderPrefix + channel + subTypeStr + currTime + strRandom;

			// System.out.println("out_trade_no = " + out_trade_no);
			// System.out.println("out_trade_no length = " + out_trade_no.length());

			return out_trade_no;
		}
}
