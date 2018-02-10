package com.gzyct.m.api.busi.bean.hicatcard.card;

import java.util.List;

import com.gzyct.m.api.busi.db.entity.hicatcard.user.TUserCardService;
import com.project.m.api.common.biz.resp.BizResp;

public class CardDetailQueryResp extends BizResp {

	CardItem card;
	List<TUserCardService> userCardServiceList;
	String backgroundImageUrl;

	public CardItem getCard() {
		return card;
	}

	public void setCard(CardItem card) {
		this.card = card;
	}

	public String getBackgroundImageUrl() {
		return backgroundImageUrl;
	}

	public void setBackgroundImageUrl(String backgroundImageUrl) {
		this.backgroundImageUrl = backgroundImageUrl;
	}

	public List<TUserCardService> getUserCardServiceList() {
		return userCardServiceList;
	}

	public void setUserCardServiceList(List<TUserCardService> userCardServiceList) {
		this.userCardServiceList = userCardServiceList;
	}

}
