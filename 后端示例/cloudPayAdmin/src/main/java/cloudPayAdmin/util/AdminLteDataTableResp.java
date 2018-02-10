package cloudPayAdmin.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Id;
import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;

import cloudPayAdmin.util.jsonFilter.NullValueAsEmptyStringFilter;
import cloudPayAdmin.util.jsonFilter.StringDateFormatFilter;

public class AdminLteDataTableResp<T> {
	
	/**
	 * 转为adminlteJson要执行的filter
	 */
	public static SerializeFilter[] filters = new  SerializeFilter[]{new StringDateFormatFilter(),new NullValueAsEmptyStringFilter()};
			
	private Integer draw;
	private Long recordsTotal;
	private Long recordsFiltered;
	private List<T> data = new ArrayList<T>();

	/**
	 * @param clazz
	 *            传入泛型T对应的类型
	 * @return
	 */
	public String toAdminLteJsonString(Class clazz) {
		
		//SerializeFilter[] filters = new  SerializeFilter[]{new StringDateFormatFilter()};
		
		//将实体类的null的属性，以空字符串输出，但是number类型转换后会直接输出 “null”
		String oriJson = JSON.toJSONString(this,filters,SerializerFeature.WriteDateUseDateFormat,SerializerFeature.DisableCircularReferenceDetect/*,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty*/);
		
		//"null"转为空字符串,由于json字符串 空串必须显示 "",所以用 "\"\""
		//oriJson = StringUtils.replace(oriJson, "null", "\"\"");
			
		String idFieldName = "";
		//如果 Id注解再字段上面
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			if (field.getAnnotation(Id.class) != null) {
				idFieldName = field.getName();
				break;
			}
		}
		
		//如果id注解注解在方法上面
		if(StringUtils.isBlank(idFieldName)) {
			Method[] methods = clazz.getDeclaredMethods();
			for(Method method : methods) {
				if(method.getAnnotation(Id.class) != null) {
					String methodName = method.getName();
					String oriFieldName = StringUtils.substring(methodName, 3);
					String first = StringUtils.substring(oriFieldName, 0,1);
					idFieldName = StringUtils.replace(oriFieldName, first, StringUtils.lowerCase(first));
					break;
				}
			}
		}
		
		if(StringUtils.isNotBlank(idFieldName)) {
			String newJson = AdminLteUtil.addJsonField(oriJson, idFieldName,"DT_RowId");
			return newJson;
		}
		return oriJson;
	}

	public AdminLteDataTableResp(Integer draw, Long recordsTotal, Long recordsFiltered, List<T> data) {
		super();
		this.draw = draw;
		this.recordsTotal = recordsTotal;
		this.recordsFiltered = recordsFiltered;
		this.data = data;
	}

	public Integer getDraw() {
		return draw;
	}

	public void setDraw(Integer draw) {
		this.draw = draw;
	}

	public Long getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(Long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public Long getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(Long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

}
