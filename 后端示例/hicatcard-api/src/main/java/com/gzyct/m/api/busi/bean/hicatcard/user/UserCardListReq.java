package com.gzyct.m.api.busi.bean.hicatcard.user;

import com.project.m.api.common.biz.req.BizRequest;

public class UserCardListReq extends BizRequest {

	private String source;
	private String status;
	private Integer page;
	private Integer pagesize;
	private String attach;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPagesize() {
		return pagesize;
	}

	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

}
