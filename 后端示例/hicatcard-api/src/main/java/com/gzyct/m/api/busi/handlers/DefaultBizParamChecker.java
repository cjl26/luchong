package com.gzyct.m.api.busi.handlers;

import java.util.ArrayList;
import java.util.List;

import com.gzyct.m.api.busi.config.BusiError;
import com.project.m.api.common.biz.req.BizRequest;
import com.project.m.api.common.biz.resp.BizResp;
import com.gzyct.m.api.validates.ValidateParam;
import com.gzyct.m.api.validates.ValidateRet;
import com.gzyct.m.api.validates.ValidateType;
import com.gzyct.m.api.validates.ValidateUtil;

/**
 * 普通公共参数检查
 * 
 * @author
 *
 * @param <R>
 * @param <S>
 */
public class DefaultBizParamChecker<R extends BizRequest, S extends BizResp> implements BizParamChecker<R, S> {

	@Override
	public ValidateRet checkParam(R req, S resp) {
		List<ValidateParam> paramList = new ArrayList<ValidateParam>();

		paramList.add(new ValidateParam("openid", ValidateType.NOTBLANK, req.getOpenid()));
		paramList.add(new ValidateParam("api_version", ValidateType.NOTBLANK, req.getApi_version()));
		paramList.add(new ValidateParam("charset", ValidateType.NOTBLANK, req.getCharset()));
		paramList.add(new ValidateParam("timestamp", ValidateType.NOTBLANK, req.getTimestamp()));
		paramList.add(new ValidateParam("service", ValidateType.NOTBLANK, req.getService()));
		paramList.add(new ValidateParam("brand", ValidateType.NOTBLANK, req.getBrand()));
		paramList.add(new ValidateParam("model", ValidateType.NOTBLANK, req.getModel()));
		paramList.add(new ValidateParam("language", ValidateType.NOTBLANK, req.getLanguage()));
		paramList.add(new ValidateParam("version", ValidateType.NOTBLANK, req.getVersion()));
		paramList.add(new ValidateParam("system", ValidateType.NOTBLANK, req.getSystem()));
		paramList.add(new ValidateParam("platform", ValidateType.NOTBLANK, req.getPlatform()));

		ValidateRet vRet = ValidateUtil.validate(paramList);
		if (vRet == null)
			return new ValidateRet(true, "");

		if (!vRet.isValid()) {
			resp.setResult_code(BusiError.ERR_CODE_PARAM_BAD_REQ);
			resp.setError_message(vRet.getErrMsg());
		}

		return vRet;
	}

}
