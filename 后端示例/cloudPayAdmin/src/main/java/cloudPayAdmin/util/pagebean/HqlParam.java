package cloudPayAdmin.util.pagebean;

import java.util.ArrayList;
import java.util.List;

public class HqlParam {

	private String hql;
	private List<Object> params = new ArrayList<Object>();
	private Class clazz;
	private Integer start;
	private Integer pageSize;

	public HqlParam(String hql, List<Object> params, Class clazz, Integer start, Integer pageSize) {
		super();
		this.hql = hql;
		this.params = params;
		this.clazz = clazz;
		this.start = start;
		this.pageSize = pageSize;
	}

	public String getHql() {
		return hql;
	}

	public void setHql(String hql) {
		this.hql = hql;
	}

	public List<Object> getParams() {
		return params;
	}

	public void setParams(List<Object> params) {
		this.params = params;
	}

	public Class getClazz() {
		return clazz;
	}

	public void setClazz(Class clazz) {
		this.clazz = clazz;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

}
