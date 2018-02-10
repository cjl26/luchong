package cloudPayAdmin.admin.dbapp.entity.user;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by chenjiajian on 2017/5/5.
 */
//@Entity
//@SequenceGenerator(name="SEQ_USER",sequenceName="SEQ_USER",allocationSize=1,initialValue=1)
//@Table(name = "T_USER")
public class TUser {
    private long id;
    private String userName;
    private long gender;
    private String avatar;
    private long status;
    private String phone;
    private String email;
    private long payFlag;
    //private String payPass;
    private Date createTime;
    private Date updateTime;
    private long realNameFlag;
    private Long picurlType;
    private String background = "";
    private String birthday = "";
    private String signature = "";
    private long loginTrialFailedTime;
    //private long payTrialFailedTime;
    

    public static long PAY_FLAG_UNSET = 0; // 未设置
    public static long PAY_FLAG_SET = 1; // 已设置

    public static long STATUS_INVALID = 0; // 无效
    public static long STATUS_VALID = 1; // 有效

    //实名标记：0未设置 1已验证 2验证中 3验证失败请重试
    public static long REALNAME_FLAG_NOTSET = 0;
    public static long REALNAME_FLAG_SET = 1;
    public static long REALNAME_FLAG_CHECKING = 2;
    public static long REALNAME_FLAG_FAILED = 3;


    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_USER")
    @Column(name = "ID", nullable = false, precision = 0)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "USER_NAME", nullable = true, length = 16)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "GENDER", nullable = true, precision = 0)
    public long getGender() {
        return gender;
    }

    public void setGender(long gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "AVATAR", nullable = true, length = 64)
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Basic
    @Column(name = "STATUS", nullable = false, precision = 0)
    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    @Basic
    @Column(name = "PHONE", nullable = true, length = 16)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "EMAIL", nullable = true, length = 32)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "PAY_FLAG", nullable = false, precision = 0)
    public long getPayFlag() {
        return payFlag;
    }

    public void setPayFlag(long payFlag) {
        this.payFlag = payFlag;
    }

//    @Basic
//    @Column(name = "PAY_PASS", nullable = true, length = 64)
//    public String getPayPass() {
//        return payPass;
//    }
//
//    public void setPayPass(String payPass) {
//        this.payPass = payPass;
//    }

    @Basic
    @Column(name = "CREATE_TIME", nullable = true)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "UPDATE_TIME", nullable = true)
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "REAL_NAME_FLAG", nullable = false, precision = 0)
    public long getRealNameFlag() {
        return realNameFlag;
    }

    public void setRealNameFlag(long realNameFlag) {
        this.realNameFlag = realNameFlag;
    }

    @Basic
    @Column(name = "PICURL_TYPE", nullable = true, precision = 0)
    public Long getPicurlType() {
        return picurlType;
    }

    public void setPicurlType(Long picurlType) {
        this.picurlType = picurlType;
    }

    @Basic
    @Column(name = "BACKGROUND", nullable = true, length = 64)
    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    @Basic
    @Column(name = "BIRTHDAY", nullable = true, length = 8)
    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "SIGNATURE", nullable = true, length = 64)
    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
    
    @Basic
    @Column(name = "LOGIN_TRIAL_FAILED_TIME", nullable = true,  precision = 0)
	public long getLoginTrialFailedTime() {
		return loginTrialFailedTime;
	}

	public void setLoginTrialFailedTime(long loginTrialFailedTime) {
		this.loginTrialFailedTime = loginTrialFailedTime;
	}

//    @Basic
//    @Column(name = "PAY_TRIAL_FAILED_TIME", nullable = true,  precision = 0)
//	public long getPayTrialFailedTime() {
//		return payTrialFailedTime;
//	}
//
//	public void setPayTrialFailedTime(long payTrialFailedTime) {
//		this.payTrialFailedTime = payTrialFailedTime;
//	}
    
    

}
