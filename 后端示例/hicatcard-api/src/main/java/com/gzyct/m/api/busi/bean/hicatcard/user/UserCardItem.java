package com.gzyct.m.api.busi.bean.hicatcard.user;

import java.util.List;

import com.gzyct.m.api.busi.bean.hicatcard.card.CardServiceItem;

public class UserCardItem {

	private Long userCardId;
	private Long userId;
	private Long card_id;
	private String card_name;
	private String type;
	private String picture_url;
	private Integer fee;
	private String detail; // 用于详情显示的数据
	private String userCardNumber;
	private String endTime;
	private String effectiveDay;
	private String is_expired; // 是否过期
	private String source;
	private Long sourceUserId;
	private String sourceUserName;
	private Long targetUserId;
	private String targetUserName;
	private String status;
	private String listDetail; // 用于列表显示的数据
	private String createTime;
	private String updateTime;
	private String serviceName;
	private String serviceTime;
	private List<UserCardServiceItem> userCardService;
	private List<CardServiceItem> cardServices;

	public String getCard_name() {
		return card_name;
	}

	public void setCard_name(String card_name) {
		this.card_name = card_name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPicture_url() {
		return picture_url;
	}

	public void setPicture_url(String picture_url) {
		this.picture_url = picture_url;
	}

	public Integer getFee() {
		return fee;
	}

	public void setFee(Integer fee) {
		this.fee = fee;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Long getUserCardId() {
		return userCardId;
	}

	public void setUserCardId(Long userCardId) {
		this.userCardId = userCardId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCard_id() {
		return card_id;
	}

	public void setCard_id(Long card_id) {
		this.card_id = card_id;
	}

	public String getUserCardNumber() {
		return userCardNumber;
	}

	public void setUserCardNumber(String userCardNumber) {
		this.userCardNumber = userCardNumber;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Long getSourceUserId() {
		return sourceUserId;
	}

	public void setSourceUserId(Long sourceUserId) {
		this.sourceUserId = sourceUserId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<UserCardServiceItem> getUserCardService() {
		return userCardService;
	}

	public void setUserCardService(List<UserCardServiceItem> userCardService) {
		this.userCardService = userCardService;
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

	public String getSourceUserName() {
		return sourceUserName;
	}

	public void setSourceUserName(String sourceUserName) {
		this.sourceUserName = sourceUserName;
	}

	public Long getTargetUserId() {
		return targetUserId;
	}

	public void setTargetUserId(Long targetUserId) {
		this.targetUserId = targetUserId;
	}

	public String getTargetUserName() {
		return targetUserName;
	}

	public void setTargetUserName(String targetUserName) {
		this.targetUserName = targetUserName;
	}

	public String getListDetail() {
		return listDetail;
	}

	public void setListDetail(String listDetail) {
		this.listDetail = listDetail;
	}

	public List<CardServiceItem> getCardServices() {
		return cardServices;
	}

	public void setCardServices(List<CardServiceItem> cardServices) {
		this.cardServices = cardServices;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getIs_expired() {
		return is_expired;
	}

	public void setIs_expired(String is_expired) {
		this.is_expired = is_expired;
	}

	public String getEffectiveDay() {
		return effectiveDay;
	}

	public void setEffectiveDay(String effectiveDay) {
		this.effectiveDay = effectiveDay;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(String serviceTime) {
		this.serviceTime = serviceTime;
	}

}
