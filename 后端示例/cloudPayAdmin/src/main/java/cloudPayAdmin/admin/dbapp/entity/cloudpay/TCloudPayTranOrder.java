package cloudPayAdmin.admin.dbapp.entity.cloudpay;

import javax.persistence.*;

import cloudPayAdmin.util.TimeUtil;
import cloudPayAdmin.util.annotation.StringDateFormat;

/**
 * Created by chenjiajian on 2017/7/19.
 */
//@Entity
//@Table(name = "T_CLOUDPAY_TRAN_ORDER", schema = "YCTAPP")
//@SequenceGenerator(name="SEQ_CLOUDPAY_TRAN_ORDER",sequenceName="SEQ_CLOUDPAY_TRAN_ORDER", allocationSize = 1,initialValue=1)
public class TCloudPayTranOrder {
    private long id;
    private String outTradeNo;
    private String termCode;
    private String userId;
    private String cardNum;
   
    @StringDateFormat(oriFormat=TimeUtil.DATE_PATTERN_NOSEPARTOR,destFormat=TimeUtil.DEFAULT_DATE_PATTERN)
    private String createTime;
    
    @StringDateFormat(oriFormat=TimeUtil.DATE_PATTERN_NOSEPARTOR,destFormat=TimeUtil.DEFAULT_DATE_PATTERN)  
    private String updateTime;
  
    @StringDateFormat(oriFormat=TimeUtil.DATE_PATTERN_NOSEPARTOR,destFormat=TimeUtil.DEFAULT_DATE_PATTERN)
    private String transactionTime;
    private String orgCode;
    private String orgName;
    
    private Long payFee;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_CLOUDPAY_TRAN_ORDER")
    @Column(name = "ID", nullable = false, precision = 0)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    
	@Basic
    @Column(name = "PAY_FEE")
    public Long getPayFee() {
		return payFee;
	}

	public void setPayFee(Long payFee) {
		this.payFee = payFee;
	}

	@Basic
    @Column(name = "OUT_TRADE_NO", nullable = false, length = 32)
    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    @Basic
    @Column(name = "TERM_CODE", nullable = false, length = 32)
    public String getTermCode() {
        return termCode;
    }

    public void setTermCode(String termCode) {
        this.termCode = termCode;
    }

    @Basic
    @Column(name = "USER_ID", nullable = false, length = 16)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "CARD_NUM", nullable = false, length = 16)
    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
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
    @Column(name = "TRANSACTION_TIME", nullable = true, length = 32)
    public String getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
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
    
    

}
