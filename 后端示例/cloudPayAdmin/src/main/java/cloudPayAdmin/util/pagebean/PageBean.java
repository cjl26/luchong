package cloudPayAdmin.util.pagebean;

import java.util.List;

/**
 * jpa使用sql查找的结果pageBean
 * @author hyj
 *
 * @param <T>
 */
public class PageBean<T> {

	private List<T> content;
	private Integer totalPage;
	private Long totalCount;
	private Integer currentPage;
	private Integer pageSize;

	public PageBean(List<T> content, Integer totalPage, Long totalCount, Integer currentPage, Integer pageSize) {
		super();
		this.content = content;
		this.totalPage = totalPage;
		this.totalCount = totalCount;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

}
