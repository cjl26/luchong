package com.gzyct.m.api.busi.bean.ext;

public class UserInfo {
	
	private String user_id;
	private String phone;
	private String username;
	private long pay_flag;
	private long real_name_flag;
	private long gender;
	private String avatar;
	private String email;
	
	
	public long getReal_name_flag() {
		return real_name_flag;
	}
	public void setReal_name_flag(long real_name_flag) {
		this.real_name_flag = real_name_flag;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public long getPay_flag() {
		return pay_flag;
	}
	public void setPay_flag(long pay_flag) {
		this.pay_flag = pay_flag;
	}
	public long getGender() {
		return gender;
	}
	public void setGender(long gender) {
		this.gender = gender;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
