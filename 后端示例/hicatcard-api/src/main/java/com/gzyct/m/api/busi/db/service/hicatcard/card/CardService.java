package com.gzyct.m.api.busi.db.service.hicatcard.card;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gzyct.m.api.busi.bean.hicatcard.card.CardItem;
import com.gzyct.m.api.busi.bean.hicatcard.card.CardServiceItem;
import com.gzyct.m.api.busi.db.entity.hicatcard.TService;
import com.gzyct.m.api.busi.db.entity.hicatcard.card.TCard;
import com.gzyct.m.api.busi.db.entity.hicatcard.card.TCardService;
import com.gzyct.m.api.busi.db.repo.card.CardRepo;
import com.gzyct.m.api.busi.db.repo.card.CardServiceRepo;
import com.gzyct.m.api.busi.db.repo.service.ServiceRepo;
import com.gzyct.m.api.busi.util.TimeUtil;

@Transactional
@Service
public class CardService {

	@Autowired
	CardRepo cardRepo;

	@Autowired
	CardServiceRepo cardServiceRepo;

	@Autowired
	ServiceRepo serviceRepo;

	@Value("${image.upload.url}")
	private String imageUploadUrl;

	@Transactional(readOnly = true)
	public List<CardItem> findByTypeAndSearchText(String type, String searchText, Pageable pageable) {

		List<TCard> cardList = new ArrayList<TCard>();
		if (StringUtils.isEmpty(type)) {
			cardList = cardRepo.findByCardNameContainingAndEnableAndStatusOrderByOrdernumAsc(searchText, true,
					TCard.STATUS_SHOW, pageable);
		} else {
			cardList = cardRepo.findByTypeAndCardNameContainingAndEnableAndStatusOrderByOrdernumAsc(type, searchText,
					true, TCard.STATUS_SHOW, pageable);
		}

		// TODO left join + group
		List<CardItem> cardItemList = new ArrayList<CardItem>();
		for (TCard card : cardList) {
			CardItem cardItem = new CardItem();
			cardItem.setCard_id(String.valueOf(card.getCardId()));
			cardItem.setCard_name(card.getCardName());
			cardItem.setType(card.getType());
			cardItem.setPicture_url(imageUploadUrl + card.getPictureUlr());
			cardItem.setFee(String.valueOf(card.getFee()));
			cardItem.setDetail(card.getDetail());
			// cardItem.setEffectiveDay("有效期为" + card.getEffectiveDay() / 30 + "个月");
			cardItem.setEffectiveDay((int) (card.getEffectiveDay() / 30) + "");

			String effectiveTimeString = "";
			if (card.getEffectiveDay() != null) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
				effectiveTimeString = dateFormat.format(TimeUtil.addDateNDay(new Date(), card.getEffectiveDay()));
				effectiveTimeString = "有效期至" + effectiveTimeString;
			}
			cardItem.setEffectiveTime(effectiveTimeString);

			List<CardServiceItem> cardServiceItemList = new ArrayList<CardServiceItem>();
			// System.out.println("card.getCard_id() = " + card.getCardId());
			List<TCardService> cardServiceList = cardServiceRepo.findByCardIdAndEnable(card.getCardId(), true);
			for (TCardService cardService : cardServiceList) {
				TService service = serviceRepo.findByServiceId(cardService.getServiceId());
				CardServiceItem cardServiceItem = new CardServiceItem();
				cardServiceItem.setCard_service_id(cardService.getCardServiceId());
				cardServiceItem.setService_id(cardService.getServiceId());
				cardServiceItem.setService_name(service.getServiceName());
				cardServiceItem.setService_time(String.valueOf(cardService.getServiceTime()));
				cardServiceItemList.add(cardServiceItem);
				cardItem.setDetail(cardItem.getDetail()); // + "(" + service.getServiceName() + "服务:"+
															// cardService.getServiceTime() + "次)"
				cardItem.setServiceName(service.getServiceName());
				cardItem.setServiceTime("" + cardService.getServiceTime());
			}
			cardItem.setServices(cardServiceItemList);
			cardItemList.add(cardItem);
		}

		return cardItemList;
	}

	@Transactional(readOnly = true)
	public TCard findByCardIdAndEnable(Long cardId, Boolean enable) {
		return cardRepo.findByCardIdAndEnable(cardId, enable);
	}

	@Transactional(readOnly = true)
	public TCard findByCardId(Long cardId) {
		return cardRepo.findByCardId(cardId);
	}

	/*
	 * public TCard findById(Long cardId) { return repo.findOne(cardId); }
	 * 
	 * public void deleteById(Long cardId) { if(cardId != null) { TCard card =
	 * repo.findOne(cardId); if(card != null) { card.setEnable(Boolean.FALSE);
	 * repo.save(card); } } }
	 * 
	 * public TCard save(TCard card) { if(card != null) { return repo.save(card); }
	 * return null; }
	 */
}
