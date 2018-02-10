package com.gzyct.m.api.busi.bean.ext;

import com.project.m.api.common.biz.resp.BizResp;

public class UserRegisterResp extends BizResp{
    private String attach;

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }
}
