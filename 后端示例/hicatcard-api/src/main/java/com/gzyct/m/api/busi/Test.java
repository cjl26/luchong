package com.gzyct.m.api.busi;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.ws.Holder;

import com.gzyct.m.api.busi.util.MD5Util;
import com.gzyct.m.api.busi.util.TimeUtil;
import com.gzyct.m.api.busi.wsdl.sms.ArrayOfMobileList;
import com.gzyct.m.api.busi.wsdl.sms.ArrayOfSmsIDList;
import com.gzyct.m.api.busi.wsdl.sms.MobileListGroup;
import com.gzyct.m.api.busi.wsdl.sms.MobsetApi;
import com.gzyct.m.api.busi.wsdl.sms.SmsIDGroup;

public class Test {
	public static void main2(String[] args) throws MalformedURLException {
		
		MobsetApi api = new MobsetApi(new URL("http://sms3.mobset.com:8080/Api"));
		
		MobileListGroup mobileGroup = new MobileListGroup();
		mobileGroup.setMobile("18826405230");
		
		
		ArrayOfMobileList mobileList = new ArrayOfMobileList();
		mobileList.getMobileListGroup().add(mobileGroup);
		
		Holder<Long> count = new Holder<Long>();
		Holder<String> errMsg = new Holder<String>();
		Holder<ArrayOfSmsIDList> smsIdHolder = new Holder<ArrayOfSmsIDList>();
		
		String timeStamp = TimeUtil.getCurrTime("MMddHHmmss");
		String password  = MD5Util.MD5Encode("303281" + "186228" + timeStamp  , "utf-8");
		api.getMobsetApiSoap().smsSend(303281, "hicat",password, timeStamp, "001", "", 0l, mobileList, "test", count, errMsg, smsIdHolder);
		
		System.out.println(count.value);
		System.out.println(errMsg.value);
		System.out.println(smsIdHolder.value.getSmsIDGroup().get(0).getMobile());
		System.out.println(smsIdHolder.value.getSmsIDGroup().get(0).getSmsID());
		
		
	
	}
}
