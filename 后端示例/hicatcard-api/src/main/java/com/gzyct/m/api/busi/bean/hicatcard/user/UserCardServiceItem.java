package com.gzyct.m.api.busi.bean.hicatcard.user;

public class UserCardServiceItem {

	private Long userCardServiceId;
	private Long userCardId;
	private Long serviceId;
	private String serviceName;
	private Integer serviceTime;
	private Integer serviceTotalTime; // 原来卡片总次数

	public Long getUserCardServiceId() {
		return userCardServiceId;
	}

	public void setUserCardServiceId(Long userCardServiceId) {
		this.userCardServiceId = userCardServiceId;
	}

	public Long getUserCardId() {
		return userCardId;
	}

	public void setUserCardId(Long userCardId) {
		this.userCardId = userCardId;
	}

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Integer getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(Integer serviceTime) {
		this.serviceTime = serviceTime;
	}

	public Integer getServiceTotalTime() {
		return serviceTotalTime;
	}

	public void setServiceTotalTime(Integer serviceTotalTime) {
		this.serviceTotalTime = serviceTotalTime;
	}

}
