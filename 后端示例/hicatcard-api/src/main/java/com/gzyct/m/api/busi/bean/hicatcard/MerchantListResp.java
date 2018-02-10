package com.gzyct.m.api.busi.bean.hicatcard;

import com.project.m.api.common.biz.resp.BizResp;

import java.util.List;

public class MerchantListResp extends BizResp {

    private List<MerchantListItem> merchant;

    public List<MerchantListItem> getMerchant() {
        return merchant;
    }

    public void setMerchant(List<MerchantListItem> merchant) {
        this.merchant = merchant;
    }
}
