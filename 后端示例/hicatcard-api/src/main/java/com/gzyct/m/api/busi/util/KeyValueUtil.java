package com.gzyct.m.api.busi.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class KeyValueUtil {

	/**
	 * 返回指定参数名的参数值
	 * @param 原字符串, 形式 key1=val1&key2=val2...
	 * @param key 要获取的参数名
	 * @return 参数值
	 */
	public static String getValue(String content, String key){
		if(content==null)return "";
		
		String sKey = key + "=";
		int spos = content.indexOf(sKey);
		if(spos == -1)return "";
		
		//value starting pos
		int sposVal = spos + sKey.length();
		
		int eposVal = content.indexOf("&", spos);
		if(eposVal == -1){
			return content.substring(sposVal);
		}else{
			return content.substring(sposVal, eposVal);
		}
	}
	
	/**
	 * 将对象转成key=value对
	 */
	public static String toKeyValues(Object obj){
		SortedMap<String, String> params = new TreeMap<String, String>();
		if(!ParamHelper.getFiledValues(obj, params)){
			return "";
		}
		StringBuffer sb = new StringBuffer();
		Set es = params.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext())
		{
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (null != v && !"".equals(v))
			{
				sb.append(k + "=" + v + "&");
			}
		}
		String reqPars = sb.substring(0, sb.lastIndexOf("&"));
		return reqPars;
	}
	
//	public static void main(String []args){
//		System.out.println(getValue("k1=vl&k2=v2&k3=v3", "k1"));
//		System.out.println(getValue("k1=vl&k2=v2&k3=v3", "k2"));
//		System.out.println(getValue("k1=vl&k2=v2&k3=v3", "k3"));
//		System.out.println(getValue("k1=vl&k2=v2&k3=v3", "k4"));
//	}
}
