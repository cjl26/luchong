package cloudPayAdmin.admin.dbapp.entity.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "t_admin_user")
public class TAdminUser {

	public static final Integer USER_STATUS_IN_USE = 1;
	public static final String USER_STATUS_IN_USE_MSG = "在用";
	public static final Integer USER_STATUS_NOT_USE = 0;
	public static final String USER_STATUS_NOT_USE_MSG = "停用";

	// 在用/停用的状态
	public static Map<String, String> initStatusMap() {
		Map<String, String> statusMap = new HashMap<String, String>();
		statusMap.put(USER_STATUS_IN_USE + "", USER_STATUS_IN_USE_MSG);
		statusMap.put(USER_STATUS_NOT_USE + "", USER_STATUS_NOT_USE_MSG);
		return statusMap;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, precision = 0)
	private Long id;
	private String userName;
	private String account;
	private String password;
	private String phone;
	private String email;
	private Integer status;
	private Date createTime;
	private Date updateTime;
	private String remark;

//	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ADMIN_USER")
//	@Column(name = "ID", nullable = false, precision = 0)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Basic
	@Column(name = "USER_NAME")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Basic
	@Column(name = "ACCOUNT")
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Basic
	@Column(name = "PASSWORD")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Basic
	@Column(name = "PHONE")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Basic
	@Column(name = "EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Basic
	@Column(name = "STATUS")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Basic
	@Column(name = "CREATE_TIME")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Basic
	@Column(name = "UPDATE_TIME")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Basic
	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
