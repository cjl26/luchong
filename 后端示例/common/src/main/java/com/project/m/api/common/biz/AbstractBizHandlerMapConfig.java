package com.project.m.api.common.biz;

import java.util.HashMap;
import java.util.Map;

import com.project.m.api.common.biz.req.BizRequest;
import com.project.m.api.common.biz.resp.BizResp;

public abstract class AbstractBizHandlerMapConfig implements BizHanlderConfig {

	protected Map<String, BizHandlerConfigItem<? extends BizRequest, ? extends BizResp>> map = new HashMap<String, BizHandlerConfigItem<? extends BizRequest, ? extends BizResp>>();

	private volatile boolean isInited = false;

	@SuppressWarnings("unchecked")
	@Override
	public BizHandlerConfigItem<BizRequest, BizResp> getItemConfig(String service) {
		if (!isInited) {
			doInit();
		}
		return (BizHandlerConfigItem<BizRequest, BizResp>) map.get(service);
	}

	synchronized private void doInit() {
		if (!isInited) {
			init();
		}
		isInited = true;
	}

	abstract protected void init();

}
