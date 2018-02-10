package com.gzyct.m.api.busi.bean.ext;

import com.gzyct.m.api.busi.db.entity.hicatcard.TBanner;
import com.project.m.api.common.biz.resp.BizResp;

import java.util.List;

/**
 * 滚动广告栏信息获取
 */
public class AdvertisementQueryResp extends BizResp {

	private List<TBanner> ads;

	public List<TBanner> getAds() {
		return ads;
	}

	public void setAds(List<TBanner> ads) {
		this.ads = ads;
	}

	// private List<Advertisement> ads;
	//
	// public List<Advertisement> getAds() {
	// return ads;
	// }
	//
	// public void setAds(List<Advertisement> ads) {
	// this.ads = ads;
	// }
}
