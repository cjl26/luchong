package com.gzyct.m.api.busi.bean.ext;

import com.project.m.api.common.biz.resp.BizResp;
/**
 * 3.9	获取短信验证码 响应参数
 * @author zoudb
 *
 */
public class SmsCodeResp extends BizResp{

	private String attach;
	

	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
}
