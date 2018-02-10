package com.gzyct.m.api.busi.db.entity.hicatcard.card;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_system_present_card")
public class TSystemPresentCard {
	public static final String  STATUS_NOT_COLLECT = "1";//未领取
	public static final String STATUS_COLLECTED = "2";//已领取

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SYSTEM_PRESENT_CARD_ID")
	private Long systemPresentCardId;

	@Column(name = "CARD_ID")
	private Long cardId;

	@Column(name = "CARD_NUMBER")
	private String cardNumber;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "CREATOR")
	private String creator;

	@Column(name = "CREATE_TIME")
	private String createTime;

	@Column(name = "UPDATE_TIME")
	private String updateTime;

	@Column(name = "ENABLE")
	private Boolean enable;

	public Long getSystemPresentCardId() {
		return systemPresentCardId;
	}

	public void setSystemPresentCardId(Long systemPresentCardId) {
		this.systemPresentCardId = systemPresentCardId;
	}

	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
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

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

}
