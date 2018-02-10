package cloudPayAdmin.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.jfinal.json.Json;
import com.jfinal.weixin.sdk.utils.HttpUtils;
import com.jfinal.wxaapp.api.WxaAccessTokenApi;

public class WxApiUtil {
	
	private static Logger logger = Logger.getLogger(WxApiUtil.class);
	
    private static String getWxaCodeUnLimitURL = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=";
    
    public static InputStream getUnLimitQrCode(String scene, String page) {
         
    	 logger.info("调用微信二维码生成接口");    	
    	 String url = getWxaCodeUnLimitURL + WxaAccessTokenApi.getAccessTokenStr();
    	 
    	 Map<String ,String> map = new HashMap<String ,String>();
    	 map.put("scene", scene);
    	 map.put("page", page);
    	 
    	 String jsonReq = JSON.toJSONString(map);
    	 return HttpUtils.download(url, jsonReq);
    }
    
}
