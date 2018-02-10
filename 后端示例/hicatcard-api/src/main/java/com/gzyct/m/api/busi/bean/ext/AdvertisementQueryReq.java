package com.gzyct.m.api.busi.bean.ext;

import com.project.m.api.common.biz.req.BizRequest;

/**
 *
 */
public class AdvertisementQueryReq extends BizRequest {

	private int place;

	public int getPlace() {
		return place;
	}

	public void setPlace(int place) {
		this.place = place;
	}
}
