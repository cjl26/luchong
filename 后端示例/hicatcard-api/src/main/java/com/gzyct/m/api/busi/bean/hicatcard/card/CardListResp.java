package com.gzyct.m.api.busi.bean.hicatcard.card;

import com.project.m.api.common.biz.resp.BizResp;

import java.util.List;

public class CardListResp extends BizResp {

    List<CardItem> card;

    public List<CardItem> getCard() {
        return card;
    }

    public void setCard(List<CardItem> card) {
        this.card = card;
    }
}
