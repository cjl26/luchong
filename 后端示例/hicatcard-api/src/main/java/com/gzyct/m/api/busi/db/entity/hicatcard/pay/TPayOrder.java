package com.gzyct.m.api.busi.db.entity.hicatcard.pay;

import com.gzyct.m.api.busi.db.entity.hicatcard.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "t_pay_order")
public class TPayOrder extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PAY_ORDER_ID")
	private Long payOrderId;

	@Column(name = "PAY_ORDER_NUMBER")
	private String payOrderNumber;

	@Column(name = "USER_ID")
	private Long userId;

	@Column(name = "CARD_ID")
	private Long cardId;

	@Column(name = "WX_ORDER_NUMBER")
	private String wxOrderNumber;

	@Column(name = "USER_CARD_ID")
	private Long userCardId;

	@Column(name = "USER_COUPON_ID")
	private Long userCouponId;

	@Column(name = "TOTAL_FEE")
	private Integer totalFee;

	@Column(name = "COUPON_FEE")
	private Integer couponFee;

	@Column(name = "PAY_FEE")
	private Integer payFee;

	@Column(name = "REMARK")
	private String remark;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "CREATE_TIME")
	private String createTime;

	@Column(name = "UPDATE_TIME")
	private String updateTime;

	@Column(name = "PREPAY_ID")
	private String prepayId;

	public Long getPayOrderId() {
		return payOrderId;
	}

	public void setPayOrderId(Long payOrderId) {
		this.payOrderId = payOrderId;
	}

	public String getPayOrderNumber() {
		return payOrderNumber;
	}

	public void setPayOrderNumber(String payOrderNumber) {
		this.payOrderNumber = payOrderNumber;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	public String getWxOrderNumber() {
		return wxOrderNumber;
	}

	public void setWxOrderNumber(String wxOrderNumber) {
		this.wxOrderNumber = wxOrderNumber;
	}

	public Long getUserCardId() {
		return userCardId;
	}

	public void setUserCardId(Long userCardId) {
		this.userCardId = userCardId;
	}

	public Long getUserCouponId() {
		return userCouponId;
	}

	public void setUserCouponId(Long userCouponId) {
		this.userCouponId = userCouponId;
	}

	public Integer getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Integer totalFee) {
		this.totalFee = totalFee;
	}

	public Integer getCouponFee() {
		return couponFee;
	}

	public void setCouponFee(Integer couponFee) {
		this.couponFee = couponFee;
	}

	public Integer getPayFee() {
		return payFee;
	}

	public void setPayFee(Integer payFee) {
		this.payFee = payFee;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getPrepayId() {
		return prepayId;
	}

	public void setPrepayId(String prepayId) {
		this.prepayId = prepayId;
	}

}
