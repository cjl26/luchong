package com.gzyct.m.api.busi.bean.hicatcard.user;

import com.project.m.api.common.biz.resp.BizResp;

import java.util.List;

public class UserCardListResp extends BizResp {

    private List<UserCardItem> dataList;

    public List<UserCardItem> getDataList() {
        return dataList;
    }

    public void setDataList(List<UserCardItem> dataList) {
        this.dataList = dataList;
    }
}
