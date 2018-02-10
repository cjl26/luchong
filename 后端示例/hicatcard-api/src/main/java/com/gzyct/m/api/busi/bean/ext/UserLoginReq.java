package com.gzyct.m.api.busi.bean.ext;

import com.project.m.api.common.biz.req.BizRequest;

public class UserLoginReq extends BizRequest{
	
	private String phone;
	private String password;
	private String userpk;
	private String attach;
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public String getUserpk() {
		return userpk;
	}
	public void setUserpk(String userpk) {
		this.userpk = userpk;
	}

}
