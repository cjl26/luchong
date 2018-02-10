package cloudPayAdmin.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.project.m.api.common.util.SpringUtils;

/**
 * 调用腾讯地图接口工具类
 * @author Administrator
 *
 */
public class TencentMapUtil {
	
	private static final Logger logger = Logger.getLogger(TencentMapUtil.class);
	
	public final static String ADDRESS_TO_LOCATION_URL = "https://apis.map.qq.com/ws/geocoder/v1/?address=";
	
	public static Map getLocationByAddress(String address) {
		if(StringUtils.isNotBlank(address)) {
			logger.info("测试日志");
			logger.info("getLocationByAddress address: " + address);
			
			try {
				address = URLEncoder.encode(address,"utf-8");
			} catch (UnsupportedEncodingException e) {
				logger.error(e.getMessage(), e);
			}
					
			String url = ADDRESS_TO_LOCATION_URL + address + "&key=" + SpringUtils.getProperty("tencent.map.key");
			logger.info("tencentMap url: " + url);
			String resp = HttpUtil.get(url);
			logger.info("getLocationByAddress resp: " + resp);
			return JSONObject.parseObject(resp, Map.class);
		}
		
		return null;
	}
	
	public static void main2(String[] args) {
		getLocationByAddress("广州市海珠区新南路16号");
	}
}
