package com.gzyct.m.api.busi.bean.hicatcard;

import com.project.m.api.common.biz.req.BizRequest;

public class UserInfoReq extends BizRequest {

	private String nickName;
	private String avaterUrl;
	private String gender;
	private String country;
	private String province;
	private String city;
	private String language;
	private String phone;

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getAvaterUrl() {
		return avaterUrl;
	}

	public void setAvaterUrl(String avaterUrl) {
		this.avaterUrl = avaterUrl;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
