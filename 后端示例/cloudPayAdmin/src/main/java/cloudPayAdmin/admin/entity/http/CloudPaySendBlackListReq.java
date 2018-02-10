package cloudPayAdmin.admin.entity.http;

import com.project.m.api.common.biz.req.BizRequest;

import java.util.List;

public class CloudPaySendBlackListReq extends BizRequest{

	private int listsize;
	private List<CloudPayBlackCard>  blacklist;

	public int getListsize() {
		return listsize;
	}

	public void setListsize(int listsize) {
		this.listsize = listsize;
	}

	public List<CloudPayBlackCard> getBlacklist() {
		return blacklist;
	}

	public void setBlacklist(List<CloudPayBlackCard> blacklist) {
		this.blacklist = blacklist;
	}
}
