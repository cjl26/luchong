package com.gzyct.m.api.busi.bean.hicatcard.card;

public class CardServiceItem {

    private Long card_service_id;
    private Long service_id;
    private String service_name;
    private String service_time;

    public Long getCard_service_id() {
        return card_service_id;
    }

    public void setCard_service_id(Long card_service_id) {
        this.card_service_id = card_service_id;
    }

    public Long getService_id() {
        return service_id;
    }

    public void setService_id(Long service_id) {
        this.service_id = service_id;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public String getService_time() {
        return service_time;
    }

    public void setService_time(String service_time) {
        this.service_time = service_time;
    }
}
