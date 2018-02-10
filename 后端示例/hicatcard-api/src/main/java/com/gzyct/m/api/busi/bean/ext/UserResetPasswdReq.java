package com.gzyct.m.api.busi.bean.ext;

import com.project.m.api.common.biz.req.BizRequest;

public class UserResetPasswdReq extends BizRequest{
	
	private Long id;
	private String phone;
	private String verify_code;
	private String n_password;
	private String attach;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getVerify_code() {
		return verify_code;
	}
	public void setVerify_code(String verify_code) {
		this.verify_code = verify_code;
	}
	public String getN_password() {
		return n_password;
	}
	public void setN_password(String n_password) {
		this.n_password = n_password;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}

}
