package cloudPayAdmin.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.serializer.SerializeFilter;

import cloudPayAdmin.util.jsonFilter.StringDateFormatFilter;
import cloudPayAdmin.util.pagebean.PageBean;

public class AdminLteUtil {
	
	public static  <T> AdminLteDataTableResp<T> pageBeanToAdminLteDataTableResp(PageBean<T> cloudpayCardPage,HttpServletRequest request) {
		Integer draw = StringUtils.isNotBlank(request.getParameter("draw")) ? Integer.parseInt(request.getParameter("draw")) : 1;
		Long recordsTotal = cloudpayCardPage.getTotalCount();
		List<T> data = cloudpayCardPage.getContent();		
		return new AdminLteDataTableResp<T>(draw, recordsTotal, recordsTotal, data); 
	}
	
	/**
	 * 向返回到前端adminlte数据表格的json字符串增加字段,字段的值为id字段的值
	 * @param jsonStr  原json字符串
	 * @param idFieldName   主键的 字段名
	 * @param addFieldName   需要新增的 字段名
	 * @return
	 */
	public static String addJsonField(String jsonStr,String idFieldName,String addFieldName){
		StringBuilder newJson = new StringBuilder();
		String[] strs = StringUtils.splitByWholeSeparator(jsonStr,idFieldName );
		if(strs.length == 1) {
			return jsonStr;
		} else {
			for (int i=0;i<strs.length;i++) {
				if(i<strs.length-1) {
					newJson.append(strs[i]).append(addFieldName+"\":").append(StringUtils.trim(StringUtils.replaceEach(StringUtils.substring(strs[i+1], StringUtils.indexOf(strs[i+1], ":")+1,StringUtils.indexOf(strs[i+1], ",")), new String[]{"}","]"}, new String[]{"",""}))).append(",\""+ idFieldName);
				}				
			}
			newJson.append(strs[strs.length-1]);
		} 
		return newJson.toString();
	}
}
