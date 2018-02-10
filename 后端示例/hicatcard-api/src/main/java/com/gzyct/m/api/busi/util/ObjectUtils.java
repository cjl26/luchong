package com.gzyct.m.api.busi.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 对象工具类
 * 
 * @author hyj
 * @since 1.0
 */
public abstract class ObjectUtils {

	/**
	 * 检查一个集合是否为空
	 * 
	 * @param c
	 * @return
	 */
	public static boolean isEmpty(@SuppressWarnings("rawtypes") Collection c) {
		return c == null || c.size() == 0;
	}

	/**
	 * 检查一个字符串是否为空
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s) {
		return s == null || "".equals(s);
	}

	/**
	 * 检查一个对象是否为空
	 * 
	 * @param o
	 * @return
	 */
	public static boolean isEmpty(Object o) {
		if (o == null) {
			return true;
		}
		if (o instanceof String) {
			return isEmpty(o.toString());
		} else if (o instanceof Collection) {
			return isEmpty((Collection<?>) o);
		}
		return false;
	}

	/**
	 * 检查一个对象数组是否为空
	 * 
	 * @param os
	 * @return
	 */
	public static boolean isEmpty(Object[] os) {
		return os == null || os.length == 0;
	}

	/**
	 * 检查多个对象当中是否有存在空参数
	 * 
	 * @param os
	 * @return
	 */
	public static boolean hasEmpty(Object... os) {
		if (isEmpty(os)) {
			return true;
		}
		for (Object o : os) {
			if (isEmpty(o)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 检查多个对象当中是否全部是空
	 * 
	 * @param os
	 * @return
	 */
	public static boolean allEmpty(Object... os) {
		if (isEmpty(os)) {
			return true;
		}
		for (Object o : os) {
			if (!isEmpty(o)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 检查多个字符串当中是否有存在空参数
	 * 
	 * @param ss
	 * @return
	 */
	public static boolean hasEmpty(String... ss) {
		if (isEmpty(ss)) {
			return true;
		}
		for (String s : ss) {
			if (isEmpty(s)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 逗号分开的String->Integer,再封装到list
	 * 
	 * @param strs
	 * @return
	 */
	public static List<Integer> stringToList(String strs) {
		if (isEmpty(strs)) {
			return null;
		}
		String strArray[] = strs.split(",");
		List<Integer> lists = null;
		if (strArray.length > 0) {
			lists = new ArrayList<Integer>();
			for (String str : strArray) {
				lists.add(Integer.parseInt(str));
			}
		}
		return lists;
	}

	/**
	 * 逗号分开的String->Integer,再封装到list
	 * 
	 * @param strs
	 * @return
	 */
	public static List<Long> stringToLongList(String strs) {
		if (isEmpty(strs)) {
			return null;
		}
		String strArray[] = strs.split(",");
		List<Long> lists = null;
		if (strArray.length > 0) {
			lists = new ArrayList<Long>();
			for (String str : strArray) {
				lists.add(Long.parseLong(str));
			}
		}
		return lists;
	}

	public static int getInt(String str, int defaultValue) {

		if (str == null) {
			return defaultValue;
		}

		int res = Integer.MIN_VALUE;
		try {
			res = Integer.valueOf(str.trim());
		} catch (Exception e) {
			res = defaultValue;
		}
		return res;
	}

	public static int getInt(String str) {
		try {
			return Integer.valueOf(str.trim());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Integer.MIN_VALUE;
	}
	
	public static String array2String(Integer[] arr){
		String retString = "";
		if(isEmpty(arr)){
			return retString;
		}
		
		for(Integer str : arr){
			retString += str + ",";
		}
		return retString.substring(0, retString.length()-1);
	}
	
}
