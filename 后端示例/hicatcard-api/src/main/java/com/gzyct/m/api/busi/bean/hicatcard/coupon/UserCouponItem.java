package com.gzyct.m.api.busi.bean.hicatcard.coupon;

public class UserCouponItem {

	private Long user_coupon_id;
	private String user_coupon_number;
	private Long user_id;
	private Long coupon_id;
	private String coupon_name;
	private String type;
	private Long card_id;
	private Integer fee;
	private String picture_url;
	private String detail;
	private String status;
	private String create_time;
	private String update_time;
	private String use_time;

	public Long getUser_coupon_id() {
		return user_coupon_id;
	}

	public void setUser_coupon_id(Long user_coupon_id) {
		this.user_coupon_id = user_coupon_id;
	}

	public String getUser_coupon_number() {
		return user_coupon_number;
	}

	public void setUser_coupon_number(String user_coupon_number) {
		this.user_coupon_number = user_coupon_number;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Long getCoupon_id() {
		return coupon_id;
	}

	public void setCoupon_id(Long coupon_id) {
		this.coupon_id = coupon_id;
	}

	public String getCoupon_name() {
		return coupon_name;
	}

	public void setCoupon_name(String coupon_name) {
		this.coupon_name = coupon_name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getCard_id() {
		return card_id;
	}

	public void setCard_id(Long card_id) {
		this.card_id = card_id;
	}

	public Integer getFee() {
		return fee;
	}

	public void setFee(Integer fee) {
		this.fee = fee;
	}

	public String getPicture_url() {
		return picture_url;
	}

	public void setPicture_url(String picture_url) {
		this.picture_url = picture_url;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getUse_time() {
		return use_time;
	}

	public void setUse_time(String use_time) {
		this.use_time = use_time;
	}

}
