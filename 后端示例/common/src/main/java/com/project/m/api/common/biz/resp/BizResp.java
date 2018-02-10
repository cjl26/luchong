package com.project.m.api.common.biz.resp;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.project.m.api.common.msg.MessageContext;


public class BizResp extends BaseBizResp {

	public void success() {
		this.setResult_code(BaseBizResp.RESULT_SUCCESS);
		this.setError_message("");
	}

	public void error(String resultCode) {
		this.setResult_code(resultCode);
		this.setError_message(MessageContext.getMessage(resultCode));
	}

	public void error(String resultCode, Object[] args) {
		this.setResult_code(resultCode);
		this.setError_message(MessageContext.getMessage(resultCode, args));
	}

	public boolean checkSuccess() {
		return BaseBizResp.RESULT_SUCCESS.equals(this.getResult_code());
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
