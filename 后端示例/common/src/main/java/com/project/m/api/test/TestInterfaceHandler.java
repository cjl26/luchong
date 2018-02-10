package com.project.m.api.test;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.project.m.api.common.biz.BizHanlderConfig;
import com.project.m.api.common.intf.DefaultInterfaceHandler;

@Component
public class TestInterfaceHandler extends DefaultInterfaceHandler {

	@Override
	@Resource(name = "testBizHandlerConfig")
	public void setBizHanlderConfig(BizHanlderConfig bizHanlderConfig) {
		super.setBizHanlderConfig(bizHanlderConfig);
	}

}
