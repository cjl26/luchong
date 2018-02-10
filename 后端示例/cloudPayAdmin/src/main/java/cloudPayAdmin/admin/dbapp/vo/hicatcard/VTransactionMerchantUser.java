package cloudPayAdmin.admin.dbapp.vo.hicatcard;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import cloudPayAdmin.util.TimeUtil;
import cloudPayAdmin.util.annotation.StringDateFormat;

@Entity
@Table(name = "v_transaction_merchant_user")
public class VTransactionMerchantUser {
	
	@Id
	@Column(name = "TRANSACTION_ID")
	private Long transactionId;
	
	@Column(name = "TRANSACTION_ORDER")
	private String transactionOrder;
	
	@Column(name = "MERCHANT_ID")
	private Long merchantId;
	
	@Column(name = "USER_ID")
	private Long userId;
	
	@Column(name = "USER_CARD_ID")
	private Long userCardId;
	
	@Column(name = "CAR_LICENCE")
	private String carLicence;
	
	@StringDateFormat(oriFormat = TimeUtil.DATE_PATTERN_NOSEPARTOR, destFormat = TimeUtil.DEFAULT_DATE_PATTERN)
	@Column(name = "CREATE_TIME")
	private String createTime;
	
	@StringDateFormat(oriFormat = TimeUtil.DATE_PATTERN_NOSEPARTOR, destFormat = TimeUtil.DEFAULT_DATE_PATTERN)
	@Column(name = "UPDATE_TIME")
	private String updateTime;
	
	@Column(name = "ENABLE")
	private boolean enable = Boolean.TRUE;
	
	@Column(name = "PHONE")
	private String phone;
	
	@Column(name = "NICKNAME")
	private String nickName;
	
	@Column(name = "MERCHANT_NUMBER")
	private String merchantNumber;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "SERVICE_TIME")
	private Integer serviceTime;
	
	@Column(name = "SERVICE_NAME")
	private String serviceName;
	
	

	public Integer getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(Integer serviceTime) {
		this.serviceTime = serviceTime;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public String getTransactionOrder() {
		return transactionOrder;
	}

	public void setTransactionOrder(String transactionOrder) {
		this.transactionOrder = transactionOrder;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getUserCardId() {
		return userCardId;
	}

	public void setUserCardId(Long userCardId) {
		this.userCardId = userCardId;
	}

	public String getCarLicence() {
		return carLicence;
	}

	public void setCarLicence(String carLicence) {
		this.carLicence = carLicence;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
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
	
	
	
	
}
