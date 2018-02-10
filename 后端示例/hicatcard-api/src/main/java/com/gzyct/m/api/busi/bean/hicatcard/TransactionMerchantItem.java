package com.gzyct.m.api.busi.bean.hicatcard;

import java.util.List;

public class TransactionMerchantItem {
    private String merchant_id;
    private String merchant_number;
    private String name;
    private String phone;
    private String province;
    private String city;
    private String address;
    private String longitude;
    private String latitude;
    private String picture_url;
    private String detail_picture_url;
    private String web_url;
    private String detail;
    private String status;
    private List<MerchantServiceItem> service;

    public String getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(String merchant_id) {
        this.merchant_id = merchant_id;
    }

    public String getMerchant_number() {
        return merchant_number;
    }

    public void setMerchant_number(String merchant_number) {
        this.merchant_number = merchant_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

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

    public String getPicture_url() {
        return picture_url;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }

    public String getWeb_url() {
        return web_url;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<MerchantServiceItem> getService() {
        return service;
    }

    public void setService(List<MerchantServiceItem> service) {
        this.service = service;
    }

    public String getDetail_picture_url() {
        return detail_picture_url;
    }

    public void setDetail_picture_url(String detail_picture_url) {
        this.detail_picture_url = detail_picture_url;
    }
}
