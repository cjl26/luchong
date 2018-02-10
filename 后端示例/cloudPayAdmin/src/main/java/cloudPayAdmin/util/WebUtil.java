package cloudPayAdmin.util;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.WebUtils;

import cloudPayAdmin.util.pagebean.HqlParam;

public class WebUtil {

	private static final Logger logger = LoggerFactory.getLogger(WebUtil.class);

	public static final String alias = "u";
	public static final String suffix = "search_";
	public static final String SEPARATOR = "_";
	public static final String OPERTYPE_EQ = "EQ";
	public static final String OPERTYPE_LK = "LK";
	public static final String OPERTYPE_DATERANGE = "DATERANGE";
	public static final String PROPERTYTYPE_INTEGER = "INTEGER";
	public static final String PROPERTYTYPE_LONG = "LONG";
	public static final String PROPERTYTYPE_STRING = "STRING";
	public static final String PROPERTYTYPE_DATE = "DATE";
	public static final String DATERANGE_SEPARATOR = " - ";

	/**
	 * 获得筛选条件数据
	 * 
	 * @param request
	 * @return
	 */
	public static Map<String, Object> getSearchParam(HttpServletRequest request) {
		return WebUtils.getParametersStartingWith(request, suffix);
	}

	/**
	 * 转换日期格式 ，第一个日期加上 000000或者00:00:00 ,第二个日期加上 23:59:59
	 * 
	 * @param map
	 * @param datePattern  根据数据库日期的格式选择，如果数据库日期格式为varChar，就选DATE_PATTERN_NOSEPARTOR，如果Date类型，就选DEFAULT_DATE_PATTERN,如果数据库日期是varchar yyyyMMdd类型,就传DATE_PATTERN_ONLYDATE_NOSEPARTOR
	 * @return
	 */
	public static Map<String, Object> formatDateRange(Map<String, Object> map, String datePattern,
			String... beginAndEndTime) {
		Map<String, Object> newMap = new HashMap<String, Object>();
		for (Entry<String, Object> e : map.entrySet()) {
			String value = e.getValue() == null ? "" : (String) e.getValue();
			String key = e.getKey();
			if (StringUtils.contains(key, OPERTYPE_DATERANGE)) {
				if (StringUtils.isNotBlank(value)) {
					String[] values = StringUtils.splitByWholeSeparator(value, DATERANGE_SEPARATOR);
					if (StringUtils.equals(datePattern, TimeUtil.DEFAULT_DATE_PATTERN)) {
						if (beginAndEndTime != null && beginAndEndTime.length == 2) {
							value = values[0] + " " + beginAndEndTime[0] + DATERANGE_SEPARATOR + values[1] + " "
									+ beginAndEndTime[1];
						} else {
							value = values[0] + " 00:00:00" + DATERANGE_SEPARATOR + values[1] + " 23:59:59";
						}

					} else if (StringUtils.equals(datePattern, TimeUtil.DATE_PATTERN_NOSEPARTOR)) {

						if (beginAndEndTime != null && beginAndEndTime.length == 2) {
							value = StringUtils.replace(values[0], "-", "") + beginAndEndTime[0] + DATERANGE_SEPARATOR
									+ StringUtils.replace(values[1], "-", "") + beginAndEndTime[1];
						} else {
							value = StringUtils.replace(values[0], "-", "") + "000000" + DATERANGE_SEPARATOR
									+ StringUtils.replace(values[1], "-", "") + "235959";
						}
					} else if (StringUtils.equals(datePattern, TimeUtil.DATE_PATTERN_ONLYDATE_NOSEPARTOR)) {						
							value = StringUtils.replace(values[0], "-", "")  + DATERANGE_SEPARATOR + StringUtils.replace(values[1], "-", "");
					}
				}

			}

			newMap.put(key, value);
		}
		return newMap;
	}
	
	/**
	 * 根据request条件拼orderString
	 * @param request
	 * @return
	 */
	private static String buildOrderString(HttpServletRequest request){
		StringBuilder hql = new StringBuilder();
		String orderIndexStr = request.getParameter("order[0][column]");
		String orderDir = request.getParameter("order[0][dir]");
		if (StringUtils.isNotBlank(orderIndexStr) && StringUtils.isNotBlank(orderDir)) {
			String orderPropertyName = request.getParameter("columns[" + Integer.parseInt(orderIndexStr) + "][data]");
			hql.append(" order by " + alias + "." + orderPropertyName + " " + orderDir);
		}
		
		return hql.toString();
	}
	
	/**
	 * 根据map来获取筛选条件
	 * @param map
	 * @return
	 */
	private static String buildConditionStringByMap(Map<String, Object> map, List<Object> params){
		StringBuilder hql = new StringBuilder();
		
		int i = 1;
		for (Entry<String, Object> e : map.entrySet()) {
			String value = e.getValue() == null ? "" : (String) e.getValue();
			String key = e.getKey();
			// 如果筛选条件不为空
			if (StringUtils.isNotBlank(value)) {
				String opType = getOperType(key);
				String propertyType = getPropertyType(key);
				String propertyName = getPropertyName(key);
				hql.append(" and " + alias + "." + propertyName);
				if (StringUtils.equals(opType, OPERTYPE_EQ)) {
					hql.append(" = " + "?" + i);
					addParams(propertyType, value, params);
				} else if (StringUtils.equals(opType, OPERTYPE_LK)) {
					hql.append(" like " + "?" + i);
					value = "%" + value + "%";
					addParams(propertyType, value, params);
				} else if (StringUtils.equals(opType, OPERTYPE_DATERANGE)) {
					String date[] = StringUtils.splitByWholeSeparator(value, DATERANGE_SEPARATOR);
					hql.append(" >= " + "?" + i).append(" and " + alias + "." + propertyName)
							.append(" <= " + "?" + (i + 1));
					addParams(propertyType, date[0], params);
					addParams(propertyType, date[1], params);
					i++;

				}
				i++;
			}
		}
		
		return hql.toString();
	}
	
	/**
	 * 
	 * @param map
	 * @param clazz
	 * @param request
	 * @param sqlString
	 * @param whetherOrder
	 * @return
	 */
	public static HqlParam buildHqlParam(Map<String, Object> map, Class clazz, HttpServletRequest request, String sqlString, boolean whetherOrder){
		List<Object> params = new ArrayList<Object>();
		StringBuilder hql = new StringBuilder();
		hql.append(sqlString);
		// 拼接筛选条件
		hql.append(buildConditionStringByMap(map, params));
		if(whetherOrder)
		{
			hql.append(buildOrderString(request));
		}
		logger.info("hql.toString = " + hql.toString());
		return new HqlParam(hql.toString(), params, clazz, Integer.parseInt(request.getParameter("start")),
				Integer.parseInt(request.getParameter("length")));
	}

	public static HqlParam buildHqlParam(Map<String, Object> map, Class clazz, HttpServletRequest request) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder hql = new StringBuilder();
		hql.append("select " + alias + " from " + clazz.getSimpleName() + " " + alias + " where 1=1");
		logger.info("hql in buildHqlParam = " + hql);

		// 拼接筛选条件
		int i = 1;
		for (Entry<String, Object> e : map.entrySet()) {
			String value = e.getValue() == null ? "" : (String) e.getValue();
			String key = e.getKey();
			// 如果筛选条件不为空
			if (StringUtils.isNotBlank(value)) {
				String opType = getOperType(key);
				String propertyType = getPropertyType(key);
				String propertyName = getPropertyName(key);
				hql.append(" and " + alias + "." + propertyName);
				if (StringUtils.equals(opType, OPERTYPE_EQ)) {
					hql.append(" = " + "?" + i);
					addParams(propertyType, value, params);
				} else if (StringUtils.equals(opType, OPERTYPE_LK)) {
					hql.append(" like " + "?" + i);
					value = "%" + value + "%";
					addParams(propertyType, value, params);
				} else if (StringUtils.equals(opType, OPERTYPE_DATERANGE)) {
					String date[] = StringUtils.splitByWholeSeparator(value, DATERANGE_SEPARATOR);
					hql.append(" >= " + "?" + i).append(" and " + alias + "." + propertyName)
							.append(" <= " + "?" + (i + 1));
					addParams(propertyType, date[0], params);
					addParams(propertyType, date[1], params);
					i++;

				}
				i++;
			}
		}
		
		//如果有enable删除标志位，就增加筛选条件===start
		Field enableField = null;
		try {
			enableField = clazz.getDeclaredField("enable");
		} catch (NoSuchFieldException | SecurityException e1) {
		}
			
		if(enableField != null) {
			hql.append(" and ").append(alias).append(".").append(enableField.getName()).append(" = true");
		}
		//如果有enable删除标志位，就增加筛选条件 ====end
			
		// 拼接order语句
		String orderIndexStr = request.getParameter("order[0][column]");
		String orderDir = request.getParameter("order[0][dir]");
		if (StringUtils.isNotBlank(orderIndexStr) && StringUtils.isNotBlank(orderDir)) {
			String orderPropertyName = request.getParameter("columns[" + Integer.parseInt(orderIndexStr) + "][data]");
			hql.append(" order by " + alias + "." + orderPropertyName + " " + orderDir);
		}
		
		String start = request.getParameter("start");
		String length = request.getParameter("length");
		return new HqlParam(hql.toString(), params, clazz, StringUtils.isNotBlank(start) && Integer.parseInt(start) >= 0 ? Integer.parseInt(request.getParameter("start")) : null,
				StringUtils.isNotBlank(length) && Integer.parseInt(length) >= 0 ? Integer.parseInt(request.getParameter("length")) : null);
	}

	/**
	 * 根据数据类型转换，并加入到参数list
	 * 
	 * @param propertyType
	 * @param value
	 */
	private static void addParams(String propertyType, String value, List<Object> params) {
		if (StringUtils.equals(propertyType, PROPERTYTYPE_INTEGER)) {
			params.add(Integer.parseInt(value));
		} else if (StringUtils.equals(propertyType, PROPERTYTYPE_LONG)) {
			params.add(Long.parseLong(value));
		} else if (StringUtils.equals(propertyType, PROPERTYTYPE_STRING)) {
			params.add(value);
		} else if (StringUtils.equals(propertyType, PROPERTYTYPE_DATE)) {
			Date dateValue = null;
			try {
				dateValue = TimeUtil.strToDate(value, TimeUtil.DEFAULT_DATE_PATTERN);
			} catch (Exception e) {
				try {
					dateValue = TimeUtil.strToDate(value, TimeUtil.DATE_PATTERN_NOSEPARTOR);
				} catch (ParseException e1) {
					e.printStackTrace();
					logger.error(e.getMessage(), e);
					throw new RuntimeException("http参数的日期格式出错");
				}
			}
			params.add(dateValue);
		}
	}

	/**
	 * 获得操作类型，等于，大于，小于，daterange之类的
	 * 
	 * @param key
	 * @return
	 */
	private static String getOperType(String key) {
		int firstIndex = StringUtils.indexOf(key, SEPARATOR);
		return StringUtils.substring(key, 0, firstIndex);
	}

	/**
	 * 获得属性的类型
	 * 
	 * @param key
	 * @return
	 */
	private static String getPropertyType(String key) {
		int firstIndex = StringUtils.indexOf(key, SEPARATOR);
		String key1 = StringUtils.substring(key, firstIndex + 1);
		int secondIndex = StringUtils.indexOf(key1, SEPARATOR);
		return StringUtils.substring(key1, 0, secondIndex);
	}

	/**
	 * 获得属性名
	 * 
	 * @param key
	 * @return
	 */
	private static String getPropertyName(String key) {
		int firstIndex = StringUtils.indexOf(key, SEPARATOR);
		String key1 = StringUtils.substring(key, firstIndex + 1);
		int secondIndex = StringUtils.indexOf(key1, SEPARATOR);
		String key2 = StringUtils.substring(key1, secondIndex + 1);
		return key2;
	}

}
