package com.gzyct.m.api.busi.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import com.google.gson.JsonObject;


public class InnerApiHelper {

	public static final String SUCCESS = "0";
	
	public static String buildRequest(String service, String charset, String subsys, String ccInner, String skInner, Map<String,String> paramKV){
		SortedMap<String,String> params = new TreeMap<String,String>();
		params.put("version", "1.0");
		params.put("service", service);
		params.put("channel_code", ccInner);//"88888888"
		params.put("user_id", subsys);//填写子系统名字"yctapi.base"
		params.put("timestamp", TimeUtil.getCurrTime("yyyy-MM-dd hh:mm:ss"));
		params.put("charset", charset);
		params.put("sign_type", "MD5");
		//签名	sign	是	String	签名
		
		Set es = paramKV.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext())
		{
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (null != v && !"".equals(v))
			{
				params.put(k, v);
			}
		}
		
		String sign = getSign(params, charset, skInner);
		params.put("sign", sign);
		
		return toJson(params);
	}
	
	private static String getSign(Map<String,String> params, String charset, String signKey){
		Set esAll = params.entrySet();
		Iterator itAll = esAll.iterator();
		StringBuffer sb = new StringBuffer();
		while (itAll.hasNext())
		{
			Map.Entry entry = (Map.Entry) itAll.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k)	&& !"key".equals(k))
			{
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + signKey);
		String toSignString = sb.toString();
		String sign = MD5Util.MD5Encode(toSignString, charset).toUpperCase();
		return sign;
	}
	
	private static String toJson(Map<String,String> params){
		JsonObject json = new JsonObject();
		
		Set esAll = params.entrySet();
		Iterator itAll = esAll.iterator();
		while (itAll.hasNext())
		{
			Map.Entry entry = (Map.Entry) itAll.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (null != v && !"".equals(v))
			{
				json.addProperty(k, v);
			}
		}
		return json.toString();
	}
}
