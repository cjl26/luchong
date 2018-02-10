package com.gzyct.m.api.busi.bean.merchant;

import com.project.m.api.common.biz.req.BizRequest;

public class MerchantCardQueryReq extends BizRequest {

	//private String merchant_id;
	private String user_card_number;

	// public String getMerchant_id() {
	// return merchant_id;
	// }
	//
	// public void setMerchant_id(String merchant_id) {
	// this.merchant_id = merchant_id;
	// }

	public String getUser_card_number() {
		return user_card_number;
	}

	public void setUser_card_number(String user_card_number) {
		this.user_card_number = user_card_number;
	}

}
