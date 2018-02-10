package com.gzyct.m.api.busi.bean.hicatcard;

import com.project.m.api.common.biz.req.BizRequest;

public class MerchantListReq extends BizRequest {
    private String longitude;
    private String latitude;
    private Integer page;
    private Integer pagesize;

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPagesize() {
        return pagesize;
    }

    public void setPagesize(Integer pagesize) {
        this.pagesize = pagesize;
    }
}
