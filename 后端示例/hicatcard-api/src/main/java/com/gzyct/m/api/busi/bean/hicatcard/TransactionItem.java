package com.gzyct.m.api.busi.bean.hicatcard;

import java.util.List;

public class TransactionItem {

	private String transaction_id;
	private String transaction_order;
	private String merchant_id;
	private TransactionMerchantItem merchantInfo;
	private String user_id;
	private String user_card_id;
	private String user_card_number;
	private String car_licence;
	private String create_time;
	private String phone;
	private String listDetail; // 用于列表显示的数据 次数/总次数
	private List<TransactionServiceItem> services;

	public String getUser_card_number() {
		return user_card_number;
	}

	public void setUser_card_number(String user_card_number) {
		this.user_card_number = user_card_number;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getTransaction_order() {
		return transaction_order;
	}

	public void setTransaction_order(String transaction_order) {
		this.transaction_order = transaction_order;
	}

	public String getMerchant_id() {
		return merchant_id;
	}

	public void setMerchant_id(String merchant_id) {
		this.merchant_id = merchant_id;
	}

	public TransactionMerchantItem getMerchantInfo() {
		return merchantInfo;
	}

	public void setMerchantInfo(TransactionMerchantItem merchantInfo) {
		this.merchantInfo = merchantInfo;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_card_id() {
		return user_card_id;
	}

	public void setUser_card_id(String user_card_id) {
		this.user_card_id = user_card_id;
	}

	public String getCar_licence() {
		return car_licence;
	}

	public void setCar_licence(String car_licence) {
		this.car_licence = car_licence;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public List<TransactionServiceItem> getServices() {
		return services;
	}

	public void setServices(List<TransactionServiceItem> services) {
		this.services = services;
	}

	public String getListDetail() {
		return listDetail;
	}

	public void setListDetail(String listDetail) {
		this.listDetail = listDetail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
