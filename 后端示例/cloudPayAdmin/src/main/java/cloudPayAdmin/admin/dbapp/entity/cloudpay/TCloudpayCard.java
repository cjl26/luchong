package cloudPayAdmin.admin.dbapp.entity.cloudpay;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import cloudPayAdmin.util.TimeUtil;
import cloudPayAdmin.util.annotation.StringDateFormat;

//@Entity
//@Table(name = "T_CLOUDPAY_CARD")
//@SequenceGenerator(name = "SEQ_CLOUDPAY_CARD", sequenceName = "SEQ_CLOUDPAY_CARD", allocationSize = 1, initialValue = 1)
public class TCloudpayCard {

	public static final Integer STATUS_EFFECITIVE = 1;
	public static final String STATUS_EFFECITIVE_MSG = "有效";
	public static final Integer STATUS_UNEFFECITIVE = 0;
	public static final String STATUS_UNEFFECITIVE_MSG = "无效";
	
	public static final Integer AVAILABLE_EXIST = 1;
	public static final String AVAILABLE_EXIST_MSG = "绑定";
	public static final Integer AVAILABLE_DELETED = 0;
	public static final String AVAILABLE_DELETED_MSG = "解绑";
	
	
	public static Map<String,String> initAvailableMap() {
		Map<String, String> availableMap = new HashMap<String, String>();
		availableMap.put(TCloudpayCard.AVAILABLE_EXIST.toString(), TCloudpayCard.AVAILABLE_EXIST_MSG);
		availableMap.put(TCloudpayCard.AVAILABLE_DELETED.toString(), TCloudpayCard.AVAILABLE_DELETED_MSG);
		return availableMap;
	}
	
	public static Map<String, String> initStatusMap() {
		Map<String, String> statusMap = new HashMap<String, String>();
		statusMap.put(TCloudpayCard.STATUS_EFFECITIVE.toString(), TCloudpayCard.STATUS_EFFECITIVE_MSG);
		statusMap.put(TCloudpayCard.STATUS_UNEFFECITIVE.toString(), TCloudpayCard.STATUS_UNEFFECITIVE_MSG);
		return statusMap;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CLOUDPAY_CARD")
	@Column(name = "ID")
	private Long id;

	@Column(name = "USER_ID")
	private Long userId;

	@Column(name = "CARD_NUM")
	private String cardNum;

	@Column(name = "APP_ID")
	private String appId;

	@Column(name = "STATUS")
	private Integer status;

	@Column(name = "REMARK")
	private String remark;
	
	@StringDateFormat(oriFormat=TimeUtil.DATE_PATTERN_NOSEPARTOR,destFormat=TimeUtil.DEFAULT_DATE_PATTERN)
	@Column(name = "CREATE_TIME")
	private String createTime;
	
	@StringDateFormat(oriFormat=TimeUtil.DATE_PATTERN_NOSEPARTOR,destFormat=TimeUtil.DEFAULT_DATE_PATTERN)
	@Column(name = "UPDATE_TIME")
	private String updateTime;

	@Column(name = "AVAILABLE")
	private Integer available;

	@Column(name = "OPERATOR")
	private String operator;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
    

	public Integer getAvailable() {
		return available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

}
