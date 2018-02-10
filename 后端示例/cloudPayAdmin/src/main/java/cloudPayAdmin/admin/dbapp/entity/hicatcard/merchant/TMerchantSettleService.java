package cloudPayAdmin.admin.dbapp.entity.hicatcard.merchant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_merchant_settle_service")
public class TMerchantSettleService {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MERCHANT_SETTLE_SERVICE_ID")
	private Long merchantSettleServiceId;
	
	@Column(name = "MERCHANT_SETTLE_ID")
	private Long merchantSettleId;
	
	@Column(name = "SERVICE_ID")
	private Long serviceId;
	
	@Column(name = "TRANSACTION_TIME")
	private Integer transactionTime;
	
	@Column(name = "TRANSACTION_FEE")
	private Integer transactionFee;
	
	@Column(name = "CREATE_TIME")
	private String createTime;
	
	@Column(name = "UPDATE_TIME")
	private String updateTime;

	public Long getMerchantSettleServiceId() {
		return merchantSettleServiceId;
	}

	public void setMerchantSettleServiceId(Long merchantSettleServiceId) {
		this.merchantSettleServiceId = merchantSettleServiceId;
	}

	public Long getMerchantSettleId() {
		return merchantSettleId;
	}

	public void setMerchantSettleId(Long merchantSettleId) {
		this.merchantSettleId = merchantSettleId;
	}

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public Integer getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(Integer transactionTime) {
		this.transactionTime = transactionTime;
	}

	public Integer getTransactionFee() {
		return transactionFee;
	}

	public void setTransactionFee(Integer transactionFee) {
		this.transactionFee = transactionFee;
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
