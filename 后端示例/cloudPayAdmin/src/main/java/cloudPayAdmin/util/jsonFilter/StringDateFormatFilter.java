package cloudPayAdmin.util.jsonFilter;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.serializer.ValueFilter;

import cloudPayAdmin.util.TimeUtil;
import cloudPayAdmin.util.annotation.StringDateFormat;

/**
 * String类型的日期字段，转换成json字符串时根据StringDateFormat注解变换格式的Filter
 * @author hyj
 *
 */
public class StringDateFormatFilter implements ValueFilter {

	@Override
	public Object process(Object object, String name, Object value) {
		if(value != null) {
			if(value instanceof String) {   //如果是string类型就根据注解做出来
				
				try {
					Field field = object.getClass().getDeclaredField(name);
					Method method = object.getClass().getMethod("get" + StringUtils.upperCase(StringUtils.substring(name,0,1)) + StringUtils.substring(name, 1,name.length()));
					
					if(field.getAnnotation(StringDateFormat.class) != null || method.getAnnotation(StringDateFormat.class) != null) {
						StringDateFormat sdf = field.getAnnotation(StringDateFormat.class) == null ? method.getAnnotation(StringDateFormat.class) : field.getAnnotation(StringDateFormat.class);
						String oriDateFormat = sdf.oriFormat();
						String destDateFormat = sdf.destFormat();
						if(StringUtils.isBlank(oriDateFormat) || StringUtils.isBlank(destDateFormat)) {
							return value;
						} else {
							if(StringUtils.isNotBlank(value.toString())) {
								Date date = TimeUtil.strToDate(value.toString(),oriDateFormat);
								return TimeUtil.getFormatTime(date, destDateFormat);
							}
							
							return value.toString();
						}
						
					} else {
						return value;   //如果没有注解，原值返回
					}
					
				} catch (Exception e) {
					//这里catch到exception不做处理
					e.printStackTrace();
				}
				
				return value;
			} else {     //如果值不是string类型，原值返回
				return value;
			}	
		} else {
			return value;
		}
		
	}
}
