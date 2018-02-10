package com.gzyct.m.api.busi.bean.hicatcard.carlicence;

import com.gzyct.m.api.busi.util.CommonConvertor;
import com.project.m.api.common.biz.req.BizRequest;

public class CarLicenceUpdateReq extends BizRequest {

	public static String TYPE_INSERT = "1"; // 新增
	public static String TYPE_UPDATE = "2"; // 更新

	static public boolean typeValidate(String typeInput) {
		if (CommonConvertor.convertStringAvoidNull(typeInput).equalsIgnoreCase(TYPE_INSERT)
				|| CommonConvertor.convertStringAvoidNull(typeInput).equalsIgnoreCase(TYPE_UPDATE)) {
			return true;
		}
		return false;
	}

	private String type; // 操作类型
	private String car_licence; // 车牌

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCar_licence() {
		return car_licence;
	}

	public void setCar_licence(String car_licence) {
		this.car_licence = car_licence;
	}

}
