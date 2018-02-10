package com.gzyct.m.api.busi.bean.merchant;

import com.project.m.api.common.biz.req.BizRequest;

/**
 * 查询未结算的数据 - 分服务汇总返回，然后汇总一个时间和金额给你
 */
public class MerchantTransactionQueryReq extends BizRequest {

	private String merchant_id;
	//private String begin_date;
	//private String end_date;
	//private Integer page;
	//private Integer pagesize;

	public String getMerchant_id() {
		return merchant_id;
	}

	public void setMerchant_id(String merchant_id) {
		this.merchant_id = merchant_id;
	}

	// public Integer getPage() {
	// return page;
	// }
	//
	// public void setPage(Integer page) {
	// this.page = page;
	// }
	//
	// public Integer getPagesize() {
	// return pagesize;
	// }
	//
	// public void setPagesize(Integer pagesize) {
	// this.pagesize = pagesize;
	// }

//	public String getBegin_date() {
//		return begin_date;
//	}
//
//	public void setBegin_date(String begin_date) {
//		this.begin_date = begin_date;
//	}
//
//	public String getEnd_date() {
//		return end_date;
//	}
//
//	public void setEnd_date(String end_date) {
//		this.end_date = end_date;
//	}

}
