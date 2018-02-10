package com.gzyct.m.api.busi.db.entity.hicatcard;

import javax.persistence.*;

@Entity
@Table(name = "t_wx_token")
public class TWxToken extends BaseEntity {

    private Long wxTokenId;
    private String token;
    private String expiresIn;
    private String createTime;
    private String updateTime;
    private Boolean enable;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WX_TOKEN_ID")
    public Long getWxTokenId() {
        return wxTokenId;
    }

    public void setWxTokenId(Long wxTokenId) {
        this.wxTokenId = wxTokenId;
    }

    @Column(name = "TOKEN")
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Column(name = "EXPIRES_IN")
    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }

    @Column(name = "CREATE_TIME")
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Column(name = "UPDATE_TIME")
    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Column(name = "ENABLE")
    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
