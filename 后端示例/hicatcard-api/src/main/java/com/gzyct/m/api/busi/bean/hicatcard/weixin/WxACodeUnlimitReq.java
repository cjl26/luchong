package com.gzyct.m.api.busi.bean.hicatcard.weixin;

import java.util.Map;

public class WxACodeUnlimitReq {

    private String scene;
    private String page;
    private Integer width;
    private Boolean auto_color;
    private Map<String, Object> line_color;

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

    public Boolean getAuto_color() {
        return auto_color;
    }

    public void setAuto_color(Boolean auto_color) {
        this.auto_color = auto_color;
    }

    public Map<String, Object> getLine_color() {
        return line_color;
    }

    public void setLine_color(Map<String, Object> line_color) {
        this.line_color = line_color;
    }
}
