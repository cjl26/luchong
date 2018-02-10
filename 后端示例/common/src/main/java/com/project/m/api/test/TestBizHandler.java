package com.project.m.api.test;

import org.springframework.stereotype.Component;

import com.project.m.api.common.biz.BizHandler;

@Component
public class TestBizHandler implements BizHandler<TestBizRequest, TestBizResp> {

	@Override
	public TestBizResp handle(TestBizRequest bizRequest) throws Exception {
		TestBizResp resp = new TestBizResp();
		resp.success();
		resp.setTestResult("test is ok");
		return resp;
	}

}
