package com.gzyct.m.api.busi.bean.ext;

import com.project.m.api.common.biz.req.BizRequest;

/**
 *
 */
public class DecryptDataReq extends BizRequest {

    private String sessionKey;
    private String encryptedData;
    private String ivStr;

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getEncryptedData() {
        return encryptedData;
    }

    public void setEncryptedData(String encryptedData) {
        this.encryptedData = encryptedData;
    }

    public String getIvStr() {
        return ivStr;
    }

    public void setIvStr(String ivStr) {
        this.ivStr = ivStr;
    }
}
