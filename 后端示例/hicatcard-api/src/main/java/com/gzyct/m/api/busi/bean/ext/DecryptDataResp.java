package com.gzyct.m.api.busi.bean.ext;

import com.project.m.api.common.biz.resp.BizResp;

/**
 * 滚动广告栏信息获取
 */
public class DecryptDataResp extends BizResp {

    private String decryptedData;

    public String getDecryptedData() {
        return decryptedData;
    }

    public void setDecryptedData(String decryptedData) {
        this.decryptedData = decryptedData;
    }
}
