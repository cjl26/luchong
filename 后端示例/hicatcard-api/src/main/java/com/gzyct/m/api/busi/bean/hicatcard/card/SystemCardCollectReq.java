package com.gzyct.m.api.busi.bean.hicatcard.card;

import com.project.m.api.common.biz.req.BizRequest;

public class SystemCardCollectReq extends BizRequest {

	private String card_number;

	public String getCard_number() {
		return card_number;
	}

	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}

}
