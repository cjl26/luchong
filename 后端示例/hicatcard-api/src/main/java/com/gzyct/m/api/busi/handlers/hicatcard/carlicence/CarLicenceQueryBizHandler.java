package com.gzyct.m.api.busi.handlers.hicatcard.carlicence;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gzyct.m.api.busi.bean.hicatcard.carlicence.CarLicenceQueryReq;
import com.gzyct.m.api.busi.bean.hicatcard.carlicence.CarLicenceQueryResp;
import com.gzyct.m.api.busi.config.BusiError;
import com.gzyct.m.api.busi.db.entity.hicatcard.user.TUser;
import com.gzyct.m.api.busi.db.entity.hicatcard.user.TUserCarLicence;
import com.gzyct.m.api.busi.db.service.hicatcard.user.UserCarLicenceService;
import com.gzyct.m.api.busi.db.service.hicatcard.user.UserService;
import com.gzyct.m.api.busi.handlers.DefaultBizParamChecker;
import com.gzyct.m.api.validates.ValidateParam;
import com.gzyct.m.api.validates.ValidateRet;
import com.gzyct.m.api.validates.ValidateUtil;
import com.project.m.api.common.biz.BizHandler;

@Component
public class CarLicenceQueryBizHandler extends DefaultBizParamChecker<CarLicenceQueryReq, CarLicenceQueryResp>
		implements BizHandler<CarLicenceQueryReq, CarLicenceQueryResp> {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	UserService userService;

	@Autowired
	UserCarLicenceService userCarLicenceService;

	@Override
	public CarLicenceQueryResp handle(CarLicenceQueryReq bizRequest) throws Exception {
		// TODO Auto-generated method stub
		CarLicenceQueryResp bizResp = new CarLicenceQueryResp();
		// 参数检查
		ValidateRet vRet = checkParam(bizRequest, bizResp);
		if (!vRet.isValid()) {
			return bizResp;
		}

		try {
			// 找用户
			List<TUser> userList = userService.findByOpenidAndEnable(bizRequest.getOpenid(), true);
			if (userList == null || userList.size() == 0) {
				bizResp.setResult_code(BusiError.ERR_CODE_USER_NONE);
				bizResp.setError_message(BusiError.ERR_MSG_USER_NONE);
				return bizResp;
			}
			TUser user = userList.get(0);

			// 更新
			List<TUserCarLicence> userCarLicenceList = userCarLicenceService.findByUserIdAndEnable(user.getUserId(),
					true);
			if (userCarLicenceList == null || userCarLicenceList.size() == 0) {
				bizResp.setResult_code(BusiError.ERR_CODE_SUCCESS);
				bizResp.setError_message(BusiError.ERR_MSG_CAR_LICENCE_NOT_EXIST);
				return bizResp;
			}
			TUserCarLicence userCarLicence = userCarLicenceList.get(0);
			bizResp.setCar_licence(userCarLicence.getCarLicenceNumber());
			bizResp.setResult_code(BusiError.ERR_CODE_SUCCESS);
			bizResp.setError_message("");
			return bizResp;
		} catch (Exception e) {
			logger.error("CarLicenceQueryBizHandler", e);
			bizResp.setResult_code(BusiError.ERR_CODE_DB_SAVE);
			bizResp.setError_message(BusiError.ERR_MSG_DB_SAVE);
			return bizResp;
		}
	}

	@Override
	public ValidateRet checkParam(CarLicenceQueryReq req, CarLicenceQueryResp resp) {
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
