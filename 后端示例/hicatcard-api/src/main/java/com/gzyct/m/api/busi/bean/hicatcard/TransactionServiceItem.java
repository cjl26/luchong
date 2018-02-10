package com.gzyct.m.api.busi.bean.hicatcard;

public class TransactionServiceItem {

	private String transaction_service_id;
	private String transaction_id;
	private String user_card_service_id;
	private String service_id;
	private String service_name;
	private String service_total_time; // service的总次数
	private Integer service_time;
	private Integer time_before;
	private Integer time_after;

	public String getTransaction_service_id() {
		return transaction_service_id;
	}

	public void setTransaction_service_id(String transaction_service_id) {
		this.transaction_service_id = transaction_service_id;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getUser_card_service_id() {
		return user_card_service_id;
	}

	public void setUser_card_service_id(String user_card_service_id) {
		this.user_card_service_id = user_card_service_id;
	}

	public String getService_id() {
		return service_id;
	}

	public void setService_id(String service_id) {
		this.service_id = service_id;
	}

	public String getService_name() {
		return service_name;
	}

	public void setService_name(String service_name) {
		this.service_name = service_name;
	}

	public Integer getService_time() {
		return service_time;
	}

	public void setService_time(Integer service_time) {
		this.service_time = service_time;
	}

	public Integer getTime_before() {
		return time_before;
	}

	public void setTime_before(Integer time_before) {
		this.time_before = time_before;
	}

	public Integer getTime_after() {
		return time_after;
	}

	public void setTime_after(Integer time_after) {
		this.time_after = time_after;
	}

	public String getService_total_time() {
		return service_total_time;
	}

	public void setService_total_time(String service_total_time) {
		this.service_total_time = service_total_time;
	}

}
