package com.gzyct.m.api.busi.bean.hicatcard;

import io.swagger.models.auth.In;

public class MerchantServiceItem {

    private Long merchant_service_id;
    private Long service_id;
    private String service_name;
    private Integer fee;

    public Long getMerchant_service_id() {
        return merchant_service_id;
    }

    public void setMerchant_service_id(Long merchant_service_id) {
        this.merchant_service_id = merchant_service_id;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    public Long getService_id() {
        return service_id;
    }

    public void setService_id(Long service_id) {
        this.service_id = service_id;
    }
}
