package com.gzyct.m.api.busi.bean.merchant;

import java.util.List;


import com.gzyct.m.api.busi.db.entity.hicatcard.merchant.TMerchantSettleService;

public class MerchantSettleQueryItem {

	private Long merchant_settle_id;

	private String settle_start_time;

	private String settle_end_time;

	private Integer settle_fee;

	private Long merchant_id;

	private String create_time;

	private String update_time;

	private List<MerchantSettleServiceQueryItem> settleServiceList;

	public Long getMerchant_settle_id() {
		return merchant_settle_id;
	}

	public void setMerchant_settle_id(Long merchant_settle_id) {
		this.merchant_settle_id = merchant_settle_id;
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

	public Integer getSettle_fee() {
		return settle_fee;
	}

	public void setSettle_fee(Integer settle_fee) {
		this.settle_fee = settle_fee;
	}

	public Long getMerchant_id() {
		return merchant_id;
	}

	public void setMerchant_id(Long merchant_id) {
		this.merchant_id = merchant_id;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

	public List<MerchantSettleServiceQueryItem> getSettleServiceList() {
		return settleServiceList;
	}

	public void setSettleServiceList(List<MerchantSettleServiceQueryItem> settleServiceList) {
		this.settleServiceList = settleServiceList;
	}

}
