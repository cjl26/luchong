package com.gzyct.m.api.busi.bean.hicatcard;

import com.gzyct.m.api.busi.util.CommonConvertor;
import com.project.m.api.common.biz.req.BizRequest;

public class SmsCodeReq extends BizRequest {



	private String phone;
	private String type;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}



}
