package com.gzyct.m.api.busi.bean.ext;

import com.project.m.api.common.biz.req.BizRequest;

/**
 * 发货请求
 * 
 * @author Administrator
 * 学生卡新办发货通知
 *
 */
public class GoodDeliveryStudentCardReq extends BizRequest {

	private String order_id; // 商品订单号
	private String pay_channel; // 支付渠道
	private String third_pay_id; // 第三方订单号
	private String pay_status; // 支付状态
	private int total_fee; // 商品总金额
	private int pay_fee; // 支付金额
	private String yct_extend; // 羊城通扩展字段

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getPay_channel() {
		return pay_channel;
	}

	public void setPay_channel(String pay_channel) {
		this.pay_channel = pay_channel;
	}

	public String getThird_pay_id() {
		return third_pay_id;
	}

	public void setThird_pay_id(String third_pay_id) {
		this.third_pay_id = third_pay_id;
	}

	public String getPay_status() {
		return pay_status;
	}

	public void setPay_status(String pay_status) {
		this.pay_status = pay_status;
	}

	public int getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}

	public int getPay_fee() {
		return pay_fee;
	}

	public void setPay_fee(int pay_fee) {
		this.pay_fee = pay_fee;
	}

	public String getYct_extend() {
		return yct_extend;
	}

	public void setYct_extend(String yct_extend) {
		this.yct_extend = yct_extend;
	}

}
