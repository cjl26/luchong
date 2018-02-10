package com.gzyct.m.api.validates;

public class ValidateParam {

	private String paramName;
	private ValidateType validateType;
	private Object[] paramObjects;

	public ValidateParam(String paramName, ValidateType validateType, Object... paramObjects) {
		this.paramName = paramName;
		this.validateType = validateType;
		this.paramObjects = paramObjects;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public ValidateType getValidateType() {
		return validateType;
	}

	public void setValidateType(ValidateType validateType) {
		this.validateType = validateType;
	}

	public Object[] getParamObjects() {
		return paramObjects;
	}

	public void setParamObjects(Object[] paramObjects) {
		this.paramObjects = paramObjects;
	}

}
