package com.gzyct.m.api.busi.util.builder;

import com.project.m.api.common.biz.req.BaseRequest;

public class BaseRequestConverter {

	public static void fromRequest(BaseRequest srcReq, BaseRequest destReq) {

		destReq.setOpenid(srcReq.getOpenid());
		destReq.setApi_version(srcReq.getApi_version());
		destReq.setCharset(srcReq.getCharset());
		destReq.setTimestamp(srcReq.getTimestamp());
		destReq.setService(srcReq.getService());
		destReq.setBrand(srcReq.getBrand());
		destReq.setModel(srcReq.getModel());
		destReq.setLanguage(srcReq.getLanguage());
		destReq.setVersion(srcReq.getVersion());
		destReq.setSystem(srcReq.getSystem());
		destReq.setPlatform(srcReq.getPlatform());
	}

	// public static void buildNotiWxinRequest( BaseRequest destReq){
	//
	//
	//
	// destReq.setVersion("1.0");
	// destReq.setService("yct.busi.noti.wxin");
	// destReq.setChannel_code("00000000");// TODO: 2016/4/14 微信支持通知无channel和user_id
	// destReq.setUser_id("00000000");
	// destReq.setCharset("UTF-8");
	// destReq.setSign_type("MD5");
	// SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	// String date = sDateFormat.format(new java.util.Date());
	// destReq.setTimestamp(date);
	// }
}
