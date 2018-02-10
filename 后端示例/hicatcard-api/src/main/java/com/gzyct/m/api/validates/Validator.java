package com.gzyct.m.api.validates;

public interface Validator {

	ValidateRet validate(String paramName, Object... paramObj);

}
