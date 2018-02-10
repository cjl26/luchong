package cloudPayAdmin.admin.dbapp.vo.hicatcard;

import java.util.List;

import cloudPayAdmin.util.TimeUtil;
import cloudPayAdmin.util.annotation.StringDateFormat;

public class MerchantSettleVo {
	
	private Long merchantSettleId;
	
	@StringDateFormat(oriFormat=TimeUtil.DATE_PATTERN_NOSEPARTOR,destFormat=TimeUtil.DEFAULT_DATE_PATTERN)
	private String settleStartTime;
	
	@StringDateFormat(oriFormat=TimeUtil.DATE_PATTERN_NOSEPARTOR,destFormat=TimeUtil.DEFAULT_DATE_PATTERN)
	private String settleEndTime;

	private Integer settleFee;
	
	private Long merchantId;
	
	private String merchantName;
	
	@StringDateFormat(oriFormat=TimeUtil.DATE_PATTERN_NOSEPARTOR,destFormat=TimeUtil.DEFAULT_DATE_PATTERN)
	private String createTime;
	
	@StringDateFormat(oriFormat=TimeUtil.DATE_PATTERN_NOSEPARTOR,destFormat=TimeUtil.DEFAULT_DATE_PATTERN)
	private String updateTime;

	private List<MerchantSettleServiceVo> merchantSettleServiceList;

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

	public List<MerchantSettleServiceVo> getMerchantSettleServiceList() {
		return merchantSettleServiceList;
	}

	public void setMerchantSettleServiceList(List<MerchantSettleServiceVo> merchantSettleServiceList) {
		this.merchantSettleServiceList = merchantSettleServiceList;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	
	
}
