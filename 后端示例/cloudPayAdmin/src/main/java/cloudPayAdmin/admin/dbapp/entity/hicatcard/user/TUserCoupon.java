package cloudPayAdmin.admin.dbapp.entity.hicatcard.user;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cloudPayAdmin.util.TimeUtil;
import cloudPayAdmin.util.annotation.StringDateFormat;

@Entity
@Table(name = "t_user_coupon")
public class TUserCoupon {

	public static final String STATUS_HAS_USE = "1";
	public static final String STATUS_HAS_USE_MSG = "已用";
	public static final String STATUS_NOT_USE = "0";
	public static final String STATUS_NOT_USE_MSG = "未用";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@Column(name = "USE_TIME")
	private String useTime;

	@Column(name = "ENABLE")
	private boolean enable = Boolean.TRUE;

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

	public static String getStatusNotUseMsg() {
		return STATUS_NOT_USE_MSG;
	}

	// 状态
	public static Map<String, String> initStatusMap() {
		Map<String, String> statusMap = new LinkedHashMap<String, String>();
		statusMap.put(STATUS_HAS_USE, STATUS_HAS_USE_MSG);
		statusMap.put(STATUS_NOT_USE, STATUS_NOT_USE_MSG);
		return statusMap;
	}
}
