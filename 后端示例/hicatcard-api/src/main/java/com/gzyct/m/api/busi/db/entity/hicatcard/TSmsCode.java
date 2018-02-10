package com.gzyct.m.api.busi.db.entity.hicatcard;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gzyct.m.api.busi.util.CommonConvertor;

@Entity
@Table(name = "t_sms_code")
public class TSmsCode extends BaseEntity {

	// 卡片激活
	public static final String SMS_CODE_REQ_TYPE_CARD_ACTIVATE = "1";

	// 类型检查
	public static Boolean typeValidate(String inputType) {
		if (CommonConvertor.convertStringAvoidNull(inputType).equalsIgnoreCase(SMS_CODE_REQ_TYPE_CARD_ACTIVATE)) {
			return true;
		}
		return false;
	}

	private Long smsCodeId;
	private String phone;
	private String type;
	private String code;
	private String expireTime;
	private String createTime;
	private String updateTime;
	private Boolean enable;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SMS_CODE_ID")
	public Long getSmsCodeId() {
		return smsCodeId;
	}

	public void setSmsCodeId(Long smsCodeId) {
		this.smsCodeId = smsCodeId;
	}

	@Column(name = "PHONE")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "TYPE")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "CODE")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "EXPIRE_TIME")
	public String getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
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
}
