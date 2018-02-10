package com.gzyct.m.api.busi.handlers.hicatcard.merchant;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gzyct.m.api.busi.bean.hicatcard.user.UserCardServiceItem;
import com.gzyct.m.api.busi.bean.merchant.MerchantCardQueryReq;
import com.gzyct.m.api.busi.bean.merchant.MerchantCardQueryResp;
import com.gzyct.m.api.busi.config.BusiError;
import com.gzyct.m.api.busi.db.entity.hicatcard.card.TCard;
import com.gzyct.m.api.busi.db.entity.hicatcard.user.TUserCarLicence;
import com.gzyct.m.api.busi.db.entity.hicatcard.user.TUserCard;
import com.gzyct.m.api.busi.db.service.hicatcard.card.CardService;
import com.gzyct.m.api.busi.db.service.hicatcard.merchant.MerchantService;
import com.gzyct.m.api.busi.db.service.hicatcard.user.UserCarLicenceService;
import com.gzyct.m.api.busi.db.service.hicatcard.user.UserCardService;
import com.gzyct.m.api.busi.handlers.MerchantBizParamChecker;
import com.gzyct.m.api.validates.ValidateParam;
import com.gzyct.m.api.validates.ValidateRet;
import com.gzyct.m.api.validates.ValidateType;
import com.gzyct.m.api.validates.ValidateUtil;
import com.project.m.api.common.biz.BizHandler;

@Component
public class MerchantCardQueryBizHandler extends MerchantBizParamChecker<MerchantCardQueryReq, MerchantCardQueryResp>
		implements BizHandler<MerchantCardQueryReq, MerchantCardQueryResp> {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	MerchantService merchantService;

	@Autowired
	UserCardService userCardService;

	@Autowired
	CardService cardService;

	@Autowired
	UserCarLicenceService userCarLicenceService;

	@Override
	public MerchantCardQueryResp handle(MerchantCardQueryReq bizRequest) throws Exception {
		// TODO Auto-generated method stub
		MerchantCardQueryResp bizResp = new MerchantCardQueryResp();
		// 参数检查
		ValidateRet vRet = checkParam(bizRequest, bizResp);
		if (!vRet.isValid()) {
			return bizResp;
		}

		try {
			// Long merchant_id = Long.parseLong(bizRequest.getMerchant_id());

			// 检查merchant
			// TMerchant merchant = merchantService.findByMerchantId(merchant_id, true);
			// if (merchant == null) {
			// bizResp.setResult_code(BusiError.ERR_CODE_MERCHANT_NOT_EXIST);
			// bizResp.setError_message(BusiError.ERR_MSG_MERCHANT_NOT_EXIST);
			// return bizResp;
			// }

			// 找user_card
			List<TUserCard> userCardList = userCardService
					.findByUserCardNumberAndEnable(bizRequest.getUser_card_number(), true);
			if (userCardList == null || userCardList.size() == 0) {
				bizResp.setResult_code(BusiError.ERR_CODE_USER_CARD_NOT_EXIST);
				bizResp.setError_message(BusiError.ERR_MSG_USER_CARD_NOT_EXIST);
				return bizResp;
			}

			TUserCard userCard = userCardList.get(0);
			if (!userCard.getStatus().equals(TUserCard.STATUS_ACTIVE)) {
				bizResp.setResult_code(BusiError.ERR_CODE_USER_CARD_NOT_ACTIVATED);
				bizResp.setError_message(BusiError.ERR_MSG_USER_CARD_NOT_ACTIVATED);
				return bizResp;
			}

			List<TUserCarLicence> userCarLicence = userCarLicenceService.findByUserIdAndEnable(userCard.getUserId(),
					true);
			if (userCarLicence != null && userCarLicence.size() > 0) {
				bizResp.setCar_licence(userCarLicence.get(0).getCarLicenceNumber());
			}

			List<UserCardServiceItem> userCardServiceItemList = userCardService
					.findUserCardServiceItemListByUserCardId(userCard.getUserCardId(), true);

			TCard card = cardService.findByCardId(userCard.getCardId());
			if (card != null) {
				bizResp.setCard_name(card.getCardName());
				bizResp.setType(card.getType());
				bizResp.setPicture_url(card.getPictureUlr());
				bizResp.setFee(String.valueOf(card.getFee()));
				bizResp.setDetail(card.getDetail());
			}
			bizResp.setEnd_time(userCard.getEndTime());
			bizResp.setUser_id(String.valueOf(userCard.getUserId()));
			bizResp.setCard_id(String.valueOf(userCard.getCardId()));
			bizResp.setUser_card_number(userCard.getUserCardNumber());
			bizResp.setUser_card_id(String.valueOf(userCard.getUserCardId()));
			bizResp.setServices(userCardServiceItemList);
			bizResp.setCreate_time(userCard.getCreateTime());
			bizResp.setUpdate_time(userCard.getUpdateTime());

			bizResp.setResult_code(BusiError.ERR_CODE_SUCCESS);
			bizResp.setError_message("");
			return bizResp;

		} catch (Exception e) {
			logger.error("MerchantCardQueryBizHandler", e);
			bizResp.setResult_code(BusiError.ERR_CODE_DB_SAVE);
			bizResp.setError_message(BusiError.ERR_MSG_DB_SAVE);
			return bizResp;
		}

	}

	@Override
	public ValidateRet checkParam(MerchantCardQueryReq req, MerchantCardQueryResp resp) {
		ValidateRet vRet = super.checkParam(req, resp);
		if (!vRet.isValid())
			return vRet;
		List<ValidateParam> paramList = new ArrayList<ValidateParam>();
		// paramList.add(new ValidateParam("merchant_id", ValidateType.NOTBLANK,
		// req.getMerchant_id()));
		paramList.add(new ValidateParam("user_card_number", ValidateType.NOTBLANK, req.getUser_card_number()));

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
