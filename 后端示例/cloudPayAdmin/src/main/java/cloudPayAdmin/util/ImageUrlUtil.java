package cloudPayAdmin.util;

import org.apache.commons.lang.StringUtils;


import com.project.m.api.common.util.SpringUtils;

/**
 * 图片url出来的工具类
 * @author Administrator
 *
 */
public class ImageUrlUtil {
	
	/**
	 * 为数据库的 图片路径 加上http基础路径前缀
	 * @param oriImageUrl
	 * @return
	 */
	public static String getFullHttpImageUrl(String oriImageUrl) {
		if(StringUtils.isNotBlank(oriImageUrl)) {
			
			return SpringUtils.getProperty("image.http.base.path") + oriImageUrl;
		}
		return null;
	}
}
