package cloudPayAdmin.admin.dbapp.entity.hicatcard.merchant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_merchant_settle")
public class TMerchantSettle {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MERCHANT_SETTLE_ID")
	private Long merchantSettleId;
	
	@Column(name = "SETTLE_START_TIME")
	private String settleStartTime;
	
	@Column(name = "SETTLE_END_TIME")
	private String settleEndTime;
	
	@Column(name = "SETTLE_FEE")
	private Integer settleFee;
	
	@Column(name = "MERCHANT_ID")
	private Long merchantId;
	
	@Column(name = "CREATE_TIME")
	private String createTime;
	
	@Column(name = "UPDATE_TIME")
	private String updateTime;

	public Long getMerchantSettleId() {
		return merchantSettleId;
	}

	public void setMerchantSettleId(Long merchantSettleId) {
		this.merchantSettleId = merchantSettleId;
	}

	public String getSettleStartTime() {
		return settleStartTime;
	}

	public void setSettleStartTime(String settleStartTime) {
		this.settleStartTime = settleStartTime;
	}

	public String getSettleEndTime() {
		return settleEndTime;
	}

	public void setSettleEndTime(String settleEndTime) {
		this.settleEndTime = settleEndTime;
	}

	public Integer getSettleFee() {
		return settleFee;
	}

	public void setSettleFee(Integer settleFee) {
		this.settleFee = settleFee;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	
}
