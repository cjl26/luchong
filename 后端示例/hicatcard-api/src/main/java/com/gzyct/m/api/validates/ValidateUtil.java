package com.gzyct.m.api.validates;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidateUtil {

	private static Map<ValidateType, Validator> validatorMap = new HashMap<ValidateType, Validator>();

	static {
		register(ValidateType.NOTBLANK, new NotBlankValidator());
		register(ValidateType.INT, new IntValidator());
	}

	/**
	 * 验证通过时, 返回null
	 * @param paramList
	 * @return
	 */
	public static ValidateRet validate(List<ValidateParam> paramList) {
		for (ValidateParam param : paramList) {
			ValidateRet validateRet = doValidate(param);
			if (validateRet != null) {
				return validateRet;
			}
		}
		return null;
	}

	private static ValidateRet doValidate(ValidateParam param) {
		Validator validator = validatorMap.get(param.getValidateType());
		if (validator == null) {
			throw new RuntimeException("没有对应的Validator，对于类型" + param.getValidateType());
		}
		return validator.validate(param.getParamName(), param.getParamObjects());
	}

	private static void register(ValidateType validateType, Validator validator) {
		validatorMap.put(validateType, validator);
	}
}
