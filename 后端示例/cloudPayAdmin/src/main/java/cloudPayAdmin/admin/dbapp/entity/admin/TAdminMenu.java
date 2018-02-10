package cloudPayAdmin.admin.dbapp.entity.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "t_admin_menu")
public class TAdminMenu {

	public static final Integer TYPE_FLODER = 1;
	public static final String TYPE_FLODER_MSG = "文件夹";
	public static final Integer TYPE_OPTION = 2;
	public static final String TYPE_OPTION_MSG = "选项";

	// 类型
	public static Map<String, String> initTypeMap() {
		Map<String, String> typeMap = new HashMap<String, String>();
		typeMap.put(TYPE_FLODER + "", TYPE_FLODER_MSG);
		typeMap.put(TYPE_OPTION + "", TYPE_OPTION_MSG);
		return typeMap;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "PARENT_ID")
	private Long parentId;

	@Column(name = "URL")
	private String url = "#";

	@Column(name = "TYPE")
	private Integer type;

	@Column(name = "ORDER_NUM")
	private Integer orderNum;

	@Column(name = "CREATE_TIME")
	private Date createTime;

	@Column(name = "UPDATE_TIME")
	private Date updateTime;

	@Column(name = "REMARK")
	private String remark;

	@Column(name = "NAME")
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
