package com.gzyct.m.api.busi.handlers.hicatcard.card;

import com.gzyct.m.api.busi.bean.hicatcard.card.CardActivateReq;
import com.gzyct.m.api.busi.bean.hicatcard.card.CardActivateResp;
import com.gzyct.m.api.busi.config.BusiError;
import com.gzyct.m.api.busi.db.entity.hicatcard.card.TCard;
import com.gzyct.m.api.busi.db.entity.hicatcard.user.TUser;
import com.gzyct.m.api.busi.db.entity.hicatcard.user.TUserCard;
import com.gzyct.m.api.busi.db.service.hicatcard.card.CardService;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class CardActivateBizHandler extends DefaultBizParamChecker<CardActivateReq, CardActivateResp>
		implements BizHandler<CardActivateReq, CardActivateResp> {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	UserService userService;

	@Autowired
	UserCardService userCardService;

	@Autowired
	CardService cardService;

	//@Autowired
	//SmsCodeService smsCodeService;

	@Override
	public CardActivateResp handle(CardActivateReq bizRequest) throws Exception {
		// TODO Auto-generated method stub
		CardActivateResp bizResp = new CardActivateResp();
		// 参数检查
		ValidateRet vRet = checkParam(bizRequest, bizResp);
		if (!vRet.isValid()) {
			return bizResp;
		}

		try {
			Long userCardId = Long.parseLong(bizRequest.getUser_card_id());

			// 1. 检查用户是否存在
			// 验证用户
			List<TUser> userList = userService.findByOpenidAndEnable(bizRequest.getOpenid(), true);
			if (userList == null || userList.size() == 0) {
				bizResp.setResult_code(BusiError.ERR_CODE_USER_NONE);
				bizResp.setError_message(BusiError.ERR_MSG_USER_NONE);
				return bizResp;
			}

			//TUser user = userList.get(0);

			// 2. user_card_id对应的用户是否一样
			TUserCard userCard = userCardService.findByUserCardIdAndEnable(userCardId, true);
			if (userCard == null) {
				bizResp.setResult_code(BusiError.ERR_CODE_USER_CARD_NOT_EXIST);
				bizResp.setError_message(BusiError.ERR_MSG_USER_CARD_NOT_EXIST);
				return bizResp;
			}

			if (!userCard.getStatus().equalsIgnoreCase(TUserCard.STATUS_NOT_ACTIVE)) {
				bizResp.setResult_code(BusiError.ERR_CODE_USER_CARD_IS_ACTIVATED);
				bizResp.setError_message(BusiError.ERR_MSG_USER_CARD_IS_ACTIVATED);
				return bizResp;
			}
//			List<TSmsCode> smsCodeList = smsCodeService
//					.findByTypeAndPhoneAndEnable(TSmsCode.SMS_CODE_REQ_TYPE_CARD_ACTIVATE, user.getPhone(), true);
//			if(bizRequest.getSms_code().startsWith("a")){
//
//			}else {
//				// 3. 检查短信验证码
//				if (smsCodeList == null || smsCodeList.size() == 0) {
//					bizResp.setResult_code(BusiError.ERR_CODE_SMS_CODE_NOT_EXIST);
//					bizResp.setError_message(BusiError.ERR_MSG_SMS_CODE_NOT_EXIST);
//					return bizResp;
//				}
//
//				TSmsCode smsCode = smsCodeList.get(0);
//				if (!smsCode.getCode().equalsIgnoreCase(bizRequest.getSms_code())) {
//					bizResp.setResult_code(BusiError.ERR_CODE_SMS_CODE_ERROR);
//					bizResp.setError_message(BusiError.ERR_MSG_SMS_CODE_ERROR);
//					return bizResp;
//				}
//
//				if (TimeUtil.getCurrTime().compareToIgnoreCase(smsCode.getExpireTime()) > 0) {
//					bizResp.setResult_code(BusiError.ERR_CODE_SMS_CODE_EXPIRED);
//					bizResp.setError_message(BusiError.ERR_MSG_SMS_CODE_EXPIRED);
//					return bizResp;
//				}
//			}

			// 4. 修改user_card状态
			TCard card = cardService.findByCardId(userCard.getCardId());
			if (card == null) {
				bizResp.setResult_code(BusiError.ERR_CODE_USER_CARD_NOT_EXIST);
				bizResp.setError_message(BusiError.ERR_MSG_USER_CARD_NOT_EXIST);
				return bizResp;
			}

			//添加失效日期
			if (card.getEffectiveDay() != null) {
				SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
				userCard.setEndTime(outFormat.format(TimeUtil.addDateNDay(new Date(), card.getEffectiveDay())));
			} else {
				SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
				userCard.setEndTime(outFormat.format(TimeUtil.addDateNDay(new Date(), 365)));
			}
			userCard.setStatus(TUserCard.STATUS_ACTIVE);
			userCard.setUpdateTime(TimeUtil.getCurrTime());
			userCardService.saveUserCard(userCard);

//			// 5. 修改短信验证码状态
//			for (TSmsCode smsCodeItem : smsCodeList) {
//				smsCodeItem.setEnable(false);
//				smsCodeItem.setUpdateTime(TimeUtil.getCurrTime());
//			}
//			smsCodeService.saveSmsCodeList(smsCodeList);

			bizResp.setResult_code(BusiError.ERR_CODE_SUCCESS);
			bizResp.setError_message("");
			return bizResp;
		} catch (Exception e) {
			logger.error("CardActivateBizHandler", e);
			bizResp.setResult_code(BusiError.ERR_CODE_DB_SAVE);
			bizResp.setError_message(BusiError.ERR_MSG_DB_SAVE);
			return bizResp;
		}
	}

	@Override
	public ValidateRet checkParam(CardActivateReq req, CardActivateResp resp) {
		ValidateRet vRet = super.checkParam(req, resp);
		if (!vRet.isValid())
			return vRet;
		List<ValidateParam> paramList = new ArrayList<ValidateParam>();
		paramList.add(new ValidateParam("user_card_id", ValidateType.NOTBLANK, req.getUser_card_id()));
		//paramList.add(new ValidateParam("sms_code", ValidateType.NOTBLANK, req.getSms_code()));

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
