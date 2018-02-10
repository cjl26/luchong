package com.gzyct.m.api.busi.constant;

public class TradeStatus {

	// 1未支付 2用户支付中 3支付成功 4发货失败 5发货成功 6已关闭 7已撤销（刷卡支付）8支付失败(其他原因，如银行返回失败) 11退款中
	// 12已退款
	public static final String[] sts_str = { "0订单初始化", "1未支付", "2用户支付中", "3支付成功", "4发货失败", "5发货成功", "6已关闭",
			"7已撤销（刷卡支付）", "8支付失败(其他原因，如银行返回失败)", "9未定义", "10未定义", "11退款中", "12已退款" };
	/**
	 * 1未支付
	 */
	public static final long TS_NOT_PAY = 1;
	/**
	 * 2用户支付中
	 */
	public static final long TS_PAYING = 2;
	/**
	 * 3支付成功
	 */
	public static final long TS_PAYED = 3;
	/**
	 * 4发货失败
	 */
	public static final long TS_DELIVER_FAIL = 4;
	/**
	 * 5发货成功
	 */
	public static final long TS_DELIVERED = 5;
	/**
	 * 6已关闭
	 */
	public static final long TS_CLOSED = 6;
	/**
	 * 7已撤销（刷卡支付）
	 */
	public static final long TS_REVOKED = 7;
	/**
	 * 8支付失败(其他原因，如银行返回失败)
	 */
	public static final long TS_PAY_ERROR = 8;
	/**
	 * 11 退款中
	 */
	public static final long TS_REFUNDING = 11;
	/**
	 * 12 已退款
	 */
	public static final long TS_REFUNDED = 12;

	public static String getStatusStr(long sts) {
		return sts_str[(int) sts];
	}

	/**
	 * 支付状态 1 未支付 2 已支付 3 发货受理 4 已发货 5 已退款
	 */
	public static final long PAYCENTER_NOTPAY = 1;
	public static final long PAYCENTER_PAYED = 2;
	public static final long PAYCENTER_DELIVERING = 3;
	public static final long PAYCENTER_DELIVERED = 4;
	public static final long PAYCENTER_REFUND = 5;
}
