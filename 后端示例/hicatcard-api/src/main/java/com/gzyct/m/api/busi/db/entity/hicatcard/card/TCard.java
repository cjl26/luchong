package com.gzyct.m.api.busi.db.entity.hicatcard.card;

import com.gzyct.m.api.busi.db.entity.hicatcard.BaseEntity;
import com.gzyct.m.api.busi.util.TimeUtil;
import com.gzyct.m.api.busi.util.annotation.StringDateFormat;

import javax.persistence.*;

@Entity
@Table(name = "t_card")
public class TCard extends BaseEntity {

	public static final String STATUS_SHOW = "1"; // 显示
	public static final String STATUS_HIDE = "0"; // 隐藏

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CARD_ID")
	private Long cardId;

	@Column(name = "CARD_NAME")
	private String cardName;

	@Column(name = "TYPE")
	private String type;

	@Column(name = "PICTURE_URL")
	private String pictureUlr;

	@Column(name = "ORDERNUM")
	private Long ordernum;

	@Column(name = "EFFECTIVE_DAY")
	private Integer effectiveDay;

	@Column(name = "FEE")
	private Integer fee;

	@Column(name = "COUPON_ID")
	private Long couponId;

	@Column(name = "DETAIL")
	private String detail;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "CREATOR")
	private String creator;

	@StringDateFormat(oriFormat = TimeUtil.DATE_PATTERN_NOSEPARTOR, destFormat = TimeUtil.DEFAULT_DATE_PATTERN)
	@Column(name = "CREATE_TIME")
	private String createTime;

	@Column(name = "UPDATE_TIME")
	private String updateTime;

	@Column(name = "ENABLE")
	private boolean enable = Boolean.TRUE;

	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPictureUlr() {
		return pictureUlr;
	}

	public void setPictureUlr(String pictureUlr) {
		this.pictureUlr = pictureUlr;
	}

	public Integer getFee() {
		return fee;
	}

	public void setFee(Integer fee) {
		this.fee = fee;
	}

	public Long getCouponId() {
		return couponId;
	}

	public void setCouponId(Long couponId) {
		this.couponId = couponId;
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

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
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

	public Long getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(Long ordernum) {
		this.ordernum = ordernum;
	}

	public Integer getEffectiveDay() {
		return effectiveDay;
	}

	public void setEffectiveDay(Integer effectiveDay) {
		this.effectiveDay = effectiveDay;
	}

}
