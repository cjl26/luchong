package com.gzyct.m.api.busi.bean.hicatcard.card;

import com.project.m.api.common.biz.req.BizRequest;

public class CardActivateReq extends BizRequest {

	private String user_card_id;
	//private String sms_code;

	public String getUser_card_id() {
		return user_card_id;
	}

	public void setUser_card_id(String user_card_id) {
		this.user_card_id = user_card_id;
	}

//	public String getSms_code() {
//		return sms_code;
//	}
//
//	public void setSms_code(String sms_code) {
//		this.sms_code = sms_code;
//	}
}
