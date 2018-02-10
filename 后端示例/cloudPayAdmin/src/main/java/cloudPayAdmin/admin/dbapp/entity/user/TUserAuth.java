package cloudPayAdmin.admin.dbapp.entity.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 * 用户授权表
 */

//@Entity(name = "T_USER_AUTH")
//@SequenceGenerator(name="SEQ_USER_AUTH",sequenceName="SEQ_USER_AUTH",allocationSize=1,initialValue=1)
public class TUserAuth implements java.io.Serializable {
	
	public static String IDENTITY_TYPE_PHONE = "phone";
	public static String IDENTITY_TYPE_EMAIL = "email";
	public static String IDENTITY_TYPE_QQ = "qq";
	public static String IDENTITY_TYPE_WEIXIN = "weixin";
	
	private static final long serialVersionUID = 25954213347442644L;
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_USER_AUTH")
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;
	@Column(name = "USER_ID", nullable = false)
	private Long userId;
	@Column(name = "IDENTITY_TYPE", nullable = false, length = 8)
	private String identityType;
	@Column(name = "IDENTIFIER", nullable = false, length = 32)
	private String identifier;
	@Column(name = "CREDENTIAL", nullable = false, length = 64)
	private String credential;
	@Column(name = "LAST_LOGIN", length = 7)
	private Date lastLogin;
	@Column(name = "IP", length = 32)
	private String ip;

	/** default constructor */
	public TUserAuth() {
	}

	/** minimal constructor */
	public TUserAuth(Long id, Long userId, String identityType,
			String identifier, String credential) {
		this.id = id;
		this.userId = userId;
		this.identityType = identityType;
		this.identifier = identifier;
		this.credential = credential;
	}

	/** full constructor */
	public TUserAuth(Long id, Long userId, String identityType,
			String identifier, String credential, Date lastLogin, String ip) {
		this.id = id;
		this.userId = userId;
		this.identityType = identityType;
		this.identifier = identifier;
		this.credential = credential;
		this.lastLogin = lastLogin;
		this.ip = ip;
	}
	
	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return this.userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}	
	public String getIdentityType() {
		return this.identityType;
	}
	public void setIdentityType(String identityType) {
		this.identityType = identityType;
	}	
	public String getIdentifier() {
		return this.identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}	
	public String getCredential() {
		return this.credential;
	}
	public void setCredential(String credential) {
		this.credential = credential;
	}
	public Date getLastLogin() {
		return this.lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	public String getIp() {
		return this.ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
}
