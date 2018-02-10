package com.gzyct.m.api.busi.handlers.hicatcard.card;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.gzyct.m.api.busi.bean.hicatcard.card.CardDetailQueryReq;
import com.gzyct.m.api.busi.bean.hicatcard.card.CardDetailQueryResp;
import com.gzyct.m.api.busi.bean.hicatcard.card.CardItem;
import com.gzyct.m.api.busi.bean.hicatcard.card.CardServiceItem;
import com.gzyct.m.api.busi.config.BusiError;
import com.gzyct.m.api.busi.db.entity.hicatcard.TService;
import com.gzyct.m.api.busi.db.entity.hicatcard.card.TCard;
import com.gzyct.m.api.busi.db.entity.hicatcard.card.TCardService;
import com.gzyct.m.api.busi.db.entity.hicatcard.user.TUserCardService;
import com.gzyct.m.api.busi.db.service.hicatcard.card.CardServiceService;
import com.gzyct.m.api.busi.db.service.hicatcard.card.SystemPresentCardService;
import com.gzyct.m.api.busi.db.service.hicatcard.service.ServiceService;
import com.gzyct.m.api.busi.db.service.hicatcard.user.UserCardService;
import com.gzyct.m.api.busi.handlers.DefaultBizParamChecker;
import com.gzyct.m.api.busi.util.TimeUtil;
import com.gzyct.m.api.validates.ValidateParam;
import com.gzyct.m.api.validates.ValidateRet;
import com.gzyct.m.api.validates.ValidateUtil;
import com.project.m.api.common.biz.BizHandler;

@Component
public class CardDetailQueryBizHandler extends DefaultBizParamChecker<CardDetailQueryReq, CardDetailQueryResp>
		implements BizHandler<CardDetailQueryReq, CardDetailQueryResp> {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	CardServiceService cardServiceService;

	@Autowired
	SystemPresentCardService systemPresentCardService;

	@Autowired
	UserCardService userCardService;

	@Autowired
	ServiceService serviceService;

	@Value("${image.upload.url}")
	String imageUploadUrl;

	@Override
	public CardDetailQueryResp handle(CardDetailQueryReq bizRequest) throws Exception {
		// TODO Auto-generated method stub
		CardDetailQueryResp bizResp = new CardDetailQueryResp();
		// 参数检查
		ValidateRet vRet = checkParam(bizRequest, bizResp);
		if (!vRet.isValid()) {
			return bizResp;
		}

		try {

			TCard card = null;
			if (!StringUtils.isEmpty(bizRequest.getUser_card_id())) {
				Long userCardId = Long.parseLong(bizRequest.getUser_card_id());
				card = userCardService.findByUserCardId(userCardId);

				List<TUserCardService> userCardServiceList = userCardService
						.findUserCardServiceByUserCardIdAndEnable(userCardId, true);
				bizResp.setUserCardServiceList(userCardServiceList);
				bizResp.setBackgroundImageUrl("http://hicatcitycardimage.6so2o.com/wxappimage/bg_for_collect_card.jpg");
			} else if (!StringUtils.isEmpty(bizRequest.getSystem_card_number())) {
				card = systemPresentCardService.findByCardNumber(bizRequest.getSystem_card_number());
				bizResp.setUserCardServiceList(null);
				bizResp.setBackgroundImageUrl("http://hicatcitycardimage.6so2o.com/wxappimage/bg_for_collect_card.jpg");
			} else {
				bizResp.setResult_code(BusiError.ERR_CODE_PARAM_BAD_REQ);
				bizResp.setError_message(BusiError.ERR_MSG_PARAM_BAD_REQ);
				return bizResp;
			}

			if (card == null) {
				bizResp.setResult_code(BusiError.ERR_CODE_CARD_NOT_EXIST);
				bizResp.setError_message(BusiError.ERR_MSG_CARD_NOT_EXIST);
				return bizResp;
			}

			List<TCardService> cardServiceList = cardServiceService.findByCardIdAndEnable(card.getCardId(), true);

			CardItem cardItem = new CardItem();
			cardItem.setCard_id(String.valueOf(card.getCardId()));
			cardItem.setCard_name(card.getCardName());
			cardItem.setType(card.getType());
			String effectiveTimeString = "";
			if (card.getEffectiveDay() != null) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
				effectiveTimeString = dateFormat.format(TimeUtil.addDateNDay(new Date(), card.getEffectiveDay()));
				effectiveTimeString = "有效期至" + effectiveTimeString;
			}
			cardItem.setEffectiveTime(effectiveTimeString);
			cardItem.setPicture_url(imageUploadUrl + card.getPictureUlr());
			cardItem.setFee(String.valueOf(card.getFee()));
			cardItem.setDetail(card.getDetail());
			List<CardServiceItem> cardServiceItemList = new ArrayList<CardServiceItem>();
			System.out.println("card.getCard_id() = " + card.getCardId());
			for (TCardService cardService : cardServiceList) {
				TService service = serviceService.findById(cardService.getServiceId());
				CardServiceItem cardServiceItem = new CardServiceItem();
				cardServiceItem.setCard_service_id(cardService.getCardServiceId());
				cardServiceItem.setService_id(cardService.getServiceId());
				cardServiceItem.setService_name(service.getServiceName());
				cardServiceItem.setService_time(String.valueOf(cardService.getServiceTime()));
				cardServiceItemList.add(cardServiceItem);
				cardItem.setDetail(cardItem.getDetail() + "(" + service.getServiceName() + "服务:"
						+ cardService.getServiceTime() + "次)");
			}
			cardItem.setServices(cardServiceItemList);

			bizResp.setResult_code(BusiError.ERR_CODE_SUCCESS);
			bizResp.setError_message("");
			bizResp.setCard(cardItem);
			return bizResp;

		} catch (Exception e) {
			logger.error("CardCollectBizHandler", e);
			bizResp.setResult_code(BusiError.ERR_CODE_DB_SAVE);
			bizResp.setError_message(BusiError.ERR_MSG_DB_SAVE);
			return bizResp;
		}

	}

	@Override
	public ValidateRet checkParam(CardDetailQueryReq req, CardDetailQueryResp resp) {
		ValidateRet vRet = super.checkParam(req, resp);
		if (!vRet.isValid())
			return vRet;
		List<ValidateParam> paramList = new ArrayList<ValidateParam>();

		vRet = ValidateUtil.validate(paramList);
		if (vRet == null) {
			vRet = new ValidateRet(true, "");
		}
		if (!vRet.isValid()) {
			resp.setResult_code(BusiError.ERR_CODE_PARAM_BAD_REQ);
			resp.setError_message(vRet.getErrMsg());
		}

		return vRet;
	}

}
