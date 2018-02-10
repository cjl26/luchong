package cloudPayAdmin.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Component;

/**
 * ehcache工具类
 * @author hyj
 *
 */
@Component
public class EhcacheUtil {
	
	public static final String CACHE_KICKOUT = "kickOutCache";
	public static final String CACHE_ACTIVESESSIONCACHE = "shiro-activeSessionCache";
	
	static EhCacheCacheManager manager;
	
	public static void put(String cacheName,String key,Object value) {
		Cache cache = manager.getCache(cacheName);
		cache.put(key, value);
	}
	
	public static void evict(String cacheName,String key,Object value) {
		Cache cache = manager.getCache(cacheName); 
		cache.evict(key);
	}
	
	public static Object get(String cacheName,String key) {
		Cache cache = manager.getCache(cacheName); 
		return cache.get(key);
	}
	
	public static <T>T get(String cacheName,String key,Class<T> clazz) {
		Cache cache = manager.getCache(cacheName); 
		return cache.get(key, clazz);
	}
	
	public static void clear(String cacheName) {
		manager.getCache(cacheName).clear();
	}
	
	@Autowired
	public void setManager(EhCacheCacheManager manager) {
		EhcacheUtil.manager = manager;
	}
	
	
}
