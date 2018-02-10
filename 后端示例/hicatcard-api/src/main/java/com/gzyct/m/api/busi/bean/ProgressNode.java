package com.gzyct.m.api.busi.bean;
/**
 * 业务进度记录项
 * @author zoudb
 *
 */
public class ProgressNode {

	private long idx;
	private String title;
	private String desc;
	private String time;
	
	public ProgressNode(){}
	
	public ProgressNode(long idx, String title, String desc, String time){
		this.idx = idx;
		this.title = title;
		this.desc = desc;
		this.time = time;
	}
	
	public long getIdx() {
		return idx;
	}
	public void setIdx(long idx) {
		this.idx = idx;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
}
