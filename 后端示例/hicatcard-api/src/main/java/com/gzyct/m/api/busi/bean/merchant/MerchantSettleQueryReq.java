package com.gzyct.m.api.busi.bean.merchant;

import com.project.m.api.common.biz.req.BizRequest;

/**
 2. 每次结算的汇总记录给我咯~！要分页的~！
 可以按时间筛选结算单
 同服务的合并成一个 给总核销次数和单次多少钱
 还有要可以关键字查询服务名称
 */
public class MerchantSettleQueryReq extends BizRequest {

    private String merchant_id;
    private String begin_date;
    private String end_date;
    private String search_service_name;
    private Integer page;
    private Integer pagesize;

    public String getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(String merchant_id) {
        this.merchant_id = merchant_id;
    }

    public String getBegin_date() {
        return begin_date;
    }

    public void setBegin_date(String begin_date) {
        this.begin_date = begin_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getSearch_service_name() {
        return search_service_name;
    }

    public void setSearch_service_name(String search_service_name) {
        this.search_service_name = search_service_name;
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
