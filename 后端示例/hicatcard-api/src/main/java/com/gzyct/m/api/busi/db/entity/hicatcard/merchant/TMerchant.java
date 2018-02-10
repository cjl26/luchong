package com.gzyct.m.api.busi.db.entity.hicatcard.merchant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gzyct.m.api.busi.db.entity.hicatcard.BaseEntity;

@Entity
@Table(name = "t_merchant")
public class TMerchant extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MERCHANT_ID")
	private Long merchantId;

	@Column(name = "MERCHANT_NUMBER")
	private String merchantNumber;

	@Column(name = "NAME")
	private String name;

	@Column(name = "PHONE")
	private String phone;

	@Column(name = "PROVINCE")
	private String province;

	@Column(name = "CITY")
	private String city;

	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "LONGITUDE")
	private String longitude;

	@Column(name = "LATITUDE")
	private String latitude;

	@Column(name = "DETAIL_PICTURE_URL")
	private String detailPictureUrl;

	@Column(name = "PICTURE_URL")
	private String pictureUrl;

	@Column(name = "WEB_URL")
	private String webUrl;

	@Column(name = "DETAIL")
	private String detail;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "CREATOR")
	private String creator;

	@Column(name = "CREATE_TIME")
	private String createTime;

	@Column(name = "UPDATE_TIME")
	private String updateTime;

	@Column(name = "ENABLE")
	private boolean enable;

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantNumber() {
		return merchantNumber;
	}

	public void setMerchantNumber(String merchantNumber) {
		this.merchantNumber = merchantNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public String getWebUrl() {
		return webUrl;
	}

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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

	public String getDetailPictureUrl() {
		return detailPictureUrl;
	}

	public void setDetailPictureUrl(String detailPictureUrl) {
		this.detailPictureUrl = detailPictureUrl;
	}

	/*
	 * public static final String STATUS_IN_USE = "1"; public static final String
	 * STATUS_IN_USE_MSG = "在用"; public static final String STATUS_NOT_USE = "0";
	 * public static final String STATUS_NOT_USE_MSG = "停用";
	 * 
	 * // 状态 public static Map<String, String> initStatusMap() { Map<String, String>
	 * statusMap = new HashMap<String, String>(); statusMap.put(STATUS_IN_USE,
	 * STATUS_IN_USE_MSG); statusMap.put(STATUS_NOT_USE, STATUS_NOT_USE_MSG); return
	 * statusMap; }
	 */
}
