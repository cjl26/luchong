package com.gzyct.m.api.busi.bean.hicatcard;

import com.gzyct.m.api.busi.db.entity.hicatcard.user.TUser;
import com.project.m.api.common.biz.resp.BizResp;

public class UserInfoResp extends BizResp{
    private TUser userInfo;

    public TUser getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(TUser userInfo) {
        this.userInfo = userInfo;
    }
}
