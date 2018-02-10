package cloudPayAdmin.admin.dbapp.vo.hicatcard;

import cloudPayAdmin.util.TimeUtil;
import cloudPayAdmin.util.annotation.StringDateFormat;

public class MerchantSettleServiceVo {

	private Long merchantSettleServiceId;

	private Long merchantSettleId;

	private Long serviceId;

	private Integer transactionTime;

	private Integer transactionFee;
	
	@StringDateFormat(oriFormat=TimeUtil.DATE_PATTERN_NOSEPARTOR,destFormat=TimeUtil.DEFAULT_DATE_PATTERN)
	private String createTime;

	@StringDateFormat(oriFormat=TimeUtil.DATE_PATTERN_NOSEPARTOR,destFormat=TimeUtil.DEFAULT_DATE_PATTERN)
	private String updateTime;

	private String serviceName;

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

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

}
