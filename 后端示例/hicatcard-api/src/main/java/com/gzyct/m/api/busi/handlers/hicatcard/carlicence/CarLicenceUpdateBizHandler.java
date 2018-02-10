package com.gzyct.m.api.busi.handlers.hicatcard.carlicence;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gzyct.m.api.busi.bean.hicatcard.carlicence.CarLicenceUpdateReq;
import com.gzyct.m.api.busi.bean.hicatcard.carlicence.CarLicenceUpdateResp;
import com.gzyct.m.api.busi.config.BusiError;
import com.gzyct.m.api.busi.db.entity.hicatcard.user.TUser;
import com.gzyct.m.api.busi.db.entity.hicatcard.user.TUserCarLicence;
import com.gzyct.m.api.busi.db.service.hicatcard.user.UserCarLicenceService;
import com.gzyct.m.api.busi.db.service.hicatcard.user.UserService;
import com.gzyct.m.api.busi.handlers.DefaultBizParamChecker;
import com.gzyct.m.api.busi.util.CommonConvertor;
import com.gzyct.m.api.busi.util.TimeUtil;
import com.gzyct.m.api.validates.ValidateParam;
import com.gzyct.m.api.validates.ValidateRet;
import com.gzyct.m.api.validates.ValidateType;
import com.gzyct.m.api.validates.ValidateUtil;
import com.project.m.api.common.biz.BizHandler;

@Component
public class CarLicenceUpdateBizHandler extends DefaultBizParamChecker<CarLicenceUpdateReq, CarLicenceUpdateResp>
		implements BizHandler<CarLicenceUpdateReq, CarLicenceUpdateResp> {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	UserService userService;

	@Autowired
	UserCarLicenceService userCarLicenceService;

	@Override
	public CarLicenceUpdateResp handle(CarLicenceUpdateReq bizRequest) throws Exception {
		// TODO Auto-generated method stub
		CarLicenceUpdateResp bizResp = new CarLicenceUpdateResp();
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

			// 根据操作类型处理
			TUserCarLicence userCarLicence = null;
			if (bizRequest.getType().equalsIgnoreCase(CarLicenceUpdateReq.TYPE_UPDATE)) {
				// 更新
				List<TUserCarLicence> userCarLicenceList = userCarLicenceService.findByUserIdAndEnable(user.getUserId(),
						true);
				if (userCarLicenceList == null || userCarLicenceList.size() == 0) {
					userCarLicence = new TUserCarLicence();
					userCarLicence.setUserId(user.getUserId());
					userCarLicence.setCarLicenceNumber(bizRequest.getCar_licence());
					userCarLicence.setCreateTime(TimeUtil.getCurrTime());
					userCarLicence.setEnable(true);
					userCarLicenceService.saveUserCarLicence(userCarLicence);
					bizResp.setResult_code(BusiError.ERR_CODE_SUCCESS);
					return bizResp;
				}
				userCarLicence = userCarLicenceList.get(0);
				userCarLicence.setCarLicenceNumber(bizRequest.getCar_licence());
			} else {				
				// 新增
				List<TUserCarLicence> userCarLicenceList = userCarLicenceService.findByUserIdAndEnable(user.getUserId(),
						true);
				if (userCarLicenceList != null && userCarLicenceList.size() > 0) {
					bizResp.setResult_code(BusiError.ERR_CODE_CAR_LICENCE_HAS_EXIST);
					bizResp.setError_message(BusiError.ERR_MSG_CAR_LICENCE_HAS_EXIST);
					return bizResp;
				}
				
				userCarLicence = new TUserCarLicence();
				userCarLicence.setUserId(user.getUserId());
				userCarLicence.setCarLicenceNumber(bizRequest.getCar_licence());
				userCarLicence.setCreateTime(TimeUtil.getCurrTime());
				userCarLicence.setEnable(true);
			}
			userCarLicence.setUpdateTime(TimeUtil.getCurrTime());
			userCarLicenceService.saveUserCarLicence(userCarLicence);

			bizResp.setResult_code(BusiError.ERR_CODE_SUCCESS);
			bizResp.setError_message("");
			return bizResp;
			// 保存数据
		} catch (Exception e) {
			logger.error("CarLicenceUpdateBizHandler", e);
			bizResp.setResult_code(BusiError.ERR_CODE_DB_SAVE);
			bizResp.setError_message(BusiError.ERR_MSG_DB_SAVE);
			return bizResp;
		}
	}

	@Override
	public ValidateRet checkParam(CarLicenceUpdateReq req, CarLicenceUpdateResp resp) {
		ValidateRet vRet = super.checkParam(req, resp);
		if (!vRet.isValid())
			return vRet;
		List<ValidateParam> paramList = new ArrayList<ValidateParam>();
		paramList.add(new ValidateParam("type", ValidateType.NOTBLANK, req.getType()));
		paramList.add(new ValidateParam("car_licence", ValidateType.NOTBLANK, req.getCar_licence()));

		vRet = ValidateUtil.validate(paramList);
		if (vRet == null) {
			vRet = new ValidateRet(true, "");
		}

		req.setType(CommonConvertor.convertStringAvoidNull(req.getType()));
		if (!req.getType().isEmpty()) {
			if (!CarLicenceUpdateReq.typeValidate(req.getType())) {
				vRet = new ValidateRet(false, "type " + BusiError.ERR_MSG_PARAM_BAD_REQ);
			}
		}

		if (!vRet.isValid()) {
			resp.setResult_code(BusiError.ERR_CODE_PARAM_BAD_REQ);
			resp.setError_message(vRet.getErrMsg());
		}

		return vRet;
	}

}
