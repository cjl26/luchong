package com.gzyct.m.api.busi.handlers.hicatcard.card;

import com.gzyct.m.api.busi.bean.hicatcard.card.SystemCardCollectReq;
import com.gzyct.m.api.busi.bean.hicatcard.card.SystemCardCollectResp;
import com.gzyct.m.api.busi.config.BusiError;
import com.gzyct.m.api.busi.db.entity.hicatcard.card.TCard;
import com.gzyct.m.api.busi.db.entity.hicatcard.card.TSystemPresentCard;
import com.gzyct.m.api.busi.db.entity.hicatcard.user.TUser;
import com.gzyct.m.api.busi.db.entity.hicatcard.user.TUserCard;
import com.gzyct.m.api.busi.db.service.hicatcard.card.CardService;
import com.gzyct.m.api.busi.db.service.hicatcard.card.SystemPresentCardService;
import com.gzyct.m.api.busi.db.service.hicatcard.user.UserCardService;
import com.gzyct.m.api.busi.db.service.hicatcard.user.UserService;
import com.gzyct.m.api.busi.handlers.DefaultBizParamChecker;
import com.gzyct.m.api.busi.util.TimeUtil;
import com.gzyct.m.api.validates.ValidateParam;
import com.gzyct.m.api.validates.ValidateRet;
import com.gzyct.m.api.validates.ValidateType;
import com.gzyct.m.api.validates.ValidateUtil;
import com.project.m.api.common.biz.BizHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SystemCardCollectBizHandler extends DefaultBizParamChecker<SystemCardCollectReq, SystemCardCollectResp>
		implements BizHandler<SystemCardCollectReq, SystemCardCollectResp> {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	UserService userService;

	@Autowired
	SystemPresentCardService systemPresentCardService;

	@Autowired
	UserCardService userCardService;

	@Autowired
	CardService cardService;

	@Override
	public SystemCardCollectResp handle(SystemCardCollectReq bizRequest) throws Exception {
		// TODO Auto-generated method stub
		SystemCardCollectResp bizResp = new SystemCardCollectResp();
		// 参数检查
		ValidateRet vRet = checkParam(bizRequest, bizResp);
		if (!vRet.isValid()) {
			return bizResp;
		}

		try {
			// 判断用户是否存在
			List<TUser> userList = userService.findByOpenidAndEnable(bizRequest.getOpenid(), true);
			if (userList == null || userList.size() == 0) {
				bizResp.setResult_code(BusiError.ERR_CODE_USER_NONE);
				bizResp.setError_message(BusiError.ERR_MSG_USER_NONE);
				return bizResp;
			}

			TUser user = userList.get(0);

			// 判断卡片是否存在
			List<TSystemPresentCard> systemPresentCardList = systemPresentCardService
					.findByCardNumberAndStatus(bizRequest.getCard_number(), TSystemPresentCard.STATUS_NOT_COLLECT);
			if (systemPresentCardList == null || systemPresentCardList.size() == 0) {
				bizResp.setResult_code(BusiError.ERR_CODE_SYSTEM_CARD_NOT_EXIST);
				bizResp.setError_message(BusiError.ERR_MSG_SYSTEM_CARD_NOT_EXIST);
				return bizResp;
			}
			TSystemPresentCard systemPresentCard = systemPresentCardList.get(0);

			TCard card = cardService.findByCardId(systemPresentCard.getCardId());
			if (card == null) {
				bizResp.setResult_code(BusiError.ERR_CODE_CARD_NOT_EXIST);
				bizResp.setError_message(BusiError.ERR_MSG_CARD_NOT_EXIST);
				return bizResp;
			}

			// 添加卡片
			TUserCard userCardInsert = new TUserCard();
			userCardInsert.setUserId(user.getUserId());
			userCardInsert.setCardId(systemPresentCard.getCardId());
			userCardInsert.setUserCardNumber(systemPresentCard.getCardNumber());    //IdGenerator.generateOrderId(IdGenerator.ORDERID_PREFIX_USER_CARD, "00", "0000")
			userCardInsert.setSource(TUserCard.SOURCE_PRESENT);
			userCardInsert.setSourceUserId(-1l);
			userCardInsert.setStatus(TUserCard.STATUS_NOT_ACTIVE);
			userCardInsert.setEndTime("");
//			if (card.getEffectiveDay() != null) {
//				SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
//				userCardInsert.setEndTime(outFormat.format(TimeUtil.addDateNDay(new Date(), card.getEffectiveDay())));
//			} else {
//				SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
//				userCardInsert.setEndTime(outFormat.format(TimeUtil.addDateNDay(new Date(), 365)));
//			}
			userCardInsert.setCreateTime(TimeUtil.getCurrTime());
			userCardInsert.setUpdateTime(TimeUtil.getCurrTime());
			userCardInsert.setEnable(true);

			userCardService.saveUserCardBySystem(userCardInsert, systemPresentCard);
			bizResp.setResult_code(BusiError.ERR_CODE_SUCCESS);
			bizResp.setError_message("");
			return bizResp;

		} catch (Exception e) {
			logger.error("SystemCardCollectBizHandler", e);
			bizResp.setResult_code(BusiError.ERR_CODE_DB_SAVE);
			bizResp.setError_message(BusiError.ERR_MSG_DB_SAVE);
			return bizResp;
		}

	}

	@Override
	public ValidateRet checkParam(SystemCardCollectReq req, SystemCardCollectResp resp) {
		ValidateRet vRet = super.checkParam(req, resp);
		if (!vRet.isValid())
			return vRet;
		List<ValidateParam> paramList = new ArrayList<ValidateParam>();
		paramList.add(new ValidateParam("card_number", ValidateType.NOTBLANK, req.getCard_number()));

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
