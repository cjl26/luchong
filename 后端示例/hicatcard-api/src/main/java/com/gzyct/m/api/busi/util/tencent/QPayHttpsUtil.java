package com.gzyct.m.api.busi.util.tencent;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

public class QPayHttpsUtil {

	private static final Logger logger = LoggerFactory.getLogger(QPayHttpsUtil.class);
	/**
	 * 发起带证书的http请求
	 * @param charset
	 * @param keyvalues
	 * @param env
	 * @return
	 */
	public static String httpsCall(String charset, String url, String keyvalues, Environment env){
		TenpayHttpClient httpClient = new TenpayHttpClient(charset);
		httpClient.setTimeOut(5);
		if(env!=null){
			String caPath = env.getProperty("qqpay.ca.path");
			String certPath	= env.getProperty("qqpay.cert.path");
			String certPassword = env.getProperty("qqpay.cert.passwd");
			httpClient.setCaInfo(new File(caPath));
			httpClient.setCertInfo(new File(certPath), certPassword);
		}
		
		httpClient.setMethod("POST");
//		httpClient.setMethod("GET");
	    String reqContent = url + "?" + keyvalues;
	    //设置请求内容 url+keyvalus
	    httpClient.setReqContent(reqContent);
	    String rescontent = "null";
	    //后台调用
	    if(httpClient.call()) {
	    	//设置结果参数
	    	rescontent = httpClient.getResContent();
	    	return rescontent;//XML
	    }else{
	    	logger.error(httpClient.getErrInfo());
	    }
	    return "";
	}
}
