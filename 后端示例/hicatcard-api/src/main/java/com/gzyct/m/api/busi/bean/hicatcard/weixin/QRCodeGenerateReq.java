package com.gzyct.m.api.busi.bean.hicatcard.weixin;

import com.project.m.api.common.biz.req.BizRequest;

public class QRCodeGenerateReq extends BizRequest {

    private String scene;
    private String page;
    private Integer width;
    private String pictureName;

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }
}
