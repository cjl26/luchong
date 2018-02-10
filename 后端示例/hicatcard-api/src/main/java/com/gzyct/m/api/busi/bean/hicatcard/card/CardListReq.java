package com.gzyct.m.api.busi.bean.hicatcard.card;

import com.project.m.api.common.biz.req.BizRequest;

public class CardListReq extends BizRequest {

    private String type;
    private String searchText;
    private Integer page;
    private Integer pagesize;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
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
