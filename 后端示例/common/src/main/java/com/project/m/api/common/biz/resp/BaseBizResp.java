package com.project.m.api.common.biz.resp;

public class BaseBizResp extends BaseResp {

	public static final String RESULT_SUCCESS = "0";

	private String result_code;
	private String error_message = "";

	public String getResult_code() {
		return result_code;
	}

	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}

	public String getError_message() {
		return error_message;
	}

	public void setError_message(String error_message) {
		this.error_message = error_message;
	}

}
