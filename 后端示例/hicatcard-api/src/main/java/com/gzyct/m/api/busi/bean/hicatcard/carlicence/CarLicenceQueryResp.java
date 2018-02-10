package com.gzyct.m.api.busi.bean.hicatcard.carlicence;

import com.project.m.api.common.biz.resp.BizResp;

public class CarLicenceQueryResp extends BizResp {

	private String car_licence; // 车牌

	public String getCar_licence() {
		return car_licence;
	}

	public void setCar_licence(String car_licence) {
		this.car_licence = car_licence;
	}

}
