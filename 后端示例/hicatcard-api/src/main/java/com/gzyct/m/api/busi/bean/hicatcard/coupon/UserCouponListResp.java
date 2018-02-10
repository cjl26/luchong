package com.gzyct.m.api.busi.bean.hicatcard.coupon;

import java.util.List;

import com.project.m.api.common.biz.resp.BizResp;

public class UserCouponListResp extends BizResp {

	List<UserCouponItem> coupon;

	public List<UserCouponItem> getCoupon() {
		return coupon;
	}

	public void setCoupon(List<UserCouponItem> coupon) {
		this.coupon = coupon;
	}

}
