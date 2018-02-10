package com.gzyct.m.api.busi.bean.ext;

import com.project.m.api.common.biz.req.BizRequest;

/**
 * 3.9	获取短信验证码 请求参数
 * @author zoudb
 *
 */
public class SmsCodeReq extends BizRequest{

	private String phone;
	private String type;
	private String attach;
	

	
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
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
