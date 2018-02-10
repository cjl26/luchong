package com.gzyct.m.api.busi.bean.wx.pay;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * (微信支付)支付成功通知应答
 */
@XmlRootElement(name = "xml")
public class WxNotifyBizResp {

    private String return_code;
    private String return_msg;

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }
}
