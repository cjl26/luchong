package com.gzyct.m.api.busi.bean.ext;

import com.gzyct.m.api.busi.db.entity.hicatcard.user.TUser;
import com.project.m.api.common.biz.resp.BizResp;

/**
 * 滚动广告栏信息获取
 */
public class AuthCodeToOpenIdResp extends BizResp {

    private String wxAuthOpenid;
    private String session_key;
    private TUser userInfo;

    public String getWxAuthOpenid() {
        return wxAuthOpenid;
    }

    public void setWxAuthOpenid(String wxAuthOpenid) {
        this.wxAuthOpenid = wxAuthOpenid;
    }

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    public TUser getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(TUser userInfo) {
        this.userInfo = userInfo;
    }
}
