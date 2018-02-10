package com.gzyct.m.api.busi.bean.hicatcard.coupon;

import com.project.m.api.common.biz.req.BizRequest;

public class UserCouponListReq extends BizRequest {

	private String status;
	private String card_id;
	private String type;
	private Integer page;
	private Integer pagesize;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCard_id() {
		return card_id;
	}

	public void setCard_id(String card_id) {
		this.card_id = card_id;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
