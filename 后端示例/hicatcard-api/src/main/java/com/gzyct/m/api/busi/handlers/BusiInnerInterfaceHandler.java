package com.gzyct.m.api.busi.handlers;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.project.m.api.common.biz.BizHanlderConfig;
import com.project.m.api.common.intf.DefaultInterfaceHandler;

/**
 * 登录状态的接口处理
 * @author zoudb
 *
 */
@Component
public class BusiInnerInterfaceHandler extends DefaultInterfaceHandler{
	
	@Override
	@Resource(name = "busiInnerBizHandlerConfig")
	public void setBizHanlderConfig(BizHanlderConfig bizHanlderConfig) {
		super.setBizHanlderConfig(bizHanlderConfig);
	}
}
