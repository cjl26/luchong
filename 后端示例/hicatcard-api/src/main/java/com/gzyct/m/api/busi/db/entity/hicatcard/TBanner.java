package com.gzyct.m.api.busi.db.entity.hicatcard;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_banner")
public class TBanner {

	public static String STATUS_IN_USE = "1";
	public static String STATUS_NOT_USE = "0";

	public static String PLACE_MAIN = "1";
	public static String PLACE_QR_CODE = "2";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BANNER_ID")
	private Long bannerId;

	@Column(name = "TITLE")
	private String title;

	@Column(name = "PLACE")
	private String place;

	@Column(name = "ORDERNUM")
	private String orderNum;

	@Column(name = "PICTURE_URL")
	private String pictureUrl;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "CREATE_TIME")
	private String createTime;

	@Column(name = "UPDATE_TIME")
	private String updateTime;

	@Column(name = "ENABLE")
	private Boolean enable;

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

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

}
