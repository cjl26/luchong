package com.gzyct.m.api.busi.db.entity.hicatcard.user;

import javax.persistence.*;

@Entity
@Table(name = "t_user_card_transfer")
public class TUserCardTransfer {

    private Long userCardTransferId;
    private String userCardNumber;
    private Long sourceUserCardId;
    private Long sourceUserId;
    private String sourceCardNumber;
    private Long targetUserCardId;
    private Long targetUserId;
    private String createTime;
    private String updateTime;
    private Boolean enable;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_CARD_TRANSFER_ID")
    public Long getUserCardTransferId() {
        return userCardTransferId;
    }

    public void setUserCardTransferId(Long userCardTransferId) {
        this.userCardTransferId = userCardTransferId;
    }

    @Column(name = "USER_CARD_NUMBER")
    public String getUserCardNumber() {
        return userCardNumber;
    }

    public void setUserCardNumber(String userCardNumber) {
        this.userCardNumber = userCardNumber;
    }

    @Column(name = "SOURCE_USER_CARD_ID")
    public Long getSourceUserCardId() {
        return sourceUserCardId;
    }

    public void setSourceUserCardId(Long sourceUserCardId) {
        this.sourceUserCardId = sourceUserCardId;
    }

    @Column(name = "SOURCE_USER_ID")
    public Long getSourceUserId() {
        return sourceUserId;
    }

    public void setSourceUserId(Long sourceUserId) {
        this.sourceUserId = sourceUserId;
    }

    @Column(name = "SOURCE_CARD_NUMBER")
    public String getSourceCardNumber() {
        return sourceCardNumber;
    }

    public void setSourceCardNumber(String sourceCardNumber) {
        this.sourceCardNumber = sourceCardNumber;
    }

    @Column(name = "TARGET_USER_CARD_ID")
    public Long getTargetUserCardId() {
        return targetUserCardId;
    }

    public void setTargetUserCardId(Long targetUserCardId) {
        this.targetUserCardId = targetUserCardId;
    }

    @Column(name = "TARGET_USER_ID")
    public Long getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(Long targetUserId) {
        this.targetUserId = targetUserId;
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
