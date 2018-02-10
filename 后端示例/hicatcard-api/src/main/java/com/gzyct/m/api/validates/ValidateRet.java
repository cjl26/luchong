package com.gzyct.m.api.validates;

public class ValidateRet {

	private boolean valid;
	private String errMsg;

	public ValidateRet(boolean valid, String errMsg) {
		this.valid = valid;
		this.errMsg = errMsg;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

}
