package com.gzyct.m.api.busi.handlers.hicatcard.user;

import com.gzyct.m.api.busi.bean.hicatcard.user.UserCardItem;
import com.gzyct.m.api.busi.bean.hicatcard.user.UserCardListReq;
import com.gzyct.m.api.busi.bean.hicatcard.user.UserCardListResp;
import com.gzyct.m.api.busi.config.BusiError;
import com.gzyct.m.api.busi.db.entity.hicatcard.user.TUser;
import com.gzyct.m.api.busi.db.entity.hicatcard.user.TUserCard;
import com.gzyct.m.api.busi.db.service.hicatcard.user.UserCardService;
import com.gzyct.m.api.busi.db.service.hicatcard.user.UserService;
import com.gzyct.m.api.busi.handlers.DefaultBizParamChecker;
import com.gzyct.m.api.busi.util.CommonConvertor;
import com.gzyct.m.api.validates.ValidateParam;
import com.gzyct.m.api.validates.ValidateRet;
import com.gzyct.m.api.validates.ValidateType;
import com.gzyct.m.api.validates.ValidateUtil;
import com.project.m.api.common.biz.BizHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserCardListBizHandler extends DefaultBizParamChecker<UserCardListReq, UserCardListResp>
		implements BizHandler<UserCardListReq, UserCardListResp> {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	UserService userService;

	@Autowired
	UserCardService userCardService;

	@Override
	public UserCardListResp handle(UserCardListReq bizRequest) throws Exception {
		UserCardListResp bizResp = new UserCardListResp();
		// 参数检查
		ValidateRet vRet = checkParam(bizRequest, bizResp);
		if (!vRet.isValid()) {
			return bizResp;
		}

		try {
			// 判斷用戶是否存在
			List<TUser> userList = userService.findByOpenidAndEnable(bizRequest.getOpenid(), true);
			if (userList == null || userList.size() == 0) {
				bizResp.setResult_code(BusiError.ERR_CODE_USER_NONE);
				bizResp.setError_message(BusiError.ERR_MSG_USER_NONE);
				return bizResp;
			}
			TUser user = userList.get(0);

			// 根据 source/ status 来取数据
			Sort.Order order = new Sort.Order(Sort.Direction.DESC, "createTime");
			Sort sort = new Sort(order);
			Pageable pageable = new PageRequest(bizRequest.getPage() - 1, bizRequest.getPagesize(), sort);
			List<UserCardItem> userCardItemList = userCardService.findUserCardItemList(user.getUserId(),
					bizRequest.getSource(), bizRequest.getStatus(), bizRequest.getAttach(), true, pageable);

			bizResp.setResult_code(BusiError.ERR_CODE_SUCCESS);
			bizResp.setError_message("");
			bizResp.setDataList(userCardItemList);
			return bizResp;

		} catch (Exception e) {
			logger.error("UserCardListBizHandler", e);
			bizResp.setResult_code(BusiError.ERR_CODE_DB_SAVE);
			bizResp.setError_message(BusiError.ERR_MSG_DB_SAVE);
			return bizResp;
		}

	}

	@Override
	public ValidateRet checkParam(UserCardListReq req, UserCardListResp resp) {
		ValidateRet vRet = super.checkParam(req, resp);
		if (!vRet.isValid())
			return vRet;

		List<ValidateParam> paramList = new ArrayList<ValidateParam>();
		paramList.add(new ValidateParam("page", ValidateType.NOTBLANK, req.getPage()));
		paramList.add(new ValidateParam("page", ValidateType.INT, req.getPage()));
		paramList.add(new ValidateParam("pagesize", ValidateType.NOTBLANK, req.getPagesize()));
		paramList.add(new ValidateParam("pagesize", ValidateType.INT, req.getPagesize()));

		vRet = ValidateUtil.validate(paramList);

		if (vRet == null) {
			vRet = new ValidateRet(true, "");
		}

		req.setStatus(CommonConvertor.convertStringAvoidNull(req.getStatus()));
		if (!req.getStatus().isEmpty()) {
			if (!TUserCard.statusValidate(req.getStatus())) {
				vRet = new ValidateRet(false, "status " + BusiError.ERR_MSG_PARAM_BAD_REQ);
			}
		}

		req.setSource(CommonConvertor.convertStringAvoidNull(req.getSource()));
		if (!req.getSource().isEmpty()) {
			if (!TUserCard.sourceValidate(req.getSource())) {
				vRet = new ValidateRet(false, "source " + BusiError.ERR_MSG_PARAM_BAD_REQ);
			}
		}

		if (!vRet.isValid()) {
			resp.setResult_code(BusiError.ERR_CODE_PARAM_BAD_REQ);
			resp.setError_message(vRet.getErrMsg());
		}

		return vRet;
	}
}
