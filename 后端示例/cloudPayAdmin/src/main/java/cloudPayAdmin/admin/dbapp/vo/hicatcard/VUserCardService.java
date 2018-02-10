package cloudPayAdmin.admin.dbapp.vo.hicatcard;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import cloudPayAdmin.util.TimeUtil;
import cloudPayAdmin.util.annotation.StringDateFormat;

@Entity
@Table(name = "v_user_card_service")
public class VUserCardService {

	@Id
	@Column(name = "USER_CARD_ID")
	private Long userCardId;

	@Column(name = "USER_ID")
	private Long userId;

	@Column(name = "CARD_ID")
	private Long cardId;

	@Column(name = "USER_CARD_NUMBER")
	private String userCardNumber;

	@Column(name = "SOURCE")
	private String source;

	@Column(name = "SOURCE_USER_ID")
	private Long sourceUserId;

	@Column(name = "STATUS")
	private String status;
	
	@StringDateFormat(oriFormat = TimeUtil.DATE_PATTERN_NOSEPARTOR, destFormat = TimeUtil.DEFAULT_DATE_PATTERN)
	@Column(name = "CREATE_TIME")
	private String createTime;

	@Column(name = "UPDATE_TIME")
	private String updateTime;

	@Column(name = "ENABLE")
	private Boolean enable = Boolean.TRUE;

	@Column(name = "SERVICE_NAME")
	private String serviceName;
	
	@StringDateFormat(oriFormat = TimeUtil.DATE_PATTERN_NOSEPARTOR, destFormat = TimeUtil.DEFAULT_DATE_PATTERN)
	@Column(name = "END_TIME")
	private String endTime;

	@Column(name = "PHONE")
	private String phone;

	@Column(name = "NICKNAME")
	private String nickname;

	@Column(name = "CARD_NAME")
	private String cardName;
	
	@Column(name = "serviceTime")
	private Integer serviceTime;
	
	@Transient
	private String traferTargetNickname;
	
	@Transient
	private String traferTargetPhone;
	
	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getTraferTargetNickname() {
		return traferTargetNickname;
	}

	public void setTraferTargetNickname(String traferTargetNickname) {
		this.traferTargetNickname = traferTargetNickname;
	}

	public String getTraferTargetPhone() {
		return traferTargetPhone;
	}

	public void setTraferTargetPhone(String traferTargetPhone) {
		this.traferTargetPhone = traferTargetPhone;
	}

	public Long getUserCardId() {
		return userCardId;
	}

	public void setUserCardId(Long userCardId) {
		this.userCardId = userCardId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Long getSourceUserId() {
		return sourceUserId;
	}

	public void setSourceUserId(Long sourceUserId) {
		this.sourceUserId = sourceUserId;
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

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
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

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public Integer getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(Integer serviceTime) {
		this.serviceTime = serviceTime;
	}
	
	

}
