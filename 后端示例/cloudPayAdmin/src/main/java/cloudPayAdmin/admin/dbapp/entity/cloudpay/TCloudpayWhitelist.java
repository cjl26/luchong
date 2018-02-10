package cloudPayAdmin.admin.dbapp.entity.cloudpay;

import javax.persistence.*;

/**
 * Created by chenjiajian on 2017/8/10.
 */
//@Entity
//@Table(name = "T_CLOUDPAY_WHITELIST", schema = "YCTAPP", catalog = "")
//@SequenceGenerator(name="SEQ_CLOUDPAY_WHITELIST",sequenceName="SEQ_CLOUDPAY_TRAN_ORDER", allocationSize = 1,initialValue=1)
public class TCloudpayWhitelist {
	
	public static final Integer STATUS_CAN_USE = 1;
	public static final String STATUS_CAN_USE_MSG = "可用";
	public static final Integer STATUS_CANNOT_USE = 0;
	public static final String STATUS_CANNOT_USE_MSG = "不允许";
	
    private long id;
    private long userId;
    private String cardNum;
    private String appId;
    private long status;
    private String remark;
    private String createTime;
    private String updateTime;
    private String operator;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_CLOUDPAY_WHITELIST")
    @Column(name = "ID", nullable = false, precision = 0)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "USER_ID", nullable = false, precision = 0)
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "CARD_NUM", nullable = true, length = 32)
    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    @Basic
    @Column(name = "APP_ID", nullable = true, length = 32)
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
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
    @Column(name = "REMARK", nullable = true, length = 32)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "CREATE_TIME", nullable = true, length = 32)
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "UPDATE_TIME", nullable = true, length = 32)
    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "OPERATOR", nullable = true, length = 32)
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TCloudpayWhitelist that = (TCloudpayWhitelist) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (status != that.status) return false;
        if (cardNum != null ? !cardNum.equals(that.cardNum) : that.cardNum != null) return false;
        if (appId != null ? !appId.equals(that.appId) : that.appId != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;
        if (operator != null ? !operator.equals(that.operator) : that.operator != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (cardNum != null ? cardNum.hashCode() : 0);
        result = 31 * result + (appId != null ? appId.hashCode() : 0);
        result = 31 * result + (int) (status ^ (status >>> 32));
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (operator != null ? operator.hashCode() : 0);
        return result;
    }
}
