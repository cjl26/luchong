package com.gzyct.m.api.busi.handlers.hicatcard.card;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gzyct.m.api.busi.bean.hicatcard.card.CardCollectReq;
import com.gzyct.m.api.busi.bean.hicatcard.card.CardCollectResp;
import com.gzyct.m.api.busi.config.BusiError;
import com.gzyct.m.api.busi.db.entity.hicatcard.user.TUser;
import com.gzyct.m.api.busi.db.entity.hicatcard.user.TUserCard;
import com.gzyct.m.api.busi.db.service.hicatcard.user.UserCardService;
import com.gzyct.m.api.busi.db.service.hicatcard.user.UserService;
import com.gzyct.m.api.busi.handlers.DefaultBizParamChecker;
import com.gzyct.m.api.busi.util.TimeUtil;
import com.gzyct.m.api.busi.util.IdGenerator.IdGenerator;
import com.gzyct.m.api.validates.ValidateParam;
import com.gzyct.m.api.validates.ValidateRet;
import com.gzyct.m.api.validates.ValidateType;
import com.gzyct.m.api.validates.ValidateUtil;
import com.project.m.api.common.biz.BizHandler;

@Component
public class CardCollectBizHandler extends DefaultBizParamChecker<CardCollectReq, CardCollectResp>
		implements BizHandler<CardCollectReq, CardCollectResp> {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	UserService userService;

	@Autowired
	UserCardService userCardService;

	@Override
	public CardCollectResp handle(CardCollectReq bizRequest) throws Exception {
		// TODO Auto-generated method stub
		CardCollectResp bizResp = new CardCollectResp();
		// 参数检查
		ValidateRet vRet = checkParam(bizRequest, bizResp);
		if (!vRet.isValid()) {
			return bizResp;
		}

		try {
			Long userCardId = Long.parseLong(bizRequest.getUser_card_id());

			// 找用户
			// 1. 检查用户是否存在
			// 验证用户
			List<TUser> userList = userService.findByOpenidAndEnable(bizRequest.getOpenid(), true);
			if (userList == null || userList.size() == 0) {
				bizResp.setResult_code(BusiError.ERR_CODE_USER_NONE);
				bizResp.setError_message(BusiError.ERR_MSG_USER_NONE);
				return bizResp;
			}

			TUser user = userList.get(0);

			// 找user_card
			// 2. user_card_id对应的用户是否一样
			TUserCard userCard = userCardService.findByUserCardIdAndEnable(userCardId, true);
			if (userCard == null) {
				bizResp.setResult_code(BusiError.ERR_CODE_USER_CARD_NOT_EXIST);
				bizResp.setError_message(BusiError.ERR_MSG_USER_CARD_NOT_EXIST);
				return bizResp;
			}

			// 比较状态
			if (userCard.getStatus().equalsIgnoreCase(TUserCard.STATUS_PRESENTED)) {
				bizResp.setResult_code(BusiError.ERR_CODE_USER_CARD_HAS_PRESENT);
				bizResp.setError_message(BusiError.ERR_MSG_USER_CARD_HAS_PRESENT);
				return bizResp;
			}

			// 比较状态
			if (userCard.getStatus().equalsIgnoreCase(TUserCard.STATUS_ACTIVE)) {
				bizResp.setResult_code(BusiError.ERR_CODE_USER_CARD_IS_ACTIVATED);
				bizResp.setError_message(BusiError.ERR_MSG_USER_CARD_IS_ACTIVATED);
				return bizResp;
			}

			// 比较userId
			if (user.getUserId().longValue() == userCard.getUserId().longValue()) {
				bizResp.setResult_code(BusiError.ERR_CODE_USER_CARD_USER_SAME);
				bizResp.setError_message(BusiError.ERR_MSG_USER_CARD_USER_SAME);
				return bizResp;
			}

			// 更新 - 新增 t_user_card + t_user_card_service t_card_transfer
			userCard.setStatus(TUserCard.STATUS_PRESENTED);
			userCard.setUpdateTime(TimeUtil.getCurrTime());

			TUserCard userCardInsert = new TUserCard();
			userCardInsert.setUserId(user.getUserId());
			userCardInsert.setCardId(userCard.getCardId());
			userCardInsert.setEndTime(userCard.getEndTime());
			userCardInsert
					.setUserCardNumber(IdGenerator.generateOrderId(IdGenerator.ORDERID_PREFIX_USER_CARD, "00", "0000"));
			userCardInsert.setSource(TUserCard.SOURCE_PRESENT);
			userCardInsert.setSourceUserId(userCard.getUserId());
			userCardInsert.setStatus(TUserCard.STATUS_NOT_ACTIVE);
			userCardInsert.setCreateTime(TimeUtil.getCurrTime());
			userCardInsert.setUpdateTime(TimeUtil.getCurrTime());
			userCardInsert.setEnable(true);

			userCardService.saveUserCardByCollect(userCard, userCardInsert);
			bizResp.setResult_code(BusiError.ERR_CODE_SUCCESS);
			bizResp.setError_message("");
			return bizResp;
		} catch (Exception e) {
			logger.error("CardCollectBizHandler", e);
			bizResp.setResult_code(BusiError.ERR_CODE_DB_SAVE);
			bizResp.setError_message(BusiError.ERR_MSG_DB_SAVE);
			return bizResp;
		}
	}

	@Override
	public ValidateRet checkParam(CardCollectReq req, CardCollectResp resp) {
		ValidateRet vRet = super.checkParam(req, resp);
		if (!vRet.isValid())
			return vRet;
		List<ValidateParam> paramList = new ArrayList<ValidateParam>();
		paramList.add(new ValidateParam("user_card_id", ValidateType.NOTBLANK, req.getUser_card_id()));

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
