package cloudPayAdmin.admin.dbapp.vo.hicatcard;

import cloudPayAdmin.util.TimeUtil;
import cloudPayAdmin.util.annotation.StringDateFormat;

public class UserCardDonationVo {
	
	private Long userCardTransferId;
	
	private Long sourceUserCardId;
	
	private Long sourceUserId;
	
	private String sourceNickname;
	
	private String sourcePhone;
	
	private Long targetUserCardId;
	
	private Long targetUserId;
	
	private String targetNickname;
	
	private String targetPhone;
	
	private String targetUserCardNumber;
	
	@StringDateFormat(oriFormat=TimeUtil.DATE_PATTERN_NOSEPARTOR,destFormat=TimeUtil.DEFAULT_DATE_PATTERN)
	private String endTime;
	
	private String status;
	
	@StringDateFormat(oriFormat=TimeUtil.DATE_PATTERN_NOSEPARTOR,destFormat=TimeUtil.DEFAULT_DATE_PATTERN)
	private String createTime;
	
	
	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTargetUserCardNumber() {
		return targetUserCardNumber;
	}

	public void setTargetUserCardNumber(String targetUserCardNumber) {
		this.targetUserCardNumber = targetUserCardNumber;
	}

	public Long getUserCardTransferId() {
		return userCardTransferId;
	}

	public void setUserCardTransferId(Long userCardTransferId) {
		this.userCardTransferId = userCardTransferId;
	}

	public Long getSourceUserCardId() {
		return sourceUserCardId;
	}

	public void setSourceUserCardId(Long sourceUserCardId) {
		this.sourceUserCardId = sourceUserCardId;
	}

	public Long getSourceUserId() {
		return sourceUserId;
	}

	public void setSourceUserId(Long sourceUserId) {
		this.sourceUserId = sourceUserId;
	}

	public String getSourceNickname() {
		return sourceNickname;
	}

	public void setSourceNickname(String sourceNickname) {
		this.sourceNickname = sourceNickname;
	}

	public String getSourcePhone() {
		return sourcePhone;
	}

	public void setSourcePhone(String sourcePhone) {
		this.sourcePhone = sourcePhone;
	}

	public Long getTargetUserCardId() {
		return targetUserCardId;
	}

	public void setTargetUserCardId(Long targetUserCardId) {
		this.targetUserCardId = targetUserCardId;
	}

	public Long getTargetUserId() {
		return targetUserId;
	}

	public void setTargetUserId(Long targetUserId) {
		this.targetUserId = targetUserId;
	}

	public String getTargetNickname() {
		return targetNickname;
	}

	public void setTargetNickname(String targetNickname) {
		this.targetNickname = targetNickname;
	}

	public String getTargetPhone() {
		return targetPhone;
	}

	public void setTargetPhone(String targetPhone) {
		this.targetPhone = targetPhone;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
	
	
	
	
}
