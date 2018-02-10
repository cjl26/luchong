package com.gzyct.m.api.busi.bean.ext;


import com.project.m.api.common.biz.resp.BizResp;

public class UserInfoQueryResp extends BizResp{
	
	private UserInfo user_info;

	public UserInfo getUser_info() {
		return user_info;
	}

	public void setUser_info(UserInfo user_info) {
		this.user_info = user_info;
	}
	



}
