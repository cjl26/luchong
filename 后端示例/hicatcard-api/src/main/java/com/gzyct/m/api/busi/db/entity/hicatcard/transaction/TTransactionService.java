package com.gzyct.m.api.busi.db.entity.hicatcard.transaction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_transaction_service")
public class TTransactionService {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TRANSACTION_SERVICE_ID")
	private Long transactionServiceId;

	@Column(name = "TRANSACTION_ID")
	private Long transactionId;

	@Column(name = "USER_CARD_SERVICE_ID")
	private Long userCardServiceId;

	@Column(name = "SERVICE_ID")
	private Long serviceId;

	@Column(name = "MERCHANT_FEE")
	private Integer merchantFee;

	@Column(name = "SERVICE_TIME")
	private Integer serviceTime;

	@Column(name = "TIME_BEFORE")
	private Integer timeBefore;

	@Column(name = "TIME_AFTER")
	private Integer timeAfter;

	@Column(name = "CREATE_TIME")
	private String createTime;

	@Column(name = "UPDATE_TIME")
	private String updateTime;

	@Column(name = "ENABLE")
	private boolean enable = Boolean.TRUE;

	public Long getTransactionServiceId() {
		return transactionServiceId;
	}

	public void setTransactionServiceId(Long transactionServiceId) {
		this.transactionServiceId = transactionServiceId;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public Integer getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(Integer serviceTime) {
		this.serviceTime = serviceTime;
	}

	public Integer getTimeBefore() {
		return timeBefore;
	}

	public void setTimeBefore(Integer timeBefore) {
		this.timeBefore = timeBefore;
	}

	public Integer getTimeAfter() {
		return timeAfter;
	}

	public void setTimeAfter(Integer timeAfter) {
		this.timeAfter = timeAfter;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public Long getUserCardServiceId() {
		return userCardServiceId;
	}

	public void setUserCardServiceId(Long userCardServiceId) {
		this.userCardServiceId = userCardServiceId;
	}

	public Integer getMerchantFee() {
		return merchantFee;
	}

	public void setMerchantFee(Integer merchantFee) {
		this.merchantFee = merchantFee;
	}

}
