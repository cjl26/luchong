package cloudPayAdmin.admin.dbapp.entity.hicatcard.user;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_user_card")
public class TUserCard {

	public static String SOURCE_PURCHASE = "1"; // 购买
	public static String SOURCE_SYSTEM_PRESENT = "2"; // 系统赠送
	public static String SOURCE_USER_PRESENT = "3"; // 用户赠送

	public static String STATUS_NOT_ACTIVE = "1"; // 未激活
	public static String STATUS_NOT_ACTIVE_MSG = "未激活";
	public static String STATUS_ACTIVE = "2"; // 已经激活
	public static String STATUS_ACTIVE_MSG = "已激活"; // 已经激活、
	public static String STATUS_HAS_GIVE = "3";
	public static String STATUS_HAS_GIVE_MSG = "已赠送出去";
	public static String STATUS_OVERDUE = "4";
	public static String STATUS_OVERDUE_MSG = "次数用尽";
	
	
	public static Map<String,String> initStatusMap() {
		Map<String,String> statusMap = new LinkedHashMap<String,String>();
		statusMap.put(STATUS_ACTIVE, STATUS_ACTIVE_MSG);
		statusMap.put(STATUS_NOT_ACTIVE, STATUS_NOT_ACTIVE_MSG);
		statusMap.put(STATUS_HAS_GIVE, STATUS_HAS_GIVE_MSG);
		statusMap.put(STATUS_OVERDUE, STATUS_OVERDUE_MSG);
		return statusMap;
	}

	private Long userCardId;
	private Long userId;
	private Long cardId;
	private String userCardNumber;
	private String source;
	private Long sourceUserId;
	private String endTime;
	private String status;
	private String createTime;
	private String updateTime;
	private Boolean enable = Boolean.TRUE;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_CARD_ID")
	public Long getUserCardId() {
		return userCardId;
	}

	public void setUserCardId(Long userCardId) {
		this.userCardId = userCardId;
	}
	
	
	@Column(name = "END_TIME")
	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Column(name = "USER_ID")
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "CARD_ID")
	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	@Column(name = "USER_CARD_NUMBER")
	public String getUserCardNumber() {
		return userCardNumber;
	}

	public void setUserCardNumber(String userCardNumber) {
		this.userCardNumber = userCardNumber;
	}

	@Column(name = "SOURCE")
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Column(name = "SOURCE_USER_ID")
	public Long getSourceUserId() {
		return sourceUserId;
	}

	public void setSourceUserId(Long sourceUserId) {
		this.sourceUserId = sourceUserId;
	}

	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "CREATE_TIME")
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "UPDATE_TIME")
	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "ENABLE")
	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

}
