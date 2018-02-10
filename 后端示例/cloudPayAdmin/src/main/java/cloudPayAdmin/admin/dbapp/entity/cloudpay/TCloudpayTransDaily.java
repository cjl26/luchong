package cloudPayAdmin.admin.dbapp.entity.cloudpay;

import javax.persistence.*;

import cloudPayAdmin.util.TimeUtil;
import cloudPayAdmin.util.annotation.StringDateFormat;

/**
 * Created by chenjiajian on 2017/9/6.
 */
//@Entity
//@Table(name = "T_CLOUDPAY_TRANS_DAILY", schema = "YCTAPP")
//@SequenceGenerator(name="SEQ_CLOUDPAY_TRANS_DAILY",sequenceName="SEQ_CLOUDPAY_TRANS_DAILY", allocationSize = 1,initialValue=1)
public class TCloudpayTransDaily {
    private long id;
   
    @StringDateFormat(oriFormat=TimeUtil.DATE_PATTERN_ONLYDATE_NOSEPARTOR,destFormat=TimeUtil.DATE_PATTERN_ONLYDATE)
    private String transDate;
  
    private String orgCode;
    private String orgName;
    private String termCode;
    private long transTotalCount;
    private long transTotalAmount;
    private long transSuccCount;
    private long transFailCount;
    private long transSuccAmount;
    private long transFailAmount;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_CLOUDPAY_TRANS_DAILY")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "TRANS_DATE")
    public String getTransDate() {
        return transDate;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate;
    }

    @Basic
    @Column(name = "ORG_CODE")
    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    @Basic
    @Column(name = "ORG_NAME")
    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    @Basic
    @Column(name = "TERM_CODE")
    public String getTermCode() {
        return termCode;
    }

    public void setTermCode(String termCode) {
        this.termCode = termCode;
    }

    @Basic
    @Column(name = "TRANS_TOTAL_COUNT")
    public long getTransTotalCount() {
        return transTotalCount;
    }

    public void setTransTotalCount(long transTotalCount) {
        this.transTotalCount = transTotalCount;
    }

    @Basic
    @Column(name = "TRANS_TOTAL_AMOUNT")
    public long getTransTotalAmount() {
        return transTotalAmount;
    }

    public void setTransTotalAmount(long transTotalAmount) {
        this.transTotalAmount = transTotalAmount;
    }

    @Basic
    @Column(name = "TRANS_SUCC_COUNT")
    public long getTransSuccCount() {
        return transSuccCount;
    }

    public void setTransSuccCount(long transSuccCount) {
        this.transSuccCount = transSuccCount;
    }

    @Basic
    @Column(name = "TRANS_FAIL_COUNT")
    public long getTransFailCount() {
        return transFailCount;
    }

    public void setTransFailCount(long transFailCount) {
        this.transFailCount = transFailCount;
    }

    @Basic
    @Column(name = "TRANS_SUCC_AMOUNT")
    public long getTransSuccAmount() {
        return transSuccAmount;
    }

    public void setTransSuccAmount(long transSuccAmount) {
        this.transSuccAmount = transSuccAmount;
    }

    @Basic
    @Column(name = "TRANS_FAIL_AMOUNT")
    public long getTransFailAmount() {
        return transFailAmount;
    }

    public void setTransFailAmount(long transFailAmount) {
        this.transFailAmount = transFailAmount;
    }

}
