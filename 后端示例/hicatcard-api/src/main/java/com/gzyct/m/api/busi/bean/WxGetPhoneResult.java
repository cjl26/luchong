package com.gzyct.m.api.busi.bean;

/**
 * Created by chenjiajian on 2018/1/5.
 */
public class WxGetPhoneResult {


    /**
     * phoneNumber : 13763319124
     * purePhoneNumber : 13763319124
     * countryCode : 86
     * watermark : {"timestamp":1515135407,"appid":"wx6f184c7366843273"}
     */

    private String phoneNumber;
    private String purePhoneNumber;
    private String countryCode;
    private WatermarkBean watermark;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPurePhoneNumber() {
        return purePhoneNumber;
    }

    public void setPurePhoneNumber(String purePhoneNumber) {
        this.purePhoneNumber = purePhoneNumber;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public WatermarkBean getWatermark() {
        return watermark;
    }

    public void setWatermark(WatermarkBean watermark) {
        this.watermark = watermark;
    }

    public static class WatermarkBean {
        /**
         * timestamp : 1515135407
         * appid : wx6f184c7366843273
         */

        private int timestamp;
        private String appid;

        public int getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(int timestamp) {
            this.timestamp = timestamp;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }
    }
}
