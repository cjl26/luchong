package com.gzyct.m.api.busi.util.cache;

import com.gzyct.m.api.busi.bean.ext.UserSessionObject;

public class SessionCacheUtil {

	
	private static SimpleCache<String, UserSessionObject> ssCache = new SimpleCache<String, UserSessionObject>(1000);
	
	public static UserSessionObject getSession(String userid){
		UserSessionObject cacheNode = ssCache.get(userid);
		return cacheNode;
	}
	
	public static void putSession(String userid, UserSessionObject obj){
		ssCache.put(userid, obj);
	}
}
