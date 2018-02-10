package com.gzyct.m.api.busi.handlers;

import com.project.m.api.common.biz.req.BizRequest;
import com.project.m.api.common.biz.resp.BizResp;
import com.gzyct.m.api.validates.ValidateRet;

public interface BizParamChecker <R extends BizRequest, S extends BizResp>{

	public ValidateRet checkParam(R req, S resp);
}
