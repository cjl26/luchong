package com.gzyct.m.api.busi.bean.merchant;

import java.util.List;

import com.project.m.api.common.biz.resp.BizResp;

public class MerchantListQueryResp extends BizResp {

	List<MerchantListQueryItem> merchant_list;

	public List<MerchantListQueryItem> getMerchant_list() {
		return merchant_list;
	}

	public void setMerchant_list(List<MerchantListQueryItem> merchant_list) {
		this.merchant_list = merchant_list;
	}

}
