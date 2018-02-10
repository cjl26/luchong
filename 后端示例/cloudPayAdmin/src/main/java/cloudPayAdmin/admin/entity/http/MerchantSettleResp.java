package cloudPayAdmin.admin.entity.http;

import java.util.List;

import cloudPayAdmin.admin.dbapp.entity.hicatcard.transaction.TTransaction;

public class MerchantSettleResp {
	
	private Long merchantId;
	
	private String settleStartTime;
	
	private String settleEndTime;
	
	private String settleFee;
	
	private List<MerchantSettleResult> merchantSettleResultList;	
	
	private List<TTransaction> transactionList;

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
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

	public String getSettleFee() {
		return settleFee;
	}

	public void setSettleFee(String settleFee) {
		this.settleFee = settleFee;
	}

	public List<MerchantSettleResult> getMerchantSettleResultList() {
		return merchantSettleResultList;
	}

	public void setMerchantSettleResultList(List<MerchantSettleResult> merchantSettleResultList) {
		this.merchantSettleResultList = merchantSettleResultList;
	}

	public List<TTransaction> getTransactionList() {
		return transactionList;
	}

	public void setTransactionList(List<TTransaction> transactionList) {
		this.transactionList = transactionList;
	}

	
	
	
}
