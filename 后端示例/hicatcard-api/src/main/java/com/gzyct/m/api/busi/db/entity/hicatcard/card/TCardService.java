package com.gzyct.m.api.busi.db.entity.hicatcard.card;

import com.gzyct.m.api.busi.db.entity.hicatcard.BaseEntity;

import javax.persistence.*;


@Entity
@Table(name = "t_card_service")
public class TCardService extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CARD_SERVICE_ID")
    private Long cardServiceId;

    @Column(name = "CARD_ID")
    private Long cardId;

    @Column(name = "SERVICE_ID")
    private Long serviceId;

    @Column(name = "SERVICE_TIME")
    private Integer serviceTime;

    @Column(name = "CREATOR")
    private String creator;

    @Column(name = "CREATE_TIME")
    private String createTime;

    @Column(name = "UPDATE_TIME")
    private String updateTime;

    @Column(name = "ENABLE")
    private boolean enable = Boolean.TRUE;

    public Long getCardServiceId() {
        return cardServiceId;
    }

    public void setCardServiceId(Long cardServiceId) {
        this.cardServiceId = cardServiceId;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(Integer serviceTime) {
        this.serviceTime = serviceTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }


}
