package com.gzyct.m.api.busi.bean.hicatcard;

import com.project.m.api.common.biz.req.BizRequest;

public class TransactionListReq extends BizRequest {

	private String user_card_id;
	private String merchant_id;
	private String search_text;
	private String begin_date;
	private String end_date;
	private Integer page;
	private Integer pagesize;

	public String getUser_card_id() {
		return user_card_id;
	}

	public void setUser_card_id(String user_card_id) {
		this.user_card_id = user_card_id;
	}

	public String getMerchant_id() {
		return merchant_id;
	}

	public void setMerchant_id(String merchant_id) {
		this.merchant_id = merchant_id;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPagesize() {
		return pagesize;
	}

	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}

	public String getSearch_text() {
		return search_text;
	}

	public void setSearch_text(String search_text) {
		this.search_text = search_text;
	}

	public String getBegin_date() {
		return begin_date;
	}

	public void setBegin_date(String begin_date) {
		this.begin_date = begin_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
}
