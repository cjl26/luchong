package com.gzyct.m.api.busi.util;

import java.net.URL;

import javax.xml.ws.Holder;

import com.gzyct.m.api.busi.wsdl.sms.ArrayOfMobileList;
import com.gzyct.m.api.busi.wsdl.sms.ArrayOfSmsIDList;
import com.gzyct.m.api.busi.wsdl.sms.MobileListGroup;
import com.gzyct.m.api.busi.wsdl.sms.MobsetApi;

/**
 * 发送短信验证码工具类
 * 
 * @author Administrator
 *
 */
public class SmsCodeUtil {
	
	public static final String webServiceUrl = "http://sms3.mobset.com:8080/Api";
	public static final long corpId = 303281;
	public static final String loginName = "hicat";
	public static final String password = "186228";
	
	
	public static SmsCodeResult sendSmsCode(String mobile,String centent) {
		URL url = null;
		try {
			url = new URL(webServiceUrl);
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
				
		MobsetApi api = new MobsetApi(url);
		
		MobileListGroup mobileGroup = new MobileListGroup();
		mobileGroup.setMobile(mobile);
		
		
		ArrayOfMobileList mobileList = new ArrayOfMobileList();
		mobileList.getMobileListGroup().add(mobileGroup);
		
		Holder<Long> count = new Holder<Long>();
		Holder<String> errMsg = new Holder<String>();
		Holder<ArrayOfSmsIDList> smsIdHolder = new Holder<ArrayOfSmsIDList>();
		
		String timeStamp = TimeUtil.getCurrTime("MMddHHmmss");
		String pwd  = MD5Util.MD5Encode(corpId + password + timeStamp  , "utf-8");
		api.getMobsetApiSoap().smsSend(corpId, loginName,pwd, timeStamp, "001", "", 0l, mobileList, centent, count, errMsg, smsIdHolder);
		System.out.println(count.value);
		if(count.value > 0) {
			return new SmsCodeResult(count.value, errMsg.value, smsIdHolder.value.getSmsIDGroup().get(0).getMobile(), smsIdHolder.value.getSmsIDGroup().get(0).getSmsID());
		} else {
			return new SmsCodeResult(count.value, errMsg.value);
		}
		
	}
	
	public static void main1(String[] args) {
		sendSmsCode("18826405230","测试");
	}
	
	
    /**
     * count:成功发送短信的数量，小于1代表发送失败
     * errMsg：发送错误时 返回的错误信息
     * mobile ：发送的目的号码
     * smsId：短信平台返回的短信id
     * @author Administrator
     *
     */
	public static class SmsCodeResult { // 内部类
		
		public SmsCodeResult(Long count, String errMsg, String mobile, Long smsId) {
			super();
			this.count = count;
			this.errMsg = errMsg;
			this.mobile = mobile;
			this.smsId = smsId;
		}
		
		
		
		public SmsCodeResult(Long count, String errMsg) {
		super();
		this.count = count;
		this.errMsg = errMsg;
		}



		private Long count;

		private String errMsg;
		
		private String mobile;
		
		private Long smsId;

		public Long getCount() {
			return count;
		}

		public void setCount(Long count) {
			this.count = count;
		}

		public String getErrMsg() {
			return errMsg;
		}

		public void setErrMsg(String errMsg) {
			this.errMsg = errMsg;
		}

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		public Long getSmsId() {
			return smsId;
		}

		public void setSmsId(Long smsId) {
			this.smsId = smsId;
		}
		
		
	}
}
