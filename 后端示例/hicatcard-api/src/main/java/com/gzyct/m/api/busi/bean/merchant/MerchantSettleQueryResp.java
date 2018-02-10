package com.gzyct.m.api.busi.bean.merchant;

import java.util.List;

import com.project.m.api.common.biz.resp.BizResp;

public class MerchantSettleQueryResp extends BizResp {

	private List<MerchantSettleQueryItem> merchantSettleQueryItemList;

	public List<MerchantSettleQueryItem> getMerchantSettleQueryItemList() {
		return merchantSettleQueryItemList;
	}

	public void setMerchantSettleQueryItemList(List<MerchantSettleQueryItem> merchantSettleQueryItemList) {
		this.merchantSettleQueryItemList = merchantSettleQueryItemList;
	}

}
