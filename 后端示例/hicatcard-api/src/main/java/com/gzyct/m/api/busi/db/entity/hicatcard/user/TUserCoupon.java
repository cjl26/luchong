package com.gzyct.m.api.busi.db.entity.hicatcard.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gzyct.m.api.busi.util.CommonConvertor;

@Entity
@Table(name = "t_user_coupon")
public class TUserCoupon {

	public static String STATUS_NOT_USE = "1"; // 未用
	public static String STATUS_USED = "2"; // 已用

	// 类型检查
	public static Boolean statusValidate(String inputStatus) {
		if (CommonConvertor.convertStringAvoidNull(inputStatus).equalsIgnoreCase(STATUS_NOT_USE)
				|| CommonConvertor.convertStringAvoidNull(inputStatus).equalsIgnoreCase(STATUS_USED)) {
			return true;
		}
		return false;
	}

	private Long userCouponId;
	private Long userId;
	private Long couponId;
	private String userCouponNumber;
	private String status;
	private String createTime;
	private String updateTime;
	private String useTime;
	private Boolean enable;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_COUPON_ID")
	public Long getUserCouponId() {
		return userCouponId;
	}

	public void setUserCouponId(Long userCouponId) {
		this.userCouponId = userCouponId;
	}

	@Column(name = "USER_ID")
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "COUPON_ID")
	public Long getCouponId() {
		return couponId;
	}

	public void setCouponId(Long couponId) {
		this.couponId = couponId;
	}

	@Column(name = "USER_COUPON_NUMBER")
	public String getUserCouponNumber() {
		return userCouponNumber;
	}

	public void setUserCouponNumber(String userCouponNumber) {
		this.userCouponNumber = userCouponNumber;
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

	@Column(name = "USE_TIME")
	public String getUseTime() {
		return useTime;
	}

	public void setUseTime(String useTime) {
		this.useTime = useTime;
	}

}
