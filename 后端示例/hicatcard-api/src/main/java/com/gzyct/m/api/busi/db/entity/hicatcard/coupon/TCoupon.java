package com.gzyct.m.api.busi.db.entity.hicatcard.coupon;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gzyct.m.api.busi.db.entity.hicatcard.BaseEntity;
import com.gzyct.m.api.busi.util.CommonConvertor;

@Entity
@Table(name = "t_coupon")
public class TCoupon extends BaseEntity {

	public static String TYPE_ALL = "1"; // 全部可用
	public static String TYPE_ONE = "2"; // 单一可用

	// 类型检查
	public static Boolean typeValidate(String typeStatus) {
		if (CommonConvertor.convertStringAvoidNull(typeStatus).equalsIgnoreCase(TYPE_ALL)
				|| CommonConvertor.convertStringAvoidNull(typeStatus).equalsIgnoreCase(TYPE_ONE)) {
			return true;
		}
		return false;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COUPON_ID")
	private Long couponId;

	@Column(name = "COUPON_NAME")
	private String couponName;

	@Column(name = "TYPE")
	private String type;

	@Column(name = "CARD_ID")
	private String cardId;

	@Column(name = "FEE")
	private Integer fee;

	@Column(name = "PICTURE_URL")
	private String picturUrl;

	@Column(name = "DETAIL")
	private String detail;

	@Column(name = "START_TIME")
	private String startTime;

	@Column(name = "END_TIME")
	private String endTime;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "CREATOR")
	private String creator;

	@Column(name = "CREATE_TIME")
	private String createTime;

	@Column(name = "UPDATE_TIME")
	private String updateTime;

	@Column(name = "ENABLE")
	private boolean enable = Boolean.TRUE;

	public Long getCouponId() {
		return couponId;
	}

	public void setCouponId(Long couponId) {
		this.couponId = couponId;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public Integer getFee() {
		return fee;
	}

	public void setFee(Integer fee) {
		this.fee = fee;
	}

	public String getPicturUrl() {
		return picturUrl;
	}

	public void setPicturUrl(String picturUrl) {
		this.picturUrl = picturUrl;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

}
