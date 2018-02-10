package cloudPayAdmin.admin.dbapp.vo.hicatcard;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import cloudPayAdmin.util.TimeUtil;
import cloudPayAdmin.util.annotation.StringDateFormat;

@Entity
@Table(name = "v_system_present_card")
public class VSystemPresentCard {
	
	@Id
	@Column(name = "SYSTEM_PRESENT_CARD_ID")
	private Long systemPresentCardId;
	
	@Column(name = "CARD_ID")
	private Long cardId;
	
	@Column(name = "BATCH")
	private Long batch;
	
	@Column(name = "CARD_NUMBER")
	private String cardNumber;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "CREATOR")
	private String creator;
	
	@Column(name = "QRCODE_PATH")
	public String qrCodePath;
	
	@StringDateFormat(oriFormat=TimeUtil.DATE_PATTERN_NOSEPARTOR,destFormat=TimeUtil.DEFAULT_DATE_PATTERN)
	@Column(name = "CREATE_TIME")
	private String createTime;
	
	@StringDateFormat(oriFormat=TimeUtil.DATE_PATTERN_NOSEPARTOR,destFormat=TimeUtil.DEFAULT_DATE_PATTERN)
	@Column(name = "UPDATE_TIME")
	private String updateTime;
	
	@Column(name = "ENABLE")
	private boolean enable = Boolean.TRUE;
	
	@Column(name = "CARD_NAME")
	private String cardName;
	
	@StringDateFormat(oriFormat=TimeUtil.DATE_PATTERN_NOSEPARTOR,destFormat=TimeUtil.DEFAULT_DATE_PATTERN)
	@Transient
	private String receiverEndTime;
	
	@Transient
	private String receiverNickname;   //接收人昵称
	
	@Transient
	private String receiverPhone;     //接收人电话
	
	@Transient
	private String receiverStatus;    //接收人状态
	
	@Transient
	private String receiverUserCardNumber;
	
	public String getReceiverUserCardNumber() {
		return receiverUserCardNumber;
	}

	public void setReceiverUserCardNumber(String receiverUserCardNumber) {
		this.receiverUserCardNumber = receiverUserCardNumber;
	}

	public String getReceiverEndTime() {
		return receiverEndTime;
	}

	public void setReceiverEndTime(String receiverEndTime) {
		this.receiverEndTime = receiverEndTime;
	}

	public Long getBatch() {
		return batch;
	}

	public void setBatch(Long batch) {
		this.batch = batch;
	}

	public Long getSystemPresentCardId() {
		return systemPresentCardId;
	}

	public void setSystemPresentCardId(Long systemPresentCardId) {
		this.systemPresentCardId = systemPresentCardId;
	}

	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getQrCodePath() {
		return qrCodePath;
	}

	public void setQrCodePath(String qrCodePath) {
		this.qrCodePath = qrCodePath;
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

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getReceiverNickname() {
		return receiverNickname;
	}

	public void setReceiverNickname(String receiverNickname) {
		this.receiverNickname = receiverNickname;
	}

	public String getReceiverPhone() {
		return receiverPhone;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}

	public String getReceiverStatus() {
		return receiverStatus;
	}

	public void setReceiverStatus(String receiverStatus) {
		this.receiverStatus = receiverStatus;
	}
	
	
}
