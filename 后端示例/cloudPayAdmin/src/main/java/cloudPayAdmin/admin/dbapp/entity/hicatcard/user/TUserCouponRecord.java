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
@Table(name = "t_user_coupon_record")
public class TUserCouponRecord {
	
	public static final String TYPE_BUYCARD_COUPON = "1";  // 线上抵扣购卡
	public static final String TYPE_BUYCARD_COUPON_MSG = "线上抵扣购卡";
	public static final String TYPE_WASHCAR_COUPON = "2"; //线下洗车减免
	public static final String TYPE_WASHCAR_COUPON_MSG = "线下洗车减免";
	
	public static Map<String,String> initTypeMap() {
		Map<String,String> typeMap = new LinkedHashMap<String,String>();
		typeMap.put(TYPE_BUYCARD_COUPON, TYPE_BUYCARD_COUPON_MSG);
		typeMap.put(TYPE_WASHCAR_COUPON, TYPE_WASHCAR_COUPON_MSG);
		return typeMap;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_COUPON_RECORD_ID")
	private Long userCouponRecordId;
	
	@Column(name = "USER_COUPON_ID")
	private Long userCouponId;
	
	@Column(name = "TYPE")
	private String type;
	
	@Column(name = "USER_CARD_ID")
	private Long userCardId;
	
	@Column(name = "PAY_ORDER_NUMBER")
	private String payOrderNumber;
	
	@Column(name = "MERCHANT_ID")
	private Long merchantId;
	
	@Column(name = "FEE")
	private Integer fee;
	
	@StringDateFormat(oriFormat=TimeUtil.DATE_PATTERN_NOSEPARTOR,destFormat=TimeUtil.DEFAULT_DATE_PATTERN)
	@Column(name = "CREATE_TIME")
	private String createTime;
	
	@StringDateFormat(oriFormat=TimeUtil.DATE_PATTERN_NOSEPARTOR,destFormat=TimeUtil.DEFAULT_DATE_PATTERN)
	@Column(name = "UPDATE_TIME")
	private String updateTime;

	public Long getUserCouponRecordId() {
		return userCouponRecordId;
	}

	public void setUserCouponRecordId(Long userCouponRecordId) {
		this.userCouponRecordId = userCouponRecordId;
	}

	public Long getUserCouponId() {
		return userCouponId;
	}

	public void setUserCouponId(Long userCouponId) {
		this.userCouponId = userCouponId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getUserCardId() {
		return userCardId;
	}

	public void setUserCardId(Long userCardId) {
		this.userCardId = userCardId;
	}

	public String getPayOrderNumber() {
		return payOrderNumber;
	}

	public void setPayOrderNumber(String payOrderNumber) {
		this.payOrderNumber = payOrderNumber;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public Integer getFee() {
		return fee;
	}

	public void setFee(Integer fee) {
		this.fee = fee;
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
	
	
}
