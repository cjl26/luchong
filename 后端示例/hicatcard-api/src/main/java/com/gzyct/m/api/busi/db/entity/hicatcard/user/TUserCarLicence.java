package com.gzyct.m.api.busi.db.entity.hicatcard.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_user_car_licence")
public class TUserCarLicence {

	// TODO
	private Long userCarLicencId;
	private Long userId;
	private String carLicenceNumber;
	private String createTime;
	private String updateTime;
	private Boolean enable;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_CAR_LICENCE_ID")
	public Long getUserCarLicencId() {
		return userCarLicencId;
	}

	public void setUserCarLicencId(Long userCarLicencId) {
		this.userCarLicencId = userCarLicencId;
	}

	@Column(name = "USER_ID")
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "CAR_LICENCE_NUMBER")
	public String getCarLicenceNumber() {
		return carLicenceNumber;
	}

	public void setCarLicenceNumber(String carLicenceNumber) {
		this.carLicenceNumber = carLicenceNumber;
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
