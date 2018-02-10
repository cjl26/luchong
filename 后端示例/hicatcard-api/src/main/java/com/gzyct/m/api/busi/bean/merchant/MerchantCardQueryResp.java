package com.gzyct.m.api.busi.bean.merchant;

import java.util.List;

import com.gzyct.m.api.busi.bean.hicatcard.user.UserCardServiceItem;
import com.project.m.api.common.biz.resp.BizResp;

public class MerchantCardQueryResp extends BizResp {

	private String user_id;
	private String card_id;
	private String card_name;
	private String type;
	private String picture_url;
	private String fee;
	private String detail;
	private String user_card_number;
	private String user_card_id;
	private List<UserCardServiceItem> services;
	private String create_time;
	private String update_time;
	private String car_licence;
	private String end_time;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getCard_id() {
		return card_id;
	}

	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}

	public String getUser_card_number() {
		return user_card_number;
	}

	public void setUser_card_number(String user_card_number) {
		this.user_card_number = user_card_number;
	}

	public String getUser_card_id() {
		return user_card_id;
	}

	public void setUser_card_id(String user_card_id) {
		this.user_card_id = user_card_id;
	}

	public List<UserCardServiceItem> getServices() {
		return services;
	}

	public void setServices(List<UserCardServiceItem> services) {
		this.services = services;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

	public String getCard_name() {
		return card_name;
	}

	public void setCard_name(String card_name) {
		this.card_name = card_name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPicture_url() {
		return picture_url;
	}

	public void setPicture_url(String picture_url) {
		this.picture_url = picture_url;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getCar_licence() {
		return car_licence;
	}

	public void setCar_licence(String car_licence) {
		this.car_licence = car_licence;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

}
