package com.gzyct.m.api.validates;

public class IntValidator implements Validator {

	@Override
	public ValidateRet validate(String paramName, Object... paramObj) {
		try {
			String s = ""+ paramObj[0];
			Integer.parseInt(s);
		} catch (Exception e) {
			return new ValidateRet(false, "错误的参数" + paramName);
		}
		return null;
	}

}
