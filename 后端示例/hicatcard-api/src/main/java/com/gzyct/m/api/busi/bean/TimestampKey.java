package com.gzyct.m.api.busi.bean;

/**
 * 时间戳密钥信息
 * @author zoudb
 *
 */
public class TimestampKey {

	private String key;
	private String start_time;
	private String end_time;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
}
