package cloudPayAdmin.admin.dbapp.entity.cloudpay;

import java.util.HashMap;
import java.util.Map;

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
//@Table(name = "T_CLOUDPAY_BLACK_HIS")
//@SequenceGenerator(name = "SEQ_CLOUDPAY_BLACK_HIS", sequenceName = "SEQ_CLOUDPAY_BLACK_HIS", allocationSize = 1, initialValue = 1)
public class TCloudpayBlackHis {
	
	public static final Integer OP_TYPE_ADD = 1;
	public static final String OP_TYPE_ADD_MSG = "加入";
	public static final Integer OP_TYPE_REMOVE = 0;
	public static final String OP_TYPE_REMOVE_MSG = "移出";
	
	public static final Integer SOURCE_FROM_USER = 1;
	public static final String SOURCE_FROM_USER_MSG = "用户操作";
	public static final Integer SOURCE_FROM_SYSTEM = 2;
	public static final String SOURCE_FROM_SYSTEM_MSG = "系统风控";
	
	public static final Integer SYNC_NOT_SENT = 0;
	public static final String SYNC_NOT_SENT_MSG = "未同步";
	public static final Integer SYNC_SENT = 1;
	public static final String SYNC_SENT_MSG = "已同步";
	

	
	//操作類型
	public static Map<String, String> initOpTypeMap() {
		Map<String, String> opTypeMap = new HashMap<String, String>();
		opTypeMap.put(OP_TYPE_ADD.toString(), OP_TYPE_ADD_MSG);
		opTypeMap.put(OP_TYPE_REMOVE.toString(), OP_TYPE_REMOVE_MSG);
		return opTypeMap;
	}
	
	//來源
	public static Map<String, String> initSourceMap() {
		Map<String, String> sourceMap = new HashMap<String, String>();
		sourceMap.put(SOURCE_FROM_USER.toString(), SOURCE_FROM_USER_MSG);
		sourceMap.put(SOURCE_FROM_SYSTEM.toString(), SOURCE_FROM_SYSTEM_MSG);
		return sourceMap;
	}
	
	//同步
	public static Map<String, String> initSyncMap() {
		Map<String, String> syncMap = new HashMap<String, String>();
		syncMap.put(SYNC_NOT_SENT.toString(), SYNC_NOT_SENT_MSG);
		syncMap.put(SYNC_SENT.toString(), SYNC_SENT_MSG);
		return syncMap;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CLOUDPAY_BLACK_HIS")
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "USER_ID")
	private Long userId;
	
	@Column(name = "CARD_NUM")
	private String cardNum;	

	@Column(name = "APP_ID")
	private String appId;

	@Column(name = "OP_TYPE")
	private Integer opType;

	@Column(name = "REMARK")
	private String remark;
	
	@StringDateFormat(oriFormat=TimeUtil.DATE_PATTERN_NOSEPARTOR,destFormat=TimeUtil.DEFAULT_DATE_PATTERN)
	@Column(name = "CREATE_TIME")
	private String createTime;

	@Column(name = "OPERATOR")
	private String operator;
	
	@Column(name = "SOURCE")
	private Integer source;
	
	@Column(name = "SYNC")
	private Integer sync;

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

	public Integer getOpType() {
		return opType;
	}

	public void setOpType(Integer opType) {
		this.opType = opType;
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

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public Integer getSync() {
		return sync;
	}

	public void setSync(Integer sync) {
		this.sync = sync;
	}	
}
