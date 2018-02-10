package com.gzyct.m.api.busi.bean.ext;

import java.io.Serializable;

public class UserSessionObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 681740520694155829L;
	private String user_id;
	private String session_id;
	private String token;
	private String expiretime;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
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
	public String getExpiretime() {
		return expiretime;
	}
	public void setExpiretime(String expiretime) {
		this.expiretime = expiretime;
	}
	
	
}
