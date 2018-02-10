package cloudPayAdmin.admin.entity.http;

public class MerchantSettleResult {
	
	private Long serviceId;
	
	private String serviceName;
	
	private String transcationFee;
	
	private String transactionTime;
	
	private String transactionTotal;
	
	

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getTranscationFee() {
		return transcationFee;
	}

	public void setTranscationFee(String transcationFee) {
		this.transcationFee = transcationFee;
	}

	public String getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(String transactionTime) {
		this.transactionTime = transactionTime;
	}

	public String getTransactionTotal() {
		return transactionTotal;
	}

	public void setTransactionTotal(String transactionTotal) {
		this.transactionTotal = transactionTotal;
	}
	
	
}
