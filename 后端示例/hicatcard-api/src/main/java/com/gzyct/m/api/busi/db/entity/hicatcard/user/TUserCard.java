package com.gzyct.m.api.busi.db.entity.hicatcard.user;

import com.gzyct.m.api.busi.util.CommonConvertor;

import javax.persistence.*;

@Entity
@Table(name = "t_user_card")
public class TUserCard {

	public static String SOURCE_PURCHASE = "1"; // 购买
	// public static String SOURCE_SYSTEM_PRESENT = "2"; // 系统赠送
	public static String SOURCE_PRESENT = "2"; // 用户+系统赠送

	static public boolean sourceValidate(String sourceInput) {
		if (CommonConvertor.convertStringAvoidNull(sourceInput).equalsIgnoreCase(SOURCE_PURCHASE)
				|| CommonConvertor.convertStringAvoidNull(sourceInput).equalsIgnoreCase(SOURCE_PRESENT)) {
			return true;
		}
		return false;
	}

	public static String STATUS_NOT_ACTIVE = "1"; // 未激活
	public static String STATUS_ACTIVE = "2"; // 已经激活
	public static String STATUS_PRESENTED = "3"; // 已经赠送出去
	public static String STATUS_FINISH = "4"; // 已经用完

	static public boolean statusValidate(String statusInput) {
		if (CommonConvertor.convertStringAvoidNull(statusInput).equalsIgnoreCase(STATUS_NOT_ACTIVE)
				|| CommonConvertor.convertStringAvoidNull(statusInput).equalsIgnoreCase(STATUS_ACTIVE)
				|| CommonConvertor.convertStringAvoidNull(statusInput).equalsIgnoreCase(STATUS_PRESENTED)) {
			return true;
		}
		return false;
	}

	/**
	 * status转换
	 * 
	 * @param statusInput
	 * @return
	 */
	static public String getStatusDetial(String statusInput) {
		String statusResult = "";
		if (statusInput.equals(STATUS_NOT_ACTIVE)) {
			statusResult = "未激活";
		} else if (statusInput.equals(STATUS_ACTIVE)) {
			statusResult = "已激活";
		} else if (statusInput.equals(STATUS_PRESENTED)) {
			statusResult = "已赠送";
		}
		return statusResult;
	}

	private Long userCardId;
	private Long userId;
	private Long cardId;
	private String endTime;
	private String userCardNumber;
	private String source;
	private Long sourceUserId;
	private String status;
	private String createTime;
	private String updateTime;
	private Boolean enable;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_CARD_ID")
	public Long getUserCardId() {
		return userCardId;
	}

	public void setUserCardId(Long userCardId) {
		this.userCardId = userCardId;
	}

	@Column(name = "USER_ID")
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "CARD_ID")
	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	@Column(name = "USER_CARD_NUMBER")
	public String getUserCardNumber() {
		return userCardNumber;
	}

	public void setUserCardNumber(String userCardNumber) {
		this.userCardNumber = userCardNumber;
	}

	@Column(name = "SOURCE")
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Column(name = "SOURCE_USER_ID")
	public Long getSourceUserId() {
		return sourceUserId;
	}

	public void setSourceUserId(Long sourceUserId) {
		this.sourceUserId = sourceUserId;
	}

	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "CREATE_TIME")
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "UPDATE_TIME")
	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "ENABLE")
	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	@Column(name = "END_TIME")
	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
