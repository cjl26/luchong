package com.gzyct.m.api.busi.bean.ext;


import com.project.m.api.common.biz.resp.BizResp;

public class UserLoginResp extends BizResp{
	
	private String session_id;
	private String token;
	private UserInfo user_info;

	public UserInfo getUser_info() {
		return user_info;
	}

	public void setUser_info(UserInfo user_info) {
		this.user_info = user_info;
	}
	
	public String getSession_id() {
		return session_id;
	}

	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}



}
