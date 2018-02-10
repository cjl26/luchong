package cloudPayAdmin.admin.dbapp.entity.hicatcard.card;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import cloudPayAdmin.admin.dbapp.entity.hicatcard.BaseEntity;
import cloudPayAdmin.util.TimeUtil;
import cloudPayAdmin.util.annotation.StringDateFormat;

@Entity
@Table(name = "t_card")
public class TCard extends BaseEntity {
	
	public static final String STATUS_SHOW = "1";
	public static final String STATUS_SHOW_MSG = "显示";
	public static final String STATUS_NOT_SHOW = "0";
	public static final String STATUS_NOT_SHOW_MSG = "隐藏";
	
	// 状态
	public static Map<String, String> initStatusMap() {
		Map<String, String> statusMap = new LinkedHashMap<String, String>();
		statusMap.put(STATUS_SHOW, STATUS_SHOW_MSG);	
		statusMap.put(STATUS_NOT_SHOW, STATUS_NOT_SHOW_MSG);
		return statusMap;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CARD_ID")
	private Long cardId;
	
	@Column(name = "CARD_NAME")
	private String cardName;
	
	@Column(name = "TYPE")
	private String type;
	
	@Column(name = "PICTURE_URL")
	private String pictureUlr;
	
	@Column(name = "FEE")
	private Integer fee;
	
	@Column(name = "ORDERNUM")
	private Long orderNum;
	
	@Column(name = "EFFECTIVE_DAY")
	private Integer effectiveDay;
	
	@Column(name = "COUPON_ID")
	private Long couponId;
	
	@Column(name = "DETAIL")
	private String detail;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "CREATOR")
	private String creator;
	
	@StringDateFormat(oriFormat=TimeUtil.DATE_PATTERN_NOSEPARTOR,destFormat=TimeUtil.DEFAULT_DATE_PATTERN)
	@Column(name = "CREATE_TIME")
	private String createTime;
	
	@Column(name = "UPDATE_TIME")
	private String updateTime;
	
	@Column(name = "ENABLE")
	private boolean enable = Boolean.TRUE;
	
	public Integer getEffectiveDay() {
		return effectiveDay;
	}

	public void setEffectiveDay(Integer effectiveDay) {
		this.effectiveDay = effectiveDay;
	}

	public Long getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Long orderNum) {
		this.orderNum = orderNum;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPictureUlr() {
		return pictureUlr;
	}

	public void setPictureUlr(String pictureUlr) {
		this.pictureUlr = pictureUlr;
	}

	public Integer getFee() {
		return fee;
	}

	public void setFee(Integer fee) {
		this.fee = fee;
	}

	public Long getCouponId() {
		return couponId;
	}

	public void setCouponId(Long couponId) {
		this.couponId = couponId;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
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
	
	
}
