package cloudPayAdmin.admin.dbapp.entity.merchant;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenjiajian on 2017/4/24.
 */
//@Entity
//@Table(name = "T_MERCHANT_ACCT_TRANS")
//@SequenceGenerator(name = "SEQ_MERCHANT_ACCT_TRANS", sequenceName = "SEQ_MERCHANT_ACCT_TRANS", allocationSize = 1, initialValue = 1)
public class TMerchantAcctTrans {

	// 加减值标记
	public static long FLAG_ADD = 1; // 加值
	public static String FLAG_ADD_MSG = "加值";
	public static long FLAG_DEDUCT = 2; // 减值
	public static String FLAG_DEDUCT_MSG = "减值";

	// 交易类型
	public static long TRADE_TYPE_ACCT_RECHARGE = 1; // 账户充值
	public static String TRADE_TYPE_ACCT_RECHARGE_MSG = "账户充值";
	public static long TRADE_TYPE_TRANSFER_YCT_CARD = 2; // 转账羊城通卡
	public static String TRADE_TYPE_TRANSFER_YCT_CARD_MSG = "转账羊城通卡";
	public static long TRADE_TYPE_QR_CODE = 3; // 二维码交易
	public static String TRADE_TYPE_QR_CODE_MSG = "二维码交易";
	public static long TRADE_TYPE_PRODUCT_TRANSACTION = 4; // 商品交易
	public static String TRADE_TYPE_PRODUCT_TRANSACTION_MSG = "商品交易";
	public static long TRADE_TYPE_REFUND = 5; // 退款
	public static String TRADE_TYPE_REFUND_MSG = "退款";
	public static long TRADE_TYPE_CLOUD_PAY = 6; // 云支付扣款
	public static String TRADE_TYPE_CLOUD_PAY_MSG = "云支付扣款";

	// 加减值
	public static Map<String, String> initFlagMap() {
		Map<String, String> flagMap = new HashMap<String, String>();
		flagMap.put(FLAG_ADD + "", FLAG_ADD_MSG);
		flagMap.put(FLAG_DEDUCT + "", FLAG_DEDUCT_MSG);
		return flagMap;
	}

	// 交易类型
	public static Map<String, String> initTradeTypeMap() {
		Map<String, String> tradeTypeMap = new HashMap<String, String>();
		tradeTypeMap.put(TRADE_TYPE_ACCT_RECHARGE + "", TRADE_TYPE_ACCT_RECHARGE_MSG);
		tradeTypeMap.put(TRADE_TYPE_TRANSFER_YCT_CARD + "", TRADE_TYPE_TRANSFER_YCT_CARD_MSG);
		tradeTypeMap.put(TRADE_TYPE_QR_CODE + "", TRADE_TYPE_QR_CODE_MSG);
		tradeTypeMap.put(TRADE_TYPE_PRODUCT_TRANSACTION + "", TRADE_TYPE_PRODUCT_TRANSACTION_MSG);
		tradeTypeMap.put(TRADE_TYPE_REFUND + "", TRADE_TYPE_REFUND_MSG);
		tradeTypeMap.put(TRADE_TYPE_CLOUD_PAY + "", TRADE_TYPE_CLOUD_PAY_MSG);
		return tradeTypeMap;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MERCHANT_ACCT_TRANS")
	@Column(name = "ID", nullable = false, precision = 0)
	private long id;
	private long acctId;
	private long flag;
	private long tradeType;
	private long tradeFee;
	private Date tradeTime;
	private long balanceBefore;
	private long balanceAfter;
	private String relatedAppid;
	private String relatedOrderId;
	private String remark;
	private Date createTime;


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Basic
	@Column(name = "ACCT_ID", nullable = false, precision = 0)
	public long getAcctId() {
		return acctId;
	}

	public void setAcctId(long acctId) {
		this.acctId = acctId;
	}

	@Basic
	@Column(name = "FLAG", nullable = false, precision = 0)
	public long getFlag() {
		return flag;
	}

	public void setFlag(long flag) {
		this.flag = flag;
	}

	@Basic
	@Column(name = "TRADE_TYPE", nullable = false, precision = 0)
	public long getTradeType() {
		return tradeType;
	}

	public void setTradeType(long tradeType) {
		this.tradeType = tradeType;
	}

	@Basic
	@Column(name = "TRADE_FEE", nullable = false, precision = 0)
	public long getTradeFee() {
		return tradeFee;
	}

	public void setTradeFee(long tradeFee) {
		this.tradeFee = tradeFee;
	}

	@Basic
	@Column(name = "TRADE_TIME", nullable = false)
	public Date getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}

	@Basic
	@Column(name = "BALANCE_BEFORE", nullable = false, precision = 0)
	public long getBalanceBefore() {
		return balanceBefore;
	}

	public void setBalanceBefore(long balanceBefore) {
		this.balanceBefore = balanceBefore;
	}

	@Basic
	@Column(name = "BALANCE_AFTER", nullable = false, precision = 0)
	public long getBalanceAfter() {
		return balanceAfter;
	}

	public void setBalanceAfter(long balanceAfter) {
		this.balanceAfter = balanceAfter;
	}

	@Basic
	@Column(name = "RELATED_APPID", nullable = true, length = 8)
	public String getRelatedAppid() {
		return relatedAppid;
	}

	public void setRelatedAppid(String relatedAppid) {
		this.relatedAppid = relatedAppid;
	}

	@Basic
	@Column(name = "RELATED_ORDER_ID", nullable = true, length = 32)
	public String getRelatedOrderId() {
		return relatedOrderId;
	}

	public void setRelatedOrderId(String relatedOrderId) {
		this.relatedOrderId = relatedOrderId;
	}

	@Basic
	@Column(name = "REMARK", nullable = true, length = 64)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Basic
	@Column(name = "CREATE_TIME", nullable = false)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		TMerchantAcctTrans that = (TMerchantAcctTrans) o;

		if (id != that.id)
			return false;
		if (acctId != that.acctId)
			return false;
		if (flag != that.flag)
			return false;
		if (tradeType != that.tradeType)
			return false;
		if (tradeFee != that.tradeFee)
			return false;
		if (balanceBefore != that.balanceBefore)
			return false;
		if (balanceAfter != that.balanceAfter)
			return false;
		if (tradeTime != null ? !tradeTime.equals(that.tradeTime) : that.tradeTime != null)
			return false;
		if (relatedAppid != null ? !relatedAppid.equals(that.relatedAppid) : that.relatedAppid != null)
			return false;
		if (relatedOrderId != null ? !relatedOrderId.equals(that.relatedOrderId) : that.relatedOrderId != null)
			return false;
		if (remark != null ? !remark.equals(that.remark) : that.remark != null)
			return false;
		if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (int) (acctId ^ (acctId >>> 32));
		result = 31 * result + (int) (flag ^ (flag >>> 32));
		result = 31 * result + (int) (tradeType ^ (tradeType >>> 32));
		result = 31 * result + (int) (tradeFee ^ (tradeFee >>> 32));
		result = 31 * result + (tradeTime != null ? tradeTime.hashCode() : 0);
		result = 31 * result + (int) (balanceBefore ^ (balanceBefore >>> 32));
		result = 31 * result + (int) (balanceAfter ^ (balanceAfter >>> 32));
		result = 31 * result + (relatedAppid != null ? relatedAppid.hashCode() : 0);
		result = 31 * result + (relatedOrderId != null ? relatedOrderId.hashCode() : 0);
		result = 31 * result + (remark != null ? remark.hashCode() : 0);
		result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
		return result;
	}
}
