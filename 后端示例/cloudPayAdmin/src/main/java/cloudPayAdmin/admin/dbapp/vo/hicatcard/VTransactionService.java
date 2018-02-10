package cloudPayAdmin.admin.dbapp.vo.hicatcard;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import cloudPayAdmin.util.TimeUtil;
import cloudPayAdmin.util.annotation.StringDateFormat;

@Entity
@Table(name = "v_transaction_service")
public class VTransactionService {
	
	@Id
	@Column(name = "TRANSACTION_SERVICE_ID")
	private Long transactionServiceId;
	
	@Column(name = "TRANSACTION_ID")
	private Long transactionId;
	
	@Column(name = "TRANSACTION_ORDER")
	private String transactionOrder;
	
	@Column(name = "SERVICE_ID")
	private Long serviceId;
	
	@Column(name = "SERVICE_NAME")
	private String serviceName;
	
	@Column(name = "SERVICE_TIME")
	private Integer serviceTime;
	
	@Column(name = "MERCHANT_FEE")
	private Integer merchantFee;
	
	@Column(name = "TIME_BEFORE")
	private Integer timeBefore;
	
	@Column(name = "TIME_AFTER")
	private Integer timeAfter;
	
	@StringDateFormat(oriFormat=TimeUtil.DATE_PATTERN_NOSEPARTOR,destFormat=TimeUtil.DEFAULT_DATE_PATTERN)
	@Column(name = "CREATE_TIME")
	private String createTime;
	
	@StringDateFormat(oriFormat=TimeUtil.DATE_PATTERN_NOSEPARTOR,destFormat=TimeUtil.DEFAULT_DATE_PATTERN)
	@Column(name = "UPDATE_TIME")
	private String updateTime;
	
	@Column(name = "ENABLE")
	private boolean enable = Boolean.TRUE;
	
	@Column(name = "PHONE")
	private String phone;
	
	@Column(name = "NICKNAME")
	private String nickname;
	
	@Column(name = "MERCHANT_NUMBER")
	private String merchantNumber;
	
	@Column(name = "NAME") 
	private String name;   //服务名称
	
	@Column(name = "CAR_LICENCE")
	private String carLicence;
	
	@Column(name = "CARD_ID")
	private Long cardId;
	
	@Column(name = "USER_CARD_NUMBER")
	private String userCardNumber;
	
	public Integer getMerchantFee() {
		return merchantFee;
	}

	public void setMerchantFee(Integer merchantFee) {
		this.merchantFee = merchantFee;
	}

	public Long getTransactionServiceId() {
		return transactionServiceId;
	}

	public void setTransactionServiceId(Long transactionServiceId) {
		this.transactionServiceId = transactionServiceId;
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

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Integer getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(Integer serviceTime) {
		this.serviceTime = serviceTime;
	}

	public Integer getTimeBefore() {
		return timeBefore;
	}

	public void setTimeBefore(Integer timeBefore) {
		this.timeBefore = timeBefore;
	}

	public Integer getTimeAfter() {
		return timeAfter;
	}

	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	public String getUserCardNumber() {
		return userCardNumber;
	}

	public void setUserCardNumber(String userCardNumber) {
		this.userCardNumber = userCardNumber;
	}

	public void setTimeAfter(Integer timeAfter) {
		this.timeAfter = timeAfter;
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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

	public String getCarLicence() {
		return carLicence;
	}

	public void setCarLicence(String carLicence) {
		this.carLicence = carLicence;
	}
	
	
}
