package com.gzyct.m.api.busi.bean.ext;

import com.project.m.api.common.biz.req.BizRequest;

public class UserRegisterReq extends BizRequest{
	public static int SOURCE_ALIPAY = 1;

	private Long id;
	private String phone;
	private String verify_code;
	private String username;
	private String password;
	private long source;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public long getSource() {
		return source;
	}

	public void setSource(long source) {
		this.source = source;
	}
}
