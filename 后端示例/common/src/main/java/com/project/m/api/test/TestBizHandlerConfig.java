package com.project.m.api.test;

import org.springframework.stereotype.Component;

import com.project.m.api.common.biz.AbstractBizHandlerMapConfig;
import com.project.m.api.common.biz.BizHandlerConfigItem;
import com.project.m.api.common.biz.req.JsonBizRequestParser;
import com.project.m.api.common.util.SpringUtils;

@Component
public class TestBizHandlerConfig extends AbstractBizHandlerMapConfig {

	@Override
	protected void init() {
		map.put("testBiz", new BizHandlerConfigItem<TestBizRequest, TestBizResp>(new JsonBizRequestParser(
				TestBizRequest.class), SpringUtils.getBean(TestBizHandler.class)));
	}
}
