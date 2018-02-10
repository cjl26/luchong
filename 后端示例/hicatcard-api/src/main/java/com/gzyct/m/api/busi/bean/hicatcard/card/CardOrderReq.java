package com.gzyct.m.api.busi.bean.hicatcard.card;

import com.project.m.api.common.biz.req.BizRequest;

public class CardOrderReq extends BizRequest {
    private String card_id;
    private Integer total_fee;
    private Integer pay_fee;
    private String user_coupon_id;
    private Integer coupon_fee;
    private String attach;

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public Integer getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(Integer total_fee) {
        this.total_fee = total_fee;
    }

    public Integer getPay_fee() {
        return pay_fee;
    }

    public void setPay_fee(Integer pay_fee) {
        this.pay_fee = pay_fee;
    }

    public String getUser_coupon_id() {
        return user_coupon_id;
    }

    public void setUser_coupon_id(String user_coupon_id) {
        this.user_coupon_id = user_coupon_id;
    }

    public Integer getCoupon_fee() {
        return coupon_fee;
    }

    public void setCoupon_fee(Integer coupon_fee) {
        this.coupon_fee = coupon_fee;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }
}
