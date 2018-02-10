package cloudPayAdmin.admin.dbapp.vo.hicatcard;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cloudPayAdmin.util.TimeUtil;
import cloudPayAdmin.util.annotation.StringDateFormat;

@Entity
@Table(name = "v_user_coupon")
public class VUserCoupon {

	@Id
	@Column(name = "USER_COUPON_ID")
	private Long userCouponId;

	@Column(name = "USER_ID")
	private Long userId;

	@Column(name = "COUPON_ID")
	private Long couponId;

	@Column(name = "USER_COUPON_NUMBER")
	private String userCouponNumber;

	@Column(name = "STATUS")
	private String status;
	
	@StringDateFormat(oriFormat=TimeUtil.DATE_PATTERN_NOSEPARTOR,destFormat=TimeUtil.DEFAULT_DATE_PATTERN)
	@Column(name = "CREATE_TIME")
	private String createTime;

	@StringDateFormat(oriFormat=TimeUtil.DATE_PATTERN_NOSEPARTOR,destFormat=TimeUtil.DEFAULT_DATE_PATTERN)
	@Column(name = "UPDATE_TIME")
	private String updateTime;
	
	@StringDateFormat(oriFormat=TimeUtil.DATE_PATTERN_NOSEPARTOR,destFormat=TimeUtil.DEFAULT_DATE_PATTERN)
	@Column(name = "USE_TIME")
	private String useTime;

	@Column(name = "ENABLE")
	private boolean enable = Boolean.TRUE;

	@Column(name = "PHONE")
	private String phone;

	@Column(name = "NICKNAME")
	private String nickname;

	@Column(name = "COUPON_NAME")
	private String couponName;

	@Column(name = "FEE")
	private Integer fee;

	@StringDateFormat(oriFormat=TimeUtil.DATE_PATTERN_NOSEPARTOR,destFormat=TimeUtil.DEFAULT_DATE_PATTERN)
	@Column(name = "START_TIME")
	private String startTime;

	@StringDateFormat(oriFormat=TimeUtil.DATE_PATTERN_NOSEPARTOR,destFormat=TimeUtil.DEFAULT_DATE_PATTERN)
	@Column(name = "END_TIME")
	private String endTime;

	public Long getUserCouponId() {
		return userCouponId;
	}

	public void setUserCouponId(Long userCouponId) {
		this.userCouponId = userCouponId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCouponId() {
		return couponId;
	}

	public void setCouponId(Long couponId) {
		this.couponId = couponId;
	}

	public String getUserCouponNumber() {
		return userCouponNumber;
	}

	public void setUserCouponNumber(String userCouponNumber) {
		this.userCouponNumber = userCouponNumber;
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

	public String getUseTime() {
		return useTime;
	}

	public void setUseTime(String useTime) {
		this.useTime = useTime;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public Integer getFee() {
		return fee;
	}

	public void setFee(Integer fee) {
		this.fee = fee;
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

}
