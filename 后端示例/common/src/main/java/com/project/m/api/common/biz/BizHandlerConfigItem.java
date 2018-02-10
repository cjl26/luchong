package com.project.m.api.common.biz;

import com.project.m.api.common.biz.req.BizRequest;
import com.project.m.api.common.biz.req.BizRequestParser;
import com.project.m.api.common.biz.resp.BizResp;

public class BizHandlerConfigItem<R extends BizRequest, P extends BizResp> {

	private BizRequestParser<BizRequest> bizRequestParser;
	private BizHandler<R, P> bizHanlder;

	public BizHandlerConfigItem() {

	}

	public BizHandlerConfigItem(BizRequestParser<BizRequest> bizRequestParser, BizHandler<R, P> bizHanlder) {
		this.bizRequestParser = bizRequestParser;
		this.bizHanlder = bizHanlder;
	}

	public BizRequestParser<BizRequest> getBizRequestParser() {
		return bizRequestParser;
	}

	public void setBizRequestParser(BizRequestParser<BizRequest> bizRequestParser) {
		this.bizRequestParser = bizRequestParser;
	}

	public BizHandler<R, P> getBizHanlder() {
		return bizHanlder;
	}

	public void setBizHanlder(BizHandler<R, P> bizHanlder) {
		this.bizHanlder = bizHanlder;
	}

}
