package com.gzyct.m.api.validates;


public class NotBlankValidator implements Validator {

	public ValidateRet validate(String paramName, Object... paramObj) {

		
//		Object paramObj1 = paramObj[0];
//
//		if (paramObj1 == null) {
//			return new ValidateRet(false, "错误的参数" + paramName);
//		} else {
//			if (paramObj1 instanceof String) {
//				if (((String) paramObj1).trim().equals("")) {
//					return new ValidateRet(false, "错误的参数" + paramName);
//				}
//			}
//		}
//
//		return null;
//		
		
		


		for (Object paramObj1 : paramObj) {

			if (paramObj1 == null) {
				return new ValidateRet(false, "错误的参数" + paramName);
			} else {
				if (paramObj1 instanceof String) {
					if (((String) paramObj1).trim().equals("")) {
						return new ValidateRet(false, "错误的参数" + paramName);
					}
				}
			}
		}
		return null;
	
	}

}
