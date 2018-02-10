package com.gzyct.m.api.busi.bean.wx;

/**
 * Created by chenjiajian on 2017/12/18.
 */
public class WxAuthResp {
    private String openid;
    private String session_key;
    private String unionid;
    private int errcode;
    private String errmsg;
    public void setOpenid(String openid) {
        this.openid = openid;
    }
    public String getOpenid() {
        return openid;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }
    public String getSession_key() {
        return session_key;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }
    public String getUnionid() {
        return unionid;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }
    public int getErrcode() {
        return errcode;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
    public String getErrmsg() {
        return errmsg;
    }
}
