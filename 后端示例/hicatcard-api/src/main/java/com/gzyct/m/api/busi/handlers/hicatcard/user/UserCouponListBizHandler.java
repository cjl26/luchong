package com.gzyct.m.api.busi.handlers.hicatcard.user;

import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.gzyct.m.api.busi.bean.hicatcard.UserInfoReq;
import com.gzyct.m.api.busi.bean.hicatcard.UserInfoResp;
import com.gzyct.m.api.busi.bean.hicatcard.coupon.UserCouponItem;
import com.gzyct.m.api.busi.bean.hicatcard.coupon.UserCouponListReq;
import com.gzyct.m.api.busi.bean.hicatcard.coupon.UserCouponListResp;
import com.gzyct.m.api.busi.config.BusiError;
import com.gzyct.m.api.busi.db.entity.hicatcard.card.TCard;
import com.gzyct.m.api.busi.db.entity.hicatcard.coupon.TCoupon;
import com.gzyct.m.api.busi.db.entity.hicatcard.user.TUser;
import com.gzyct.m.api.busi.db.entity.hicatcard.user.TUserCoupon;
import com.gzyct.m.api.busi.db.service.hicatcard.card.CardService;
import com.gzyct.m.api.busi.db.service.hicatcard.user.UserCouponService;
import com.gzyct.m.api.busi.db.service.hicatcard.user.UserService;
import com.gzyct.m.api.busi.handlers.DefaultBizParamChecker;
import com.gzyct.m.api.busi.util.CommonConvertor;
import com.gzyct.m.api.validates.ValidateParam;
import com.gzyct.m.api.validates.ValidateRet;
import com.gzyct.m.api.validates.ValidateType;
import com.gzyct.m.api.validates.ValidateUtil;
import com.project.m.api.common.biz.BizHandler;

@Component
public class UserCouponListBizHandler extends DefaultBizParamChecker<UserCouponListReq, UserCouponListResp>
		implements BizHandler<UserCouponListReq, UserCouponListResp> {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	UserService userService;

	@Autowired
	CardService cardService;

	@Autowired
	UserCouponService userCouponService;

	@Override
	public UserCouponListResp handle(UserCouponListReq bizRequest) throws Exception {
		// TODO Auto-generated method stub
		UserCouponListResp bizResp = new UserCouponListResp();
		// 参数检查
		ValidateRet vRet = checkParam(bizRequest, bizResp);
		if (!vRet.isValid()) {
			return bizResp;
		}

		try {

			// 验证卡片
			if (!StringUtils.isNullOrEmpty(bizRequest.getCard_id())) {
				Long cardId = Long.parseLong(bizRequest.getCard_id());
				TCard card = cardService.findByCardIdAndEnable(cardId, true);
				if (card == null) {
					bizResp.setResult_code(BusiError.ERR_CODE_CARD_NOT_EXIST);
					bizResp.setError_message(BusiError.ERR_MSG_CARD_NOT_EXIST);
					return bizResp;
				}
			}

			// 验证用户
			List<TUser> userList = userService.findByOpenidAndEnable(bizRequest.getOpenid(), true);
			if (userList == null || userList.size() == 0) {
				bizResp.setResult_code(BusiError.ERR_CODE_USER_NONE);
				bizResp.setError_message(BusiError.ERR_MSG_USER_NONE);
				return bizResp;
			}

			TUser user = userList.get(0);
			logger.info("user id = " + user.getUserId());

			Sort.Order order = new Sort.Order(Sort.Direction.DESC, "createTime");
			Sort sort = new Sort(order);
			Pageable pageable = new PageRequest(bizRequest.getPage() - 1, bizRequest.getPagesize(), sort);

			// 获取用户优惠券列表
			List<UserCouponItem> userCouponItemList = userCouponService.findByStatusAndCardIdAndUserId(
					bizRequest.getStatus(), bizRequest.getType(), bizRequest.getCard_id(), user.getUserId(), pageable);

			bizResp.setCoupon(userCouponItemList);
			bizResp.setResult_code(BusiError.ERR_CODE_SUCCESS);
			bizResp.setError_message("");
			return bizResp;

		} catch (Exception e) {
			logger.error("CardOrderBizHandler", e);
			bizResp.setResult_code(BusiError.ERR_CODE_DB_SAVE);
			bizResp.setError_message(BusiError.ERR_MSG_DB_SAVE);
			return bizResp;
		}
	}

	@Override
	public ValidateRet checkParam(UserCouponListReq req, UserCouponListResp resp) {
		ValidateRet vRet = super.checkParam(req, resp);
		if (!vRet.isValid())
			return vRet;

		List<ValidateParam> paramList = new ArrayList<ValidateParam>();
		paramList.add(new ValidateParam("page", ValidateType.NOTBLANK, req.getPage()));
		paramList.add(new ValidateParam("pagesize", ValidateType.NOTBLANK, req.getPagesize()));

		vRet = ValidateUtil.validate(paramList);

		if (vRet == null) {
			vRet = new ValidateRet(true, "");
		}

		req.setStatus(CommonConvertor.convertStringAvoidNull(req.getStatus()));
		if (!req.getStatus().isEmpty()) {
			if (!TUserCoupon.statusValidate(req.getStatus())) {
				vRet = new ValidateRet(false, "status " + BusiError.ERR_MSG_PARAM_BAD_REQ);
			}
		}

		req.setType(CommonConvertor.convertStringAvoidNull(req.getType()));
		if (!req.getType().isEmpty()) {
			if (!TCoupon.typeValidate(req.getType())) {
				vRet = new ValidateRet(false, "type " + BusiError.ERR_MSG_PARAM_BAD_REQ);
			}
		}

		req.setCard_id(CommonConvertor.convertStringAvoidNull(req.getCard_id()));

		if (!vRet.isValid()) {
			resp.setResult_code(BusiError.ERR_CODE_PARAM_BAD_REQ);
			resp.setError_message(vRet.getErrMsg());
		}

		return vRet;
	}

}
