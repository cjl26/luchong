package com.gzyct.m.api.busi.constant;

import org.apache.commons.lang.StringUtils;


/**
 * PAYCENTER 退款状态
 */
public class RefundStatus {
	
	/**
	 *  1已受理
	 */
	public static final String RS_INIT = "1";
	/**
	 *  2退款处理中
	 */
	public static final String RS_IN_PROCESS = "2";
	/**
	 *  3 发往第三方支付申请
	 */
	public static final String RS_SENT = "3";
	/**
	 *  4 退款成功
	 */
	public static final String RS_SUCCESS = "4";
	/**
	 *  5 退款失败
	 */
	public static final String RS_FAIL = "5";
	/**
	 * 6 成功，但需人工处理
	 */
	public static final String RS_MANUAL = "6";
	
	/**
	 * 适配手机QQ支付退款返回状态
		4，10：退款成功。
		3，5，6：退款失败。
		8，9，11：退款处理中。
		1，2：未确定，需要商户原退款单号重新发起。
		7：转入代发，需要商户人工干预
	 * @param qpayRefundSts
	 * @return
	 */
	public static String fromQPay(Long qpayRefundSts){
		if(qpayRefundSts==null)return RS_FAIL;
		if(qpayRefundSts == 4 || qpayRefundSts==10)return RS_SUCCESS;
		if(qpayRefundSts==3 || qpayRefundSts==5 || qpayRefundSts==6)return RS_FAIL;
		if(qpayRefundSts==8 || qpayRefundSts==9 || qpayRefundSts==11)return RS_SENT;//RS_IN_PROCESS;
		if(qpayRefundSts==7)return RS_MANUAL;
		return RS_FAIL;
	}
	/**
	 * 退款状态：SUCCESS—退款成功
	 * FAIL—退款失败   PROCESSING—退款处理中
	 * NOTSURE—未确定，需要商户原退款单号重新发起
	 * CHANGE—转入代发，退款到银行发现用户的卡作废或者冻结了，导致原路退款银行卡失败，资金回流到商户的现金帐号，需要商户人工干预，通过线下或者财付通转账的方式进行退款。
	 */
	public static String fromWxin(String wxinRefundSts){
		if(wxinRefundSts==null)return RS_FAIL;
		if(wxinRefundSts.equals("SUCCESS"))return RS_SUCCESS;
		if(wxinRefundSts.equals("FAIL"))return RS_FAIL;
		if(wxinRefundSts.equals("PROCESSING"))return RS_SENT;//RS_IN_PROCESS;
		if(wxinRefundSts.equals("CHANGE"))return RS_MANUAL;
		return RS_FAIL;
	}
	
	/**
	 * 适配通用第三方支付支付退款返回状态
		1 已受理
        2 退款处理中
        3 退款处理中(发往第三方支付申请)
        4 退款成功
        5 退款失败
        6 成功，但需人工处理

	 * @param genRefundSts
	 * @return
	 */
	public static String fromGenPay(String genRefundSts){
		if(StringUtils.isBlank(genRefundSts)) return RS_FAIL;
        if(genRefundSts.equals("1"))return RS_SENT;
		if(genRefundSts.equals("2"))return RS_SENT;
        if(genRefundSts.equals("3"))return RS_SENT;
        if(genRefundSts.equals("4"))return RS_SUCCESS;
		if(genRefundSts.equals("5"))return RS_FAIL;
		if(genRefundSts.equals("6"))return RS_MANUAL;
		return RS_FAIL;
	}
}
