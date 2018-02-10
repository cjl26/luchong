package com.project.m.api.common.biz;

import com.project.m.api.common.biz.req.BizRequest;
import com.project.m.api.common.biz.resp.BizResp;

public interface BizHanlderConfig {

	BizHandlerConfigItem<BizRequest, BizResp> getItemConfig(String service);

}
