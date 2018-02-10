package com.gzyct.m.api.busi.bean;


/**
 * 流水号信息
 * @author wenxiao
 *
 */
public class FlowInfo {

	private String user_id;
	private String order_id;
	private Integer flag;
	private Integer type;
	private String merchant;
	private Integer tran_fee;
	private Integer pay_fee;
	private String time;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getMerchant() {
		return merchant;
	}
	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}
	public Integer getTran_fee() {
		return tran_fee;
	}
	public void setTran_fee(Integer tran_fee) {
		this.tran_fee = tran_fee;
	}
	public Integer getPay_fee() {
		return pay_fee;
	}
	public void setPay_fee(Integer pay_fee) {
		this.pay_fee = pay_fee;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

}
