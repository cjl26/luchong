package cloudPayAdmin.admin.dbapp.entity.admin;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

//@Entity
//@Table(name = "T_ADMIN_PRIVILEGE_CHANGE_REC")
//@SequenceGenerator(name = "SEQ_ADMIN_PRIVILEGE_CHANGE_REC", sequenceName = "SEQ_ADMIN_PRIVILEGE_CHANGE_REC", allocationSize = 1, initialValue = 1)
public class TAdminPrivilegeChangeRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ADMIN_PRIVILEGE_CHANGE_REC")
	@Column(name = "ID")
	private Long id;

	@Column(name = "ROLE_ID")
	private Long roleId;

	@Column(name = "PRIVILEGE_IDS")
	private String privilegesIds;


	@Column(name = "CREATE_TIME")
	private Date createTime;

	@Column(name = "UPDATE_TIME")
	private Date updateTime;

	@Column(name = "REMARK")
	private String remark;

	@Column(name = "OPER_ID")
	private Long oper_id;
	
	@Column(name = "OPER_NAME")
	private String operName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getOperName() {
		return operName;
	}

	public void setOperName(String operName) {
		this.operName = operName;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getPrivilegesIds() {
		return privilegesIds;
	}

	public void setPrivilegesIds(String privilegesIds) {
		this.privilegesIds = privilegesIds;
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

	public Long getOper_id() {
		return oper_id;
	}

	public void setOper_id(Long oper_id) {
		this.oper_id = oper_id;
	}

}
