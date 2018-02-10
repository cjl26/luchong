package com.gzyct.m.api.busi.bean.merchant;

public class MerchantSettleServiceQueryItem {

	private Long merchantSettleId;

	private Long serviceId;

	private String serviceName;

	private Integer transactionTime;

	private Integer transactionFee;

	private String settle_start_time;

	private String settle_end_time;

	private Integer total_fee;

	private String create_time;

	public Long getMerchantSettleId() {
		return merchantSettleId;
	}

	public void setMerchantSettleId(Long merchantSettleId) {
		this.merchantSettleId = merchantSettleId;
	}

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Integer getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(Integer transactionTime) {
		this.transactionTime = transactionTime;
	}

	public Integer getTransactionFee() {
		return transactionFee;
	}

	public void setTransactionFee(Integer transactionFee) {
		this.transactionFee = transactionFee;
	}

	public String getSettle_start_time() {
		return settle_start_time;
	}

	public void setSettle_start_time(String settle_start_time) {
		this.settle_start_time = settle_start_time;
	}

	public String getSettle_end_time() {
		return settle_end_time;
	}

	public void setSettle_end_time(String settle_end_time) {
		this.settle_end_time = settle_end_time;
	}

	public Integer getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(Integer total_fee) {
		this.total_fee = total_fee;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

}
