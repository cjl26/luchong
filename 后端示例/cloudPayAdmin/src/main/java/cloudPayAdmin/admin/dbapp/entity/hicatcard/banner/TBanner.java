package cloudPayAdmin.admin.dbapp.entity.hicatcard.banner;

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
@Table(name = "t_banner")
public class TBanner {
	
	public final static String STATUS_SHOW = "1";
	public final static String STATUS_SHOW_MSG = "显示";
	public final static String STATUS_NO_SHOW = "0";
	public final static String STATUS_NO_SHOW_MSG = "不显示";
	
	public final static String PLACE_MAIN_PAGE = "1";
	public final static String PLACE_MAIN_PAGE_MSG = " 首页";
	public final static String PLACE_TRANSACTION_PAGE = "2";
	public final static String PLACE_TRANSACTION_PAGE_MSG = "核销页";
	
	public static Map<String,String> initPlaceMap() {
		Map<String,String> statusMap = new LinkedHashMap<String,String>();
		statusMap.put(PLACE_MAIN_PAGE, PLACE_MAIN_PAGE_MSG);
		statusMap.put(PLACE_TRANSACTION_PAGE, PLACE_TRANSACTION_PAGE_MSG);
		return statusMap;
	}
	
	public static Map<String,String> initStatusMap() {
		Map<String,String> statusMap = new LinkedHashMap<String,String>();
		statusMap.put(STATUS_SHOW, STATUS_SHOW_MSG);
		statusMap.put(STATUS_NO_SHOW, STATUS_NO_SHOW_MSG);
		return statusMap;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BANNER_ID")
	private Long bannerId;
	
	@Column(name = "TITLE")
	private String title;
	
	@Column(name = "PICTURE_URL")
	private String pictureUrl;
	
	@Column(name = "PLACE")
	private String place;
	
	@Column(name = "ORDERNUM")
	private Long orderNum;
	
	@Column(name = "STATUS")
	private String status;
	
	@StringDateFormat(oriFormat=TimeUtil.DATE_PATTERN_NOSEPARTOR,destFormat=TimeUtil.DEFAULT_DATE_PATTERN)
	@Column(name = "CREATE_TIME")
	private String createTime;
	
	@StringDateFormat(oriFormat=TimeUtil.DATE_PATTERN_NOSEPARTOR,destFormat=TimeUtil.DEFAULT_DATE_PATTERN)
	@Column(name = "UPDATE_TIME")
	private String updateTime;
	
	@Column(name = "ENABLE")
	private boolean enable = Boolean.TRUE;

	public Long getBannerId() {
		return bannerId;
	}

	public void setBannerId(Long bannerId) {
		this.bannerId = bannerId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public Long getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Long orderNum) {
		this.orderNum = orderNum;
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

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}
	
}
