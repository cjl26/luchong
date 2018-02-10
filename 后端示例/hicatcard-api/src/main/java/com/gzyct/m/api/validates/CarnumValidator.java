package com.gzyct.m.api.validates;

public class CarnumValidator implements Validator {

	@Override
	public ValidateRet validate(String paramName, Object... paramObj) {
		
		Object paramObj1 = paramObj[0];

		if (paramObj1 == null) {
			return new ValidateRet(false, "错误的参数" + paramName);
		} else {
			if (paramObj1 instanceof String) {
				String param = (String)paramObj1;
				param = param.trim();
				if (param.equals("")) {
					return new ValidateRet(false, "错误的参数" + paramName);
				}
				int len = param.length();
				if(len==16 || len==10 || len==9 || len==8){
					if(!isNumeric(param)){
						return new ValidateRet(false, "错误的参数" + paramName);
					}
				}else{
					return new ValidateRet(false, "错误的参数" + paramName);
				}
			}
		}
		
		return null;
	}
	
	private boolean isNumeric(String str){
		return str.matches("[-+]?\\d*\\.?\\d+");
	}

}
