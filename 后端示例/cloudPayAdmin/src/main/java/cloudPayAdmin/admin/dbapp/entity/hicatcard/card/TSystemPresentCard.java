package cloudPayAdmin.admin.dbapp.entity.hicatcard.card;

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
@Table(name = "t_system_present_card")
public class TSystemPresentCard {
	
	public static final String STATUS_HAS_GET = "1";
	public static final String STATUS_HAS_GET_MSG = "未领取";
	public static final String STATUS_NOT_GET = "2";
	public static final String STATUS_NOT_GET_MSG = "已领取";
	
	public static Map<String,String> initStatusMap() {
		Map<String,String> statusMap = new LinkedHashMap<String,String>();
		statusMap.put(STATUS_HAS_GET, STATUS_HAS_GET_MSG);
		statusMap.put(STATUS_NOT_GET, STATUS_NOT_GET_MSG);
		return statusMap;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	public String getQrCodePath() {
		return qrCodePath;
	}

	public void setQrCodePath(String qrCodePath) {
		this.qrCodePath = qrCodePath;
	}
	
	
}
