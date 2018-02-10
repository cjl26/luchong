package com.project.m.api.common.biz;

import com.project.m.api.common.biz.req.BizRequest;
import com.project.m.api.common.biz.resp.BizResp;

public interface BizHandler<R extends BizRequest, P extends BizResp> {

	P handle(R bizRequest) throws Exception;

}
