package com.gzyct.m.api.busi.bean.hicatcard.card;

import com.project.m.api.common.biz.req.BizRequest;

public class CardDetailQueryReq extends BizRequest {

	private String user_card_id; // 根据用户卡ID查
	private String system_card_number; // 根据系统卡号查

	public String getUser_card_id() {
		return user_card_id;
	}

	public void setUser_card_id(String user_card_id) {
		this.user_card_id = user_card_id;
	}

	public String getSystem_card_number() {
		return system_card_number;
	}

	public void setSystem_card_number(String system_card_number) {
		this.system_card_number = system_card_number;
	}

}
