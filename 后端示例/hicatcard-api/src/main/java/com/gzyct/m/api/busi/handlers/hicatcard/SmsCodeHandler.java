package com.gzyct.m.api.busi.handlers.hicatcard;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;

import com.gzyct.m.api.busi.bean.hicatcard.SmsCodeReq;
import com.gzyct.m.api.busi.bean.hicatcard.SmsCodeResp;
import com.gzyct.m.api.busi.config.BusiError;
import com.gzyct.m.api.busi.db.entity.hicatcard.TSmsCode;
import com.gzyct.m.api.busi.db.service.hicatcard.SmsCodeService;
import com.gzyct.m.api.busi.handlers.DefaultBizParamChecker;
import com.gzyct.m.api.busi.util.SmsCodeUtil;
import com.gzyct.m.api.busi.util.SmsCodeUtil.SmsCodeResult;
import com.gzyct.m.api.busi.util.TimeUtil;
import com.gzyct.m.api.validates.ValidateParam;
import com.gzyct.m.api.validates.ValidateRet;
import com.gzyct.m.api.validates.ValidateType;
import com.gzyct.m.api.validates.ValidateUtil;
import com.project.m.api.common.biz.BizHandler;

/**
 * 获取短信验证码
 * 
 * @author cjl
 *
 */

@Component
// @RefreshScope
public class SmsCodeHandler extends DefaultBizParamChecker<SmsCodeReq, SmsCodeResp>
		implements BizHandler<SmsCodeReq, SmsCodeResp> {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	SmsCodeService smsCodeService;

	@Override
	public SmsCodeResp handle(SmsCodeReq bizRequest) throws Exception {
		// TODO Auto-generated method stub

		SmsCodeResp bizResp = new SmsCodeResp();
		// 参数检查
		ValidateRet vRet = checkParam(bizRequest, bizResp);
		if (!vRet.isValid()) {
			return bizResp;
		}
		try {
			String code = Long.toString(Math.round(Math.random() * 9000 + 1000));
		    String messageToSend = "您的验证码是:" + code + "， 15分钟内有效。";
			TSmsCode smsCode = null;
			
			// TODO 发送短信 等接口
			SmsCodeResult result =  SmsCodeUtil.sendSmsCode(bizRequest.getPhone(),messageToSend);
			
			logger.info("smsContent: " + messageToSend);
			logger.info("smsPhone: " + result.getMobile());
			logger.info("smsCount: " + result.getCount());
			logger.info("smsErrMsg: " + result.getErrMsg());
			
			//如果发送短信验证码失败
			if(result.getCount() < 1) {
				bizResp.setResult_code(BusiError.ERR_CODE_GET_SMSCODE_FAIL);
				bizResp.setError_message(BusiError.ERR_MSG_GET_SMSCODE_FAIL);
				return bizResp;
			}
									
			// 查找phone+type有无数据
			List<TSmsCode> smsCodeList = smsCodeService.findByTypeAndPhone(bizRequest.getType(), bizRequest.getPhone());
			if (smsCodeList.size() > 0) {
				// 有数据全部设置为无效
				for (TSmsCode smsCodeItem : smsCodeList) {
					smsCodeItem.setEnable(TSmsCode.DISABLE);
					smsCodeItem.setUpdateTime(TimeUtil.getCurrTime());
				}
				smsCodeService.saveSmsCodeList(smsCodeList);
				smsCode = smsCodeList.get(0);
			} else {
				// 新增
				smsCode = new TSmsCode();
				smsCode.setPhone(bizRequest.getPhone());
				smsCode.setType(bizRequest.getType());
			}

			smsCode.setCode(code);
			Date now = new Date();
			smsCode.setExpireTime(
					TimeUtil.getFormatTime(new Date(now.getTime() + 15 * 60 * 1000), TimeUtil.DATE_PATTERN_NOSEPARTOR));
			smsCode.setUpdateTime(TimeUtil.getCurrTime());
			smsCode.setCreateTime(TimeUtil.getCurrTime());
			smsCode.setEnable(TSmsCode.ENABLE);
			smsCodeService.saveSmsCode(smsCode);

			bizResp.setResult_code(BusiError.ERR_CODE_SUCCESS);
			bizResp.setError_message("");
			return bizResp;

		} catch (Exception ex) {
			logger.error("内部出错", ex);
			bizResp.setResult_code(BusiError.ERR_CODE_DB_CONF);
			bizResp.setError_message(BusiError.ERR_MSG_DB_CONF);
			return bizResp;
		}
	}

	@Override
	public ValidateRet checkParam(SmsCodeReq req, SmsCodeResp resp) {
		ValidateRet vRet = super.checkParam(req, resp);
		if (!vRet.isValid())
			return vRet;

		List<ValidateParam> paramList = new ArrayList<ValidateParam>();
		paramList.add(new ValidateParam("phone", ValidateType.NOTBLANK, req.getPhone()));
		paramList.add(new ValidateParam("type", ValidateType.NOTBLANK, req.getType()));
		vRet = ValidateUtil.validate(paramList);

		if (vRet == null) {
			vRet = new ValidateRet(true, "");
		}

		if (!TSmsCode.typeValidate(req.getType())) {
			vRet = new ValidateRet(false, BusiError.ERR_MSG_TYPE_ERROR);
		}

		if (!vRet.isValid()) {
			resp.setResult_code(BusiError.ERR_CODE_PARAM_BAD_REQ);
			resp.setError_message(vRet.getErrMsg());
		}

		return vRet;
	}

}
