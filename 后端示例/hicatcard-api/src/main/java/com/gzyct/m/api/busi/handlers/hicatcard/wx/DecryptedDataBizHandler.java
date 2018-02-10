package com.gzyct.m.api.busi.handlers.hicatcard.wx;


import com.alibaba.fastjson.JSON;
import com.gzyct.m.api.busi.bean.WxGetPhoneResult;
import com.gzyct.m.api.busi.bean.ext.DecryptDataReq;
import com.gzyct.m.api.busi.bean.ext.DecryptDataResp;
import com.gzyct.m.api.busi.bean.ext.DecryptDataReq;
import com.gzyct.m.api.busi.bean.ext.DecryptDataResp;
import com.gzyct.m.api.busi.config.BusiError;
import com.gzyct.m.api.busi.db.entity.hicatcard.user.TUser;
import com.gzyct.m.api.busi.db.service.hicatcard.user.UserService;
import com.gzyct.m.api.busi.handlers.DefaultBizParamChecker;
import com.gzyct.m.api.validates.ValidateParam;
import com.gzyct.m.api.validates.ValidateRet;
import com.gzyct.m.api.validates.ValidateType;
import com.gzyct.m.api.validates.ValidateUtil;
import com.jfinal.aop.Duang;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.encrypt.WxaBizDataCrypt;
import com.jfinal.wxaapp.api.WxaUserApi;
import com.project.m.api.common.biz.BizHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class DecryptedDataBizHandler extends DefaultBizParamChecker<DecryptDataReq, DecryptDataResp> implements BizHandler<DecryptDataReq, DecryptDataResp> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	UserService userService;

	@Override
	public DecryptDataResp handle(DecryptDataReq req) throws Exception {
		DecryptDataResp bizResp = new DecryptDataResp();
		ValidateRet vRet = checkParam(req, bizResp);
		if(!vRet.isValid()){
			return bizResp;
		}
		WxaBizDataCrypt dataCrypt = new WxaBizDataCrypt(req.getSessionKey());
		String json = dataCrypt.decrypt(req.getEncryptedData(), req.getIvStr());
		logger.info("json:"+json+","+"apiResult:"+json);
		try{
			WxGetPhoneResult wxGetPhoneResult = JSON.parseObject(json,WxGetPhoneResult.class);
			//更新用户手机号
			List<TUser> userList = userService.findByOpenidAndEnable(req.getOpenid(), true);
			if(userList != null || userList.size() > 0)
			{
				TUser user = userList.get(0);
				user.setPhone(wxGetPhoneResult.getPurePhoneNumber());
				userService.saveUser(user);
			}
			bizResp.setDecryptedData(wxGetPhoneResult.getPurePhoneNumber());
			bizResp.setResult_code(BusiError.ERR_CODE_SUCCESS);
			return bizResp;
		}catch (Exception e){
			bizResp.setResult_code(BusiError.ERR_CODE_NET_JSON);
			bizResp.setError_message(BusiError.ERR_MSG_NET_JSON);
			return bizResp;
		}



	}
	

	@Override
	public ValidateRet checkParam(DecryptDataReq req, DecryptDataResp resp) {
		ValidateRet vRet = super.checkParam(req, resp);
		if(!vRet.isValid())return vRet;
		
		List<ValidateParam> paramList = new ArrayList<ValidateParam>();
		paramList.add(new ValidateParam("EncryptedData", ValidateType.NOTBLANK, req.getEncryptedData()));
		paramList.add(new ValidateParam("IvStr", ValidateType.NOTBLANK, req.getIvStr()));
		paramList.add(new ValidateParam("SessionKey", ValidateType.NOTBLANK, req.getSessionKey()));
		vRet = ValidateUtil.validate(paramList);
		
		if(vRet==null)vRet = new ValidateRet(true, "");
		if(!vRet.isValid()){
			resp.setResult_code(BusiError.ERR_CODE_PARAM_BAD_REQ);
			resp.setError_message(vRet.getErrMsg());
		}
		return vRet;
	}
}
