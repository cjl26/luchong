package com.gzyct.m.api.busi.bean.hicatcard.card;

import java.util.List;

public class CardItem {
	private String card_id;
	private String card_name;
	private String type;
	private String picture_url;
	private String effectiveTime;
	private String effectiveDay;
	private String fee;
	private String detail;
	private String serviceName;
	private String serviceTime;
	private List<CardServiceItem> services;

	public String getCard_id() {
		return card_id;
	}

	public void setCard_id(String card_id) {
		this.card_id = card_id;
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

	public List<CardServiceItem> getServices() {
		return services;
	}

	public void setServices(List<CardServiceItem> services) {
		this.services = services;
	}

	public String getEffectiveTime() {
		return effectiveTime;
	}

	public void setEffectiveTime(String effectiveTime) {
		this.effectiveTime = effectiveTime;
	}

	public String getEffectiveDay() {
		return effectiveDay;
	}

	public void setEffectiveDay(String effectiveDay) {
		this.effectiveDay = effectiveDay;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(String serviceTime) {
		this.serviceTime = serviceTime;
	}

}
