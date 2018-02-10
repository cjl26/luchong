package com.project.m.api.common.biz.req;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.m.api.common.intf.req.InterfaceRequest;
import com.project.m.api.common.util.JacksonObjectMapper;

public class JsonBizRequestParser implements BizRequestParser<BizRequest> {

	private Class<? extends BizRequest> bizRequestClass;

	public JsonBizRequestParser(Class<? extends BizRequest> bizRequestClass) {
		this.bizRequestClass = bizRequestClass;
	}

	@Override
	public BizRequest parse(InterfaceRequest ir) throws Exception {
		ObjectMapper objectMapper = JacksonObjectMapper.getDefault();
		return objectMapper.readValue(ir.getRequestBody(), bizRequestClass);
	}
}
