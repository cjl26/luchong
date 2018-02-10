package com.project.m.api.common.biz.req;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class BizRequest extends BaseRequest {

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
