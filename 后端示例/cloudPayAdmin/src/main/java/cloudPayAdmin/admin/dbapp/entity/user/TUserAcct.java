package cloudPayAdmin.admin.dbapp.entity.user;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 * 用户账户信息表
 */
//@Entity(name = "T_USER_ACCT")
//@SequenceGenerator(name = "SEQ_USER_ACCT", sequenceName = "SEQ_USER_ACCT", allocationSize = 1, initialValue = 1)
public class TUserAcct implements Serializable {

	// 业务类型
	public static long USER_ACCT_ACCT_TYPE_MAIN = 1; // 1羊城通宝主账户

	public static long ACCT_STS_INIT = 0; // 0未激活
	public static long ACCT_STS_INUSE = 1; // 1在用
	public static long ACCT_STS_PAUSE = 2; // 2暂停

	private static final long serialVersionUID = 259542134107442644L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USER_ACCT")
	@Column(name = "ID", unique = true, nullable = false)
	private long id;
	@Column(name = "USER_ID", nullable = false)
	private long userId;
	@Column(name = "PAY_PASS", length = 64)
	private String payPass;
	@Column(name = "ACCT_TYPE", nullable = false)
	private long acctType;
	@Column(name = "BALANCE", nullable = false)
	private long balance;
	@Column(name = "FROZEN")
	private long frozen;
	@Column(name = "STATUS", nullable = false)
	private long status;
	@Column(name = "PAY_TRIAL_FAILED_TIME", nullable = false)
	private long payTrialFailedTime;
	@Column(name = "REMARK", length = 64)
	private String remark;
	@Column(name = "CREATE_TIME", length = 7)
	private Date createTime;
	@Column(name = "UPDATE_TIME", length = 7)
	private Date updateTime;

	/** default constructor */
	public TUserAcct() {
	}

	/** minimal constructor */
	public TUserAcct(long id, long userId, long acctType, long balance, long status) {
		this.id = id;
		this.userId = userId;
		this.acctType = acctType;
		this.balance = balance;
		this.status = status;
	}

	/** full constructor */
	public TUserAcct(long id, long userId, long acctType, long balance, long frozen, long status, String remark,
			Date createTime, Date updateTime) {
		this.id = id;
		this.userId = userId;
		this.acctType = acctType;
		this.balance = balance;
		this.frozen = frozen;
		this.status = status;
		this.remark = remark;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getAcctType() {
		return this.acctType;
	}

	public void setAcctType(long acctType) {
		this.acctType = acctType;
	}

	public long getBalance() {
		return this.balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public long getFrozen() {
		return this.frozen;
	}

	public void setFrozen(long frozen) {
		this.frozen = frozen;
	}

	public long getStatus() {
		return this.status;
	}

	public void setStatus(long status) {
		this.status = status;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getPayPass() {
		return payPass;
	}

	public void setPayPass(String payPass) {
		this.payPass = payPass;
	}

	public long getPayTrialFailedTime() {
		return payTrialFailedTime;
	}

	public void setPayTrialFailedTime(long payTrialFailedTime) {
		this.payTrialFailedTime = payTrialFailedTime;
	}

}