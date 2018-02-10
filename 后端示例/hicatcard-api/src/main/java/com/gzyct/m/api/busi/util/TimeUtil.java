package com.gzyct.m.api.busi.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {

	public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_PATTERN_MILLI = "yyyy-MM-dd HH:mm:ss.S";
	public static final String DATE_PATTERN_NOSEPARTOR = "yyyyMMddHHmmss";

	/**
	 * 获取unix时间，从1970-01-01 00:00:00开始的秒数
	 * 
	 * @param date
	 * @return long
	 */
	public static long getUnixTime(Date date) {
		if (null == date) {
			return 0;
		}

		return date.getTime() / 1000;
	}

	/**
	 * 获取当前时间 format, 比如yyyyMMddHHmmss
	 * 
	 * @return String
	 */
	public static String getCurrTime(String format) {
		Date now = new Date();
		SimpleDateFormat outFormat = new SimpleDateFormat(format);
		String s = outFormat.format(now);
		return s;
	}

	/**
	 * 获取当前时间 format, 默认yyyyMMddHHmmss
	 * 
	 * @return String
	 */
	public static String getCurrTime() {
		Date now = new Date();
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String s = outFormat.format(now);
		return s;
	}

	/**
	 * 获取昨天时间
	 * 
	 * @param format
	 * @return
	 */
	public static String getYesterday(String format) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String yesterday = new SimpleDateFormat(format).format(cal.getTime());
		return yesterday;
	}

	/**
	 * 格式化时间 format, 比如yyyyMMddHHmmss
	 * 
	 * @return String
	 */
	public static String getFormatTime(Date date, String format) {
		SimpleDateFormat outFormat = new SimpleDateFormat(format);
		String s = outFormat.format(date);
		return s;
	}

	public static String getFormatTime(Date date) {
		SimpleDateFormat outFormat = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
		String s = outFormat.format(date);
		return s;
	}

	/**
	 * 格式化时间 format, 比如yyyyMMddHHmmss
	 * 
	 * @return String
	 */
	public static String getFormatTime(long unixtime, String format) {
		SimpleDateFormat outFormat = new SimpleDateFormat(format);
		String s = outFormat.format(new Date(unixtime * 1000));
		return s;
	}

	/**
	 * 字符串转时间, yyyyMMddHHmmss
	 * 
	 * @return String
	 * @throws ParseException
	 */
	public static Date strToDate(String time, String format) throws ParseException {
		SimpleDateFormat outFormat = new SimpleDateFormat(format);
		Date dt = outFormat.parse(time);
		return dt;
	}

	/**
	 * 获取当前时间 format, 比如yyyyMMddHHmmss
	 * 
	 * @return String
	 */
	public static String getOneDayLater(String format) {
		Date now = addDateOneDay(new Date());
		SimpleDateFormat outFormat = new SimpleDateFormat(format);
		String s = outFormat.format(now);
		return s;
	}

	public static String getTwoHourLater(String format) {
		Date now = addDateTwoHour(new Date());
		SimpleDateFormat outFormat = new SimpleDateFormat(format);
		String s = outFormat.format(now);
		return s;
	}

	public static String getXHourLater(String format, int x) {
		Date now = addXHour(new Date(), x);
		SimpleDateFormat outFormat = new SimpleDateFormat(format);
		String s = outFormat.format(now);
		return s;
	}

	public static Date addDateOneDay(Date date) {
		if (null == date) {
			return date;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date); // 设置当前日期
		c.add(Calendar.DATE, 1); // 日期加1天
		// c.add(Calendar.DATE, -1); //日期减1天
		date = c.getTime();
		return date;
	}

	/**
	 * 添加多少日
	 * 
	 * @param date
	 * @param n
	 * @return
	 */
	public static Date addDateNDay(Date date, int n) {
		if (null == date) {
			return date;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date); // 设置当前日期
		c.add(Calendar.DATE, n); // 日期加1天
		// c.add(Calendar.DATE, -1); //日期减1天
		date = c.getTime();
		return date;
	}

	public static Date addDateTwoHour(Date date) {
		if (null == date) {
			return date;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date); // 设置当前日期
		c.add(Calendar.HOUR, 2); // 日期加1天
		// c.add(Calendar.DATE, -1); //日期减1天
		date = c.getTime();
		return date;
	}

	public static Date addXHour(Date date, int x) {
		if (null == date) {
			return date;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date); // 设置当前日期
		c.add(Calendar.HOUR, x); // 日期加x小时
		date = c.getTime();
		return date;
	}

	/**
	 * 日期格式转化方法
	 * 
	 * @param dateStr
	 *            需要被转换的日期字符还
	 * @param oriFormat
	 *            原本日期的格式
	 * @param destFormat
	 *            需要转换成什么格式的参数
	 * @return
	 */
	public static String transferDateFormat(String dateStr, String oriFormat, String destFormat) {
		SimpleDateFormat oriFor = new SimpleDateFormat(oriFormat);
		SimpleDateFormat destFor = new SimpleDateFormat(destFormat);
		Date date = null;
		try {
			date = oriFor.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException("日期转换格式错误");
		}
		dateStr = destFor.format(date);
		return dateStr;
	}

}
