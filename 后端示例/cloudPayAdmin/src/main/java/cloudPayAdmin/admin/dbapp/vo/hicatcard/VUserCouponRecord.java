package cloudPayAdmin.admin.dbapp.vo.hicatcard;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import cloudPayAdmin.util.TimeUtil;
import cloudPayAdmin.util.annotation.StringDateFormat;

@Entity
@Table(name = "v_user_coupon_record")
public class VUserCouponRecord {
	
	@Id
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
	
	@Column(name = "MERCHANT_NUMBER")
	private String merchantNumber;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "PHONE")
	private String phone;
	
	@Column(name = "NICKNAME")
	private String nickname;
	
	@Column(name = "CARD_ID")
	private Long cardId;
	
	@Column(name = "CARD_NAME")
	private String cardName;
	
	@Column(name = "USER_CARD_NUMBER")
	private String userCardNumber;
	
	@Column(name = "COUPON_ID")
	private Long couponId;
	
	@Column(name = "COUPON_NAME")
	private String couponName;
	
	@Column(name = "USER_COUPON_NUMBER")
	private String userCouponNumber;

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

	public String getMerchantNumber() {
		return merchantNumber;
	}

	public void setMerchantNumber(String merchantNumber) {
		this.merchantNumber = merchantNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getUserCardNumber() {
		return userCardNumber;
	}

	public void setUserCardNumber(String userCardNumber) {
		this.userCardNumber = userCardNumber;
	}

	public Long getCouponId() {
		return couponId;
	}

	public void setCouponId(Long couponId) {
		this.couponId = couponId;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public String getUserCouponNumber() {
		return userCouponNumber;
	}

	public void setUserCouponNumber(String userCouponNumber) {
		this.userCouponNumber = userCouponNumber;
	}
	
	
	
	
	
}
